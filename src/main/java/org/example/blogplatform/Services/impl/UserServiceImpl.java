package org.example.blogplatform.Services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.blogplatform.Services.UserService;
import org.example.blogplatform.domain.entities.User;
import org.example.blogplatform.repostries.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User getUserById(UUID userId) {

        return userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException(" user not found by id" + userId));
    }
}
