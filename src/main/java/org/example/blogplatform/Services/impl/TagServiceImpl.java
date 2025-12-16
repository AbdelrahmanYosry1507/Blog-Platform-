package org.example.blogplatform.Services.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.blogplatform.Services.TagService;
import org.example.blogplatform.domain.entities.Tag;
import org.example.blogplatform.repostries.TagRepository;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Transactional
    @Override
    public void deleteTag(UUID id) {

        tagRepository.findById(id).ifPresent(
                tag->{
                    if(!tag.getPosts().isEmpty()){
                        throw new IllegalArgumentException("Tag is associated with posts");
                    }
                        tagRepository.deleteById(id);

                }

        );
    }

    @Override
    public Tag getTagById(UUID tagId) {
        return tagRepository.findById(tagId).orElseThrow(() -> new EntityNotFoundException("Tag not found"));
    }

    @Override
    public List<Tag> getTagByIds(Set<UUID> ids) {
        List<Tag> foundTags = tagRepository.findAllById(ids);
        if (foundTags.size() != ids.size()){
            throw new EntityNotFoundException("not all specfied ids exist");
        }
        return foundTags;
    }
}
