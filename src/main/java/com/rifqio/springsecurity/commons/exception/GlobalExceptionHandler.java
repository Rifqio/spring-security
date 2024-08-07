package com.rifqio.springsecurity.commons.exception;

import com.rifqio.springsecurity.commons.dto.response.ApiErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiErrorResponse<String>> handleInternalServerError(RuntimeException ex, WebRequest request) {
        logger.severe(ex.getMessage());
        return ResponseEntity.status(500).body(ApiErrorResponse.internalServerError());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiErrorResponse<Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        return ResponseEntity.status(400).body(ApiErrorResponse.badRequest("Validation error", errors));
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ApiErrorResponse<String>> handleNotFoundException(RuntimeException ex, WebRequest request) {
        logger.severe(ex.getMessage());
        return ResponseEntity.status(404).body(ApiErrorResponse.notFound());
    }

    public static ResponseEntity<ApiErrorResponse<String>> handleUnauthorizedException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(401).body(ApiErrorResponse.unauthorized());
    }
}
