package org.example.blogplatform.Services;

import org.example.blogplatform.domain.entities.User;

import java.util.UUID;

public interface UserService {

    User getUserById(UUID userId);
}
