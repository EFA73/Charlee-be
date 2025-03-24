package com.efa73.charleeweb.user.dto;

import com.efa73.charleeweb.user.domain.Role;
import com.efa73.charleeweb.user.domain.User;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.efa73.charleeweb.user.domain.User}
 */
public record UserRegisterRequest(
        Long id,
        String name,
        String email,
        String phone,
        Role role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static UserRegisterRequest of(Long id, String name, String email, String phone, Role role) {
        return new UserRegisterRequest(id, name, email, phone, role, null, null);
    }

    public static UserRegisterRequest of(Long id, String name, String email, String phone, Role role,
                                         LocalDateTime createdAt,
                                         LocalDateTime updatedAt) {
        return new UserRegisterRequest(id, name, email, phone, role, createdAt, updatedAt);
    }

    public static UserRegisterRequest from(User user) {
        return new UserRegisterRequest(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}