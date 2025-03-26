package com.efa73.charleeweb.user.domain.repository;

import com.efa73.charleeweb.user.domain.entity.Role;
import com.efa73.charleeweb.user.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByRole(Role role);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
