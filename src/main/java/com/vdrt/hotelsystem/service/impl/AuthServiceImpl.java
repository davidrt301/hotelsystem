package com.vdrt.hotelsystem.service.impl;

import com.vdrt.hotelsystem.dto.auth.AuthResponse;
import com.vdrt.hotelsystem.dto.auth.LoginRequest;
import com.vdrt.hotelsystem.exception.ConflictException;
import com.vdrt.hotelsystem.exception.ResourceNotFoundException;
import com.vdrt.hotelsystem.model.RefreshToken;
import com.vdrt.hotelsystem.model.Usuario;
import com.vdrt.hotelsystem.repository.RefreshTokenRepository;
import com.vdrt.hotelsystem.repository.UsuarioRepository;
import com.vdrt.hotelsystem.security.JwtService;
import com.vdrt.hotelsystem.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpirationMs;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                            UsuarioRepository usuarioRepository,
                            RefreshTokenRepository refreshTokenRepository,
                            JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return generarRespuestaConTokens(usuario);
    }

    @Override
    public AuthResponse refresh(String refreshTokenValue) {
        RefreshToken guardado = refreshTokenRepository.findByToken(refreshTokenValue)
                .orElseThrow(() -> new ConflictException("Refresh token invalido"));

        if (guardado.isRevocado() || guardado.getFechaExpiracion().isBefore(Instant.now())) {
            throw new ConflictException("Refresh token expirado o revocado");
        }

        Usuario usuario = guardado.getUsuario();

        if (!usuario.isHabilitado()) {
            throw new ConflictException("Usuario deshabilitado");
        }

        // rotacion: invalidamos el refresh usado y emitimos uno nuevo
        refreshTokenRepository.delete(guardado);

        return generarRespuestaConTokens(usuario);
    }

    @Override
    public void logout(String refreshTokenValue) {
        refreshTokenRepository.findByToken(refreshTokenValue)
                .ifPresent(refreshTokenRepository::delete);
    }

    private AuthResponse generarRespuestaConTokens(Usuario usuario) {
        String accessToken = jwtService.generarAccessToken(usuario);
        String refreshTokenValue = jwtService.generarRefreshToken(usuario);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(refreshTokenValue);
        refreshToken.setUsuario(usuario);
        refreshToken.setFechaExpiracion(Instant.now().plusMillis(refreshExpirationMs));
        refreshToken.setRevocado(false);
        refreshTokenRepository.save(refreshToken);

        return new AuthResponse(accessToken, refreshTokenValue, usuario.getRol().name());
    }
}
