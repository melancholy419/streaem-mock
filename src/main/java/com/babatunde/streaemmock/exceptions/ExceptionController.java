package com.babatunde.streaemmock.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = { InvalidFetchRequestException.class })
    public ResponseEntity<ErrorResponse> resolveInvalidFetchRequestException(
            InvalidFetchRequestException ex,HttpServletRequest request) {
        log.error("Exception occurred while trying to process request: {} with message: {}", request.getRequestURI(),
                ex.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(UUID.randomUUID().toString(), ex.getMessage()));

    }

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<ErrorResponse> resolveResourceNotFoundException(
            ResourceNotFoundException ex,HttpServletRequest request) {
        log.error("Exception occurred while trying to process request: {} with message: {}", request.getRequestURI(),
                ex.getMessage());
        return ResponseEntity.notFound().build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> resolveNoMethodException(MethodArgumentNotValidException ex,HttpServletRequest request
                                                                 ) {
        ErrorResponse errorResponse = new ErrorResponse(UUID.randomUUID().toString(), ex.getBody().getDetail());
        log.error("Exception occurred while trying to process request: {} with message: {}", request.getRequestURI(),
                ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> resolveMessageNotReadableException(HttpMessageNotReadableException ex,HttpServletRequest request
                                                                 ) {
        ErrorResponse errorResponse = new ErrorResponse(UUID.randomUUID().toString(), ex.getMessage());
        log.error("Exception occurred while trying to process request: {} with message: {}", request.getRequestURI(),
                ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
