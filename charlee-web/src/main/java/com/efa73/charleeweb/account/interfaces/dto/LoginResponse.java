package com.efa73.charleeweb.account.interfaces.dto;

public record LoginResponse(
        String email
) {
    public static LoginResponse of(String email) {
        return new LoginResponse(email);
    }
}
