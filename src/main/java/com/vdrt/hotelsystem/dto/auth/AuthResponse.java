package com.vdrt.hotelsystem.dto.auth;

public record AuthResponse(String accessToken, String refreshToken, String rol) {}
