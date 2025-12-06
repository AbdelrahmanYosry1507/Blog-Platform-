package org.example.blogplatform.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CategoryDto {
    private UUID id;
    private String name;
    private Long postCount;

}
