package com.efa73.charleeweb.config;

import static org.assertj.core.api.Fail.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.efa73.charleeweb.account.domain.entity.Account;
import com.efa73.charleeweb.account.domain.entity.Role;
import com.efa73.charleeweb.account.domain.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AdminInitializerTest {

    @InjectMocks
    private AdminInitializer adminInitializer;
    @Mock
    private AccountRepository userRepository;
    @Mock
    private AdminProperties adminProperties;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void 관리자_계정_없으면_생성해야함() {
        //given
        given(userRepository.existsByRole(Role.ADMIN)).willReturn(false);

        //when
        try {
            adminInitializer.run();
        } catch (Exception e) {
            fail("예외 발생하면 안됨.");
        }

        //then
        verify(userRepository).save(any(Account.class));
    }

    @Test
    void 관리자_계정_있으면_생성하지_않아야함() {
        //given
        given(userRepository.existsByRole(Role.ADMIN)).willReturn(true);

        //when
        try {
            adminInitializer.run();
        } catch (Exception e) {
            fail("예외 발생하면 안됨.");
        }

        //then
        verify(userRepository, never()).save(any(Account.class));
    }
}