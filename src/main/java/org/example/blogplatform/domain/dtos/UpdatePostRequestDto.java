package org.example.blogplatform.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.blogplatform.domain.PostStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePostRequestDto {


    @NotNull(message = "Post id is required")
    private UUID id;

    @NotBlank(message = "title is required")
    @Size(min = 3, max = 100, message = "title must be between 3 and 100 characters")
    private String title;
    @NotBlank(message = "content is required")
    @Size(min = 3, max = 500, message = "Content must be between 3 and 500 characters")
    private String content;
    @NotNull(message = "category id is required")
    private UUID categoryId;

    @Builder.Default
    @Size(max = 10, message = "max 10 tags allowed")
    private Set<UUID> tagIds = new HashSet<>();

    @NotNull(message = "status must be not null")
    private PostStatus postStatus;
}
