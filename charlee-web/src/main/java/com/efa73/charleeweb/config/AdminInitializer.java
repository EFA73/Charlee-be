package com.efa73.charleeweb.config;

import com.efa73.charleeweb.account.domain.entity.Role;
import com.efa73.charleeweb.account.domain.entity.Account;
import com.efa73.charleeweb.account.domain.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminProperties adminProperties;

    private String adminName;
    private String adminEmail;
    private String adminPassword;

    @PostConstruct
    private void init() {
        this.adminName = adminProperties.getName();
        this.adminEmail = adminProperties.getEmail();
        this.adminPassword = adminProperties.getPassword();
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByRole(Role.ADMIN)) {
            Account admin = Account.of(adminName, adminEmail, passwordEncoder.encode(adminPassword), null,
                    Role.ADMIN);
            userRepository.save(admin);
        }
    }
}
