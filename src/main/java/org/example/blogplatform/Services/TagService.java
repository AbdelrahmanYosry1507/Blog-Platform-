package org.example.blogplatform.Services;

import org.example.blogplatform.domain.dtos.CreateTagsRequest;
import org.example.blogplatform.domain.entities.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {
    List<Tag> getTags();
    List<Tag> createTag(Set<String> tagNames);
}
