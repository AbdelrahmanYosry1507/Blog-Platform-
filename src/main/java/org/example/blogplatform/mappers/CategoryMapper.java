package org.example.blogplatform.mappers;

import org.example.blogplatform.domain.PostStatus;
import org.example.blogplatform.domain.dtos.CategoryDto;
import org.example.blogplatform.domain.dtos.CreateCategoryRequest;
import org.example.blogplatform.domain.entities.Category;
import org.example.blogplatform.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    @Mapping(target = "postCount",source = "posts", qualifiedByName = "calculatedPostCount")
    CategoryDto toDto(Category category);
    Category toEntity(CreateCategoryRequest createCategoryRequest);
    @Named("calculatedPostCount")
    default Long calculatedPostCount(List<Post> posts) {
        if(posts == null) {
            return 0L;
        }
        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
