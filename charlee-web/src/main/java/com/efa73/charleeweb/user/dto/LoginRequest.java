package com.efa73.charleeweb.user.dto;

public record LoginRequest(
        String email,
        String password
) {
}
