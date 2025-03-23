package com.efa73.charleeweb.user.repository;

import com.efa73.charleeweb.user.domain.Role;
import com.efa73.charleeweb.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByRole(Role role);
}
