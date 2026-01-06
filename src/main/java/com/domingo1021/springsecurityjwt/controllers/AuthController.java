package com.domingo1021.springsecurityjwt.controllers;

import com.domingo1021.springsecurityjwt.controllers.dto.AuthRequest;
import com.domingo1021.springsecurityjwt.controllers.dto.AuthResponse;
import com.domingo1021.springsecurityjwt.services.AuthService;
import com.domingo1021.springsecurityjwt.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implement login and register functionality
 * <p>
 * login: verify username password, return JWT-like token (HMAC-SHA256) register: create new user
 * (in-memory) verify-token: verify Bearer token
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest request) {
        authService.registerUser(request.username(), request.password());
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "user created"));
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AuthRequest request) {
        authService.registerAdmin(request.username(), request.password());
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "user created"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            var authenticate = authService.authenticate(request.toUsernamePasswordPair());
            return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, authenticate.jwtToken())
                .build();
        } catch (BadCredentialsException badCredentialsException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}