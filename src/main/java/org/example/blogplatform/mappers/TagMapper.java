package org.example.blogplatform.mappers;

import org.example.blogplatform.domain.PostStatus;
import org.example.blogplatform.domain.dtos.TagResponse;
import org.example.blogplatform.domain.entities.Post;
import org.example.blogplatform.domain.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(target = "postCount", source = "posts",qualifiedByName = "calculatePostCount")
    TagResponse toTagResponse(Tag tag);
    @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> posts) {
        if(posts==null){
            return 0;
        }
        return Math.toIntExact(posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count());

    }
}
