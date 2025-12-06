package org.example.blogplatform.Services;

import org.example.blogplatform.domain.dtos.LoginRequest;
import org.example.blogplatform.domain.dtos.RegisterRequest;
import org.example.blogplatform.domain.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService{
    UserDetails authenticateUser(String email, String password);
    String generateToken(UserDetails userDetails);
    UserDetails validateToken(String token);
    public User register(RegisterRequest request);
}
