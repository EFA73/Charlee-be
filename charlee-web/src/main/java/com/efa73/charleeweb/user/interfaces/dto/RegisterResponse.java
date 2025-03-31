package com.efa73.charleeweb.user.interfaces.dto;

import com.efa73.charleeweb.user.domain.entity.User;

/**
 * DTO for {@link User}
 */
public record RegisterResponse(
        Long id
) {

    public static RegisterResponse of(Long id) {
        return new RegisterResponse(id);
    }
}
