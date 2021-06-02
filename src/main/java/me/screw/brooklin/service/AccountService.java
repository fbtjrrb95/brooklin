package me.screw.brooklin.service;

import me.screw.brooklin.domain.Account;
import me.screw.brooklin.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;

@Service
public class AccountService {

    @PersistenceContext
    AccountRepository accountRepository;

    public void saveAccount(Account account){
        accountRepository.save(account);
    }
}
