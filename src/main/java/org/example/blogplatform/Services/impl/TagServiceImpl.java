package org.example.blogplatform.Services.impl;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.blogplatform.Services.TagService;
import org.example.blogplatform.domain.entities.Tag;
import org.example.blogplatform.repostries.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    @Override
    public List<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }
}
