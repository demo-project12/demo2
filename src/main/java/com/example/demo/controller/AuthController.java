package com.example.demo.controller;

import com.example.demo.domain.AuthProvider;
import com.example.demo.domain.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.SignUpRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> body) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        body.get("email"),
                        body.get("password")
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(
                new JSONObject()
                        .appendField("tokenType", "Bearer")
                        .appendField("accessToken", token)
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getIdx()).toUri();

        return ResponseEntity.created(location)
                .body(new JSONObject()
                        .appendField("result", true)
                        .appendField("message", "User registered successfully@"));
    }
}
