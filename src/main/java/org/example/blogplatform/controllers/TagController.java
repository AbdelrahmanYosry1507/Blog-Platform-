package org.example.blogplatform.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.blogplatform.Services.TagService;
import org.example.blogplatform.domain.dtos.CreateTagsRequest;
import org.example.blogplatform.domain.dtos.TagResponse;
import org.example.blogplatform.domain.entities.Tag;
import org.example.blogplatform.mappers.TagMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
       List<Tag> tags = tagService.getTags();
       List<TagResponse> tagResponses = tags.stream().map(tagMapper::toTagResponse).toList();
       return ResponseEntity.ok(tagResponses);
    }
    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(@RequestBody CreateTagsRequest request) {
        List<Tag> tags = tagService.createTag(request.getNames());
        List<TagResponse> tagResponses = tags.stream().map(tagMapper::toTagResponse).toList();
        return  new ResponseEntity<>(tagResponses, HttpStatus.CREATED);
    }

}
