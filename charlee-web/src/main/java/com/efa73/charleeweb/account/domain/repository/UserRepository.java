package com.efa73.charleeweb.account.domain.repository;

import com.efa73.charleeweb.account.domain.entity.Role;
import com.efa73.charleeweb.account.domain.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {

    boolean existsByRole(Role role);

    boolean existsByEmail(String email);

    Optional<Account> findByEmail(String email);
}
