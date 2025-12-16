package org.example.blogplatform.Services;

import org.example.blogplatform.domain.CreatePostRequest;
import org.example.blogplatform.domain.UpdatePostRequest;
import org.example.blogplatform.domain.dtos.CreatePostRequestDto;
import org.example.blogplatform.domain.entities.Post;
import org.example.blogplatform.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post getPost(UUID id);
    List<Post> getPosts(UUID categoryId, UUID tagId);

    List<Post> getDrafts(User loggedUser);

    Post createPost(User user, CreatePostRequest createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);

    void deletePost(UUID id);
}
