package com.example.service;

import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.ClientException;
import com.example.exception.ConflictException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Account register(Account account) throws ClientException, ConflictException {
        if(account.getUsername() == null || account.getPassword() == null || account.getPassword().length() < 4) {
            throw new ClientException("Invalid user or password");
        }

        if(accountRepository.findOneByUsername(account.getUsername()).isPresent()){
            throw new ConflictException("Duplicate found");
        }
        
        return accountRepository.save(account);
    }
    public Account login(Account account) throws LoginException {
        Account duplicate = accountRepository.findOneByUsername(account.getUsername()).orElseThrow(()-> new LoginException("Unauthorized"));
        if(account.getPassword() == null || !account.getPassword().equalsIgnoreCase(duplicate.getPassword())) {
            throw new LoginException("Unauthorized");
        }
        return duplicate;
    }
}
