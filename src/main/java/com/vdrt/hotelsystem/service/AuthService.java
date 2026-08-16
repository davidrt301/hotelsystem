package com.vdrt.hotelsystem.service;

import com.vdrt.hotelsystem.dto.auth.AuthResponse;
import com.vdrt.hotelsystem.dto.auth.LoginRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse refresh(String refreshToken);
    void logout(String refreshToken);
}