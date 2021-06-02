package me.screw.brooklin.controller;

import me.screw.brooklin.domain.Account;
import me.screw.brooklin.request.RegisterRequest;
import me.screw.brooklin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class registerController {

    @Autowired
    public AccountService accountService;

    @PostMapping("/register")
    public void registerMember(@RequestBody RegisterRequest registerRequest) {
        String id = registerRequest.getAccountId();
        String pw = registerRequest.getAccountPassword();
        Account account = new Account(id,pw);
        accountService.saveAccount(account);
    }

}
