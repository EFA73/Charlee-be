package com.efa73.charleeweb.config;

import com.efa73.charleeweb.account.domain.entity.Account;
import com.efa73.charleeweb.account.domain.entity.Role;
import com.efa73.charleeweb.account.domain.repository.AccountRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminInitializer implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final AccountRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminProperties adminProperties;

    private String adminEmail;
    private String adminPassword;

    @PostConstruct
    private void init() {
        this.adminEmail = adminProperties.getEmail();
        this.adminPassword = adminProperties.getPassword();
    }

    @Override
    public void run(String... args) throws Exception {
        if (!accountRepository.existsByRole(Role.ADMIN)) {
            Account admin = Account.createEntity(adminEmail, passwordEncoder.encode(adminPassword), Role.ADMIN);
            accountRepository.save(admin);
        }
    }
}
