package com.efa73.charleeweb.account.domain.service;

import com.efa73.charleeweb.account.domain.entity.Account;
import com.efa73.charleeweb.account.domain.repository.AccountRepository;
import com.efa73.charleeweb.account.interfaces.dto.RegisterRequest;
import com.efa73.charleeweb.common.exception.CharleeException;
import com.efa73.charleeweb.common.exception.CommonErrorCode;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long registerUser(RegisterRequest request) {
        validateEmailDuplicate(request.email());
        String rawPassword = generateRandomPassword();
        log.info("register password: {}", rawPassword); //TODO: notifyPassword() 구현 후 삭제
        Account user = Account.of(
                request.name(),
                request.email(),
                passwordEncoder.encode(rawPassword),
                request.phone(),
                request.role()
        );
        Account savedAccount = userRepository.save(user);
//        notifyPassword(); //TODO: 새 유저에게 패스워드 알려주는 기능 필요
//        return user.getId(); //TODO: 질문
        return savedAccount.getId();
    }

    private void validateEmailDuplicate(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CharleeException(CommonErrorCode.EMAIL_ALREADY_EXISTS);
        }
    }

    private static String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
