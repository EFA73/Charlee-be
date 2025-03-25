package com.efa73.charleeweb.user.dto;

public record LoginResponse(
        String token,
        String email
) {
    public static LoginResponse of(String token, String email) {
        return new LoginResponse(token, email);
    }
}
