package com.efa73.charleeweb.account.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.efa73.charleeweb.account.domain.entity.Role;
import com.efa73.charleeweb.account.domain.entity.User;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void Role이_ADMIN인_계정이_존재하면_true_반환() {
        //given
        User admin = User.of("admin", "admin@test.com", "testtest", null, Role.ADMIN);
        userRepository.save(admin);

        //when
        boolean exists = userRepository.existsByRole(Role.ADMIN);

        //then
        assertThat(exists).isTrue();
    }

    @Test
    void Role이_ADMIN인_계정이_존재하지_않으면_false_반환() {
        //when
        boolean exists = userRepository.existsByRole(Role.ADMIN);

        //then
        assertThat(exists).isFalse();
    }

    @Test
    void 이메일이_존재하면_true_반환() {
        // given
        String email = "user@example.com";
        User user = User.of("user", email, "password123", null, Role.USER);
        userRepository.save(user);

        // when
        boolean exists = userRepository.existsByEmail(email);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void 이메일이_존재하지_않으면_false_반환() {
        // given
        String email = "user@example.com";

        // when
        boolean exists = userRepository.existsByEmail(email);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void 이메일로_계정을_찾을_수_있다() {
        // given
        String email = "user@example.com";
        User user = User.of("user", email, "password123", null, Role.COMPANY);
        userRepository.save(user);

        // when
        Optional<User> foundAccount = userRepository.findByEmail(email);

        // then
        assertThat(foundAccount).isPresent();
        assertThat(foundAccount.get().getEmail()).isEqualTo(email);
    }

    @Test
    void 존재하지_않는_이메일로_계정을_찾을_수_없다() {
        // given
        String email = "nonexistent@example.com";

        // when
        Optional<User> foundAccount = userRepository.findByEmail(email);

        // then
        assertThat(foundAccount).isNotPresent();
    }
}
