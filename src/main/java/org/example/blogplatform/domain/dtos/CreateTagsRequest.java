package org.example.blogplatform.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTagsRequest {

    @NotEmpty(message = "Names cannot be empty")
    @Size(max = 10, message = "Names cannot exceed {max} characters")
    private Set<@Size(max = 50, message = "Name cannot exceed {max} characters") @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Name can only contain letters, numbers, and underscores") String> names;
}
