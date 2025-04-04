package com.efa73.charleeweb.account.interfaces.dto;

import com.efa73.charleeweb.account.domain.entity.Account;
import com.efa73.charleeweb.account.domain.entity.Role;

/**
 * DTO for {@link User}
 */
public record RegisterRequest(
        String name,
        String email,
        String phone,
        Role role
) {

    public static RegisterRequest of(String name, String email, String phone, Role role) {
        return new RegisterRequest(name, email, phone, role);
    }

    public static RegisterRequest from(Account account) {
        return new RegisterRequest(
                account.getName(),
                account.getEmail(),
                account.getPhone(),
                account.getRole()
        );
    }
}
