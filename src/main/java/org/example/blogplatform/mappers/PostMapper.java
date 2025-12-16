package org.example.blogplatform.mappers;

import org.example.blogplatform.domain.CreatePostRequest;
import org.example.blogplatform.domain.UpdatePostRequest;
import org.example.blogplatform.domain.dtos.CreatePostRequestDto;
import org.example.blogplatform.domain.dtos.PostDto;
import org.example.blogplatform.domain.dtos.UpdatePostRequestDto;
import org.example.blogplatform.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author",source = "author")
    @Mapping(target = "category",source = "category")
    @Mapping(target = "tags",source = "tags")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto createPostRequestDto);
    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto updatePostRequestDto);

}
