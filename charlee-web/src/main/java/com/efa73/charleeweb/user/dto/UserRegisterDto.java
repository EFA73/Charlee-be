package com.efa73.charleeweb.user.dto;

import com.efa73.charleeweb.user.domain.Role;
import com.efa73.charleeweb.user.domain.User;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.efa73.charleeweb.user.domain.User}
 */
public record UserRegisterDto(
        Long id,
        String name,
        String email,
        String phone,
        Role role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static UserRegisterDto of(Long id, String name, String email, String phone, Role role) {
        return new UserRegisterDto(id, name, email, phone, role, null, null);
    }

    public static UserRegisterDto of(Long id, String name, String email, String phone, Role role,
                                     LocalDateTime createdAt,
                                     LocalDateTime updatedAt) {
        return new UserRegisterDto(id, name, email, phone, role, createdAt, updatedAt);
    }

    public static UserRegisterDto from(User user) {
        return new UserRegisterDto(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

//    public User toEntity() {
//        return User.of(
//
//        )
//    }
}