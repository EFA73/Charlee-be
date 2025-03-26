package com.efa73.charleeweb.user.domain.service;

import com.efa73.charleeweb.common.exception.AlreadyExistsException;
import com.efa73.charleeweb.user.domain.entity.User;
import com.efa73.charleeweb.user.domain.repository.UserRepository;
import com.efa73.charleeweb.user.interfaces.dto.UserRegisterRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long registerUser(UserRegisterRequest request) {
        validateEmailDuplicate(request.email());
        User user = User.of(
                request.name(),
                request.email(),
                passwordEncoder.encode(generateRandomPassword()),
                request.phone(),
                request.role()
        );
        User savedUser = userRepository.save(user);
//        notifyPassword(); //TODO: 새 유저에게 패스워드 알려주는 기능 필요
//        return user.getId(); //TODO: 질문
        return savedUser.getId();
    }

    private void validateEmailDuplicate(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistsException();
        }
    }

    private static String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
