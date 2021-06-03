package me.screw.brooklin.service;

import me.screw.brooklin.domain.Account;
import me.screw.brooklin.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public void saveAccount(Account account){
        accountRepository.save(account);
    }
}
