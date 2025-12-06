package org.example.blogplatform.controllers;


import lombok.RequiredArgsConstructor;
import org.example.blogplatform.Services.AuthenticationService;
import org.example.blogplatform.domain.dtos.AuthResponse;
import org.example.blogplatform.domain.dtos.LoginRequest;
import org.example.blogplatform.domain.dtos.RegisterRequest;
import org.example.blogplatform.domain.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        String token = authenticationService.generateToken(userDetails);
        AuthResponse authResponse = AuthResponse.builder().token(token).expiresIn(24000).build();
        return ResponseEntity.ok(authResponse);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = authenticationService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

}
