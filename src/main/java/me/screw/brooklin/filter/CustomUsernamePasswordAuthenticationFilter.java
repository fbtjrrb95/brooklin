package me.screw.brooklin.filter;

// https://johnmarc.tistory.com/74

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;


public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private boolean postOnly = true;
    private HashMap<String, String> jsonRequest;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(postOnly && !request.getMethod().equals("POST")){
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        if(request.getHeader("Content-Type").equals(MediaType.APPLICATION_JSON)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                this.jsonRequest = objectMapper.readValue(request.getReader().lines().collect(Collectors.joining()), new TypeReference<HashMap<String, String>>() {
                });
            } catch (IOException e) {
                throw new AuthenticationServiceException("Request Content-Type(application/json) Parsing Error");
            }
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if(username == null){
            username = "";
        }

        if(password == null){
            password = "";
        }

        username = username.trim();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,password);
        setDetails(request, authToken);
        return this.getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String passwordParameter = super.getPasswordParameter();
        if(request.getHeader("Content-Type").equals(MediaType.APPLICATION_JSON)){
            return this.jsonRequest.get(passwordParameter);
        }
        return request.getParameter(passwordParameter);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String usernameParameter = super.getUsernameParameter();
        if(request.getHeader("Content-Type").equals(MediaType.APPLICATION_JSON)){
            return this.jsonRequest.get(usernameParameter);
        }
        return request.getParameter(usernameParameter);
    }

    @Override
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
