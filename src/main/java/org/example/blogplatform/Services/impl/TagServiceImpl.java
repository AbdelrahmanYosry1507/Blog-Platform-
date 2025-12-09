package org.example.blogplatform.Services.impl;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.blogplatform.Services.TagService;
import org.example.blogplatform.domain.entities.Tag;
import org.example.blogplatform.repostries.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    @Override
    public List<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }


    @Transactional
    @Override
    public List<Tag> createTag(Set<String> tagNames) {
        List<Tag> existingTag = tagRepository.findAllWithPostCount();
        Set<String> existingTagNames = existingTag.stream().map(Tag::getName).collect(Collectors.toSet());
        List<Tag> newTagNames = tagNames.stream().filter(name -> !existingTagNames.contains(name))
                        .map(name->Tag.builder().name(name).posts(new HashSet<>()).build())
                .toList();
        List<Tag> savedTags = new ArrayList<>();
        if (!newTagNames.isEmpty()) {
            savedTags = tagRepository.saveAll(newTagNames);
        }
        savedTags.addAll(existingTag);
        return savedTags;
    }
}
