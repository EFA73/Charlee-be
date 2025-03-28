package com.efa73.charleeweb.account.interfaces.dto;

import com.efa73.charleeweb.account.domain.entity.Role;
import com.efa73.charleeweb.account.domain.entity.Account;

/**
 * DTO for {@link Account}
 */
public record UserRegisterRequest(
        String name,
        String email,
        String phone,
        Role role
) {

    public static UserRegisterRequest of(String name, String email, String phone, Role role) {
        return new UserRegisterRequest(name, email, phone, role);
    }

    public static UserRegisterRequest from(Account user) {
        return new UserRegisterRequest(
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getRole()
        );
    }
}
