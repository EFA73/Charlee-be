package com.efa73.charleeweb.account.domain.service;

import com.efa73.charleeweb.account.domain.entity.Account;
import com.efa73.charleeweb.account.domain.entity.Role;
import com.efa73.charleeweb.account.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account create(String email, String password, Role role) {
        Account account = Account.createEntity(email, password, role);
        
        return accountRepository.save(account);
    }
}
