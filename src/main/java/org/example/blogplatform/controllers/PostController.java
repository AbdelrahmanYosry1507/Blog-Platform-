package org.example.blogplatform.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Negative;
import lombok.RequiredArgsConstructor;
import org.example.blogplatform.Services.PostService;
import org.example.blogplatform.Services.UserService;
import org.example.blogplatform.domain.CreatePostRequest;
import org.example.blogplatform.domain.UpdatePostRequest;
import org.example.blogplatform.domain.dtos.CreatePostRequestDto;
import org.example.blogplatform.domain.dtos.PostDto;
import org.example.blogplatform.domain.dtos.UpdatePostRequestDto;
import org.example.blogplatform.domain.entities.Post;
import org.example.blogplatform.domain.entities.User;
import org.example.blogplatform.mappers.PostMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam(required = false) UUID categoryId,@RequestParam(required = false) UUID tagId){
        List<Post> posts =  postService.getPosts(categoryId, tagId);
        List<PostDto> postDtos = posts.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userId){

        User LoggedUser = userService.getUserById(userId);
        List<Post> posts = postService.getDrafts(LoggedUser);
        List<PostDto> postDtos = posts.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(postDtos);

    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody CreatePostRequestDto createPostRequestDto,@Valid @RequestAttribute UUID userId){
        User loggedUser = userService.getUserById(userId);
        CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(createPostRequestDto);
        Post post = postService.createPost(loggedUser,createPostRequest);
        PostDto postDto = postMapper.toDto(post);
        return ResponseEntity.ok(postDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePostRequestDto updatePostRequestDto
            ){
        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(updatePostRequestDto);
        Post updatePost = postService.updatePost(id,updatePostRequest);
        PostDto updatedPostDto = postMapper.toDto(updatePost);
        return ResponseEntity.ok(updatedPostDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable UUID id){
        Post post = postService.getPost(id);
        PostDto postDto = postMapper.toDto(post);
        return ResponseEntity.ok(postDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable UUID id){
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }


}
