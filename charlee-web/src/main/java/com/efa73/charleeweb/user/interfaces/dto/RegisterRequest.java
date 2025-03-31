package com.efa73.charleeweb.user.interfaces.dto;

import com.efa73.charleeweb.user.domain.entity.Role;
import com.efa73.charleeweb.user.domain.entity.User;

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

    public static RegisterRequest from(User user) {
        return new RegisterRequest(
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getRole()
        );
    }
}
