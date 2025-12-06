package org.example.blogplatform.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.blogplatform.Services.TagService;
import org.example.blogplatform.domain.dtos.TagResponse;
import org.example.blogplatform.domain.entities.Tag;
import org.example.blogplatform.mappers.TagMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
