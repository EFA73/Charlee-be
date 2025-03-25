package com.efa73.charleeweb.user.interfaces.dto;

import com.efa73.charleeweb.user.domain.entity.Role;
import com.efa73.charleeweb.user.domain.entity.User;

/**
 * DTO for {@link User}
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

    public static UserRegisterRequest from(User user) {
        return new UserRegisterRequest(
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getRole()
        );
    }
}
