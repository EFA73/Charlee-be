package com.efa73.charleeweb.account.interfaces.dto;

import com.efa73.charleeweb.account.domain.entity.Account;

/**
 * DTO for {@link Account}
 */
public record UserRegisterResponse(
        Long id
) {

    public static UserRegisterResponse of(Long id) {
        return new UserRegisterResponse(id);
    }
}
