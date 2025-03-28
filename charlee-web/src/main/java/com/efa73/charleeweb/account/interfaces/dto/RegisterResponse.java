package com.efa73.charleeweb.account.interfaces.dto;

import com.efa73.charleeweb.account.domain.entity.Account;

/**
 * DTO for {@link Account}
 */
public record RegisterResponse(
        Long id
) {

    public static RegisterResponse of(Long id) {
        return new RegisterResponse(id);
    }
}
