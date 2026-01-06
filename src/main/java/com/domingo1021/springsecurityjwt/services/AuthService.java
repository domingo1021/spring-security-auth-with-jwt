package com.domingo1021.springsecurityjwt.services;

import com.domingo1021.springsecurityjwt.models.Admin;
import com.domingo1021.springsecurityjwt.models.User;
import com.domingo1021.springsecurityjwt.models.UserRepository;
import com.domingo1021.springsecurityjwt.security.JWTUtil;
import com.domingo1021.springsecurityjwt.services.dto.AuthenticationResult;
import com.domingo1021.springsecurityjwt.services.dto.CustomizedSpringSecurityUserDetails;
import com.domingo1021.springsecurityjwt.services.dto.UsernamePasswordPair;
import java.util.Optional;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
        JWTUtil jwtUtil,
        AuthenticationManager authenticationManager,
        UserRepository userRepository,
        PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String username, String password) {
        Optional<User> maybeUser = userRepository.findByUsername(username);
        if (maybeUser.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        var newUser = new User(username, passwordEncoder.encode(password));
        userRepository.save(newUser);
    }

    public void registerAdmin(String username, String password) {
        Optional<User> maybeUser = userRepository.findByUsername(username);
        if (maybeUser.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        var newUser = new Admin(username, passwordEncoder.encode(password));
        userRepository.save(newUser);
    }

    public AuthenticationResult authenticate(UsernamePasswordPair usernamePasswordPair) {
        var authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                usernamePasswordPair.username(),
                usernamePasswordPair.password()
            )
        );

        return getResponseWithJwtToken(authenticate);
    }

    private @NonNull AuthenticationResult getResponseWithJwtToken(Authentication authenticate) {
        var userDetails = (CustomizedSpringSecurityUserDetails) authenticate.getPrincipal();
        assert userDetails != null;

        var jwtToken = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getRole());
        return new AuthenticationResult(jwtToken);
    }
}
