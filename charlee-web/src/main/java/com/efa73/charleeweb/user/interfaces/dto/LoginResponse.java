package com.efa73.charleeweb.user.interfaces.dto;

public record LoginResponse(
        String email
) {
    public static LoginResponse of(String email) {
        return new LoginResponse(email);
    }
}
