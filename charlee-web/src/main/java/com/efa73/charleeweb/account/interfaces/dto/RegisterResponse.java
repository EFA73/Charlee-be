package com.efa73.charleeweb.account.interfaces.dto;

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
