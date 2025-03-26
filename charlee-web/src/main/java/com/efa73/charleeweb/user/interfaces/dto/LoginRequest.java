package com.efa73.charleeweb.user.interfaces.dto;

public record LoginRequest(
        String email,
        String password
) {
}
