package com.efa73.charleeweb.user.interfaces.dto;

import com.efa73.charleeweb.user.domain.entity.User;

/**
 * DTO for {@link User}
 */
public record UserRegisterResponse(
        Long id
) {

    public static UserRegisterResponse of(Long id) {
        return new UserRegisterResponse(id);
    }
}
