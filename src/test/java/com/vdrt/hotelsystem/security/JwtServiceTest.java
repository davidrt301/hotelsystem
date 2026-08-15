package com.vdrt.hotelsystem.security;

import com.vdrt.hotelsystem.model.Usuario;
import com.vdrt.hotelsystem.model.enums.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;
    private Usuario usuario;

    private static final String SECRET = "12345678901234567890123456789012"; // 32+ chars

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secretKey", SECRET);
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", 900000L); // 15 min
        ReflectionTestUtils.setField(jwtService, "refreshExpiration", 604800000L); // 7 dias

        usuario = new Usuario();
        usuario.setEmail("carlos@hotelsystem.com");
        usuario.setPassword("hashNoImporta");
        usuario.setRol(Rol.RECEPCIONISTA);
        usuario.setHabilitado(true);
    }

    @Test
    void generarAccessToken_debeContenerEmailYRolCorrectos() {
        String token = jwtService.generarAccessToken(usuario);

        assertNotNull(token);
        assertEquals("carlos@hotelsystem.com", jwtService.extraerEmail(token));
        assertEquals("RECEPCIONISTA", jwtService.extraerRol(token));
    }

    @Test
    void generarRefreshToken_debeContenerEmailPeroNoRol() {
        String token = jwtService.generarRefreshToken(usuario);

        assertEquals("carlos@hotelsystem.com", jwtService.extraerEmail(token));
        assertNull(jwtService.extraerRol(token), "El refresh token no deberia tener claim de rol");
    }

    @Test
    void esTokenValido_debeSerTrue_conEmailCorrectoYNoExpirado() {
        String token = jwtService.generarAccessToken(usuario);

        assertTrue(jwtService.esTokenValido(token, "carlos@hotelsystem.com"));
    }

    @Test
    void esTokenValido_debeSerFalse_conEmailDiferente() {
        String token = jwtService.generarAccessToken(usuario);

        assertFalse(jwtService.esTokenValido(token, "otro@hotelsystem.com"));
    }

    @Test
    void esTokenValido_debeSerFalse_conTokenExpirado() {
        // simulamos un token que expiro hace 1 segundo
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", -1000L);
        String tokenExpirado = jwtService.generarAccessToken(usuario);

        assertFalse(jwtService.esTokenValido(tokenExpirado, "carlos@hotelsystem.com"));
    }
}