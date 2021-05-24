package me.screw.brooklin.config;

import me.screw.brooklin.handler.AuthFailureHandler;
import me.screw.brooklin.handler.AuthSuccessHandler;
import me.screw.brooklin.handler.HttpLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Autowired
    private HttpLogoutSuccessHandler logoutSuccessHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(this.userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{

        // Authentication Entry Point 설정
        httpSecurity.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        httpSecurity.csrf().disable();
        httpSecurity.formLogin()
                .permitAll()
                .loginProcessingUrl("/login")
                .usernameParameter("USERNAME")
                .passwordParameter("PASSWORD")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .and()
            .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("logout"))
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
            .sessionManagement()
                .maximumSessions(1)
            ;

        // "v1/feed" url만 authentication 없이 접근 허용
        // 나머지는 authenticaion 필요

        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.GET,"v1/feed").permitAll()
                .anyRequest().authenticated()
                ;
    }

}
