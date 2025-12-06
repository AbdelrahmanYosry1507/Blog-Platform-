package org.example.blogplatform.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {

    private int statues;
    private String message;
    private List<FieldErrors> errors;



    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FieldErrors {
        private String field;
        private String message;
    }


}
