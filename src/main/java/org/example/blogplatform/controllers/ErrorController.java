package org.example.blogplatform.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.blogplatform.domain.dtos.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

@RestController
@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception e) {
        log.error("Exception occurred: ", e);
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .statues(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An error occurred while processing the request")
                .errors(List.of(ApiErrorResponse.FieldErrors.builder().field("error").message(e.getMessage()).build()))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException occurred: ", e);
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .statues(HttpStatus.BAD_REQUEST.value())
                .message("An error occurred while processing the request")
                .errors(List.of(ApiErrorResponse.FieldErrors.builder().field("error").message(e.getMessage()).build()))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalStateException(IllegalStateException e) {
        log.error("IllegalStateException occurred: ", e);
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .statues(HttpStatus.CONFLICT.value())
                .message("An error occurred while processing the request")
                .errors(List.of(ApiErrorResponse.FieldErrors.builder().field("error").message(e.getMessage()).build()))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
        log.error("BadCredentialsException occurred: ", e);
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .statues(HttpStatus.UNAUTHORIZED.value())
                .message("Invalid email or password")
                .errors(List.of(ApiErrorResponse.FieldErrors.builder().field("error").message(e.getMessage()).build()))
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

}
