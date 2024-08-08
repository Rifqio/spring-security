package com.rifqio.springsecurity.commons.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ErrorResponse<E> extends ApiResponse {
    private E errors;

    public ErrorResponse(boolean success, String message, E errors) {
        super(success, message);
        this.errors = errors;
    }

    public static <E> ErrorResponse<E> badRequest(String message, E errors) {
        return new ErrorResponse<>(false, message, errors);
    }

    public static ErrorResponse<String> internalServerError() {
        return new ErrorResponse<>(false, "Internal Server Error", null);
    }

    public static ErrorResponse<String> notFound() {
        return new ErrorResponse<>(false, "Resource not found", null);
    }

    public static ErrorResponse<String> notFound(String message) {
        return new ErrorResponse<>(false, message, null);
    }

    public static ErrorResponse<String> unauthorized() {
        return new ErrorResponse<>(false, "Unauthorized", null);
    }

    public static <E> ErrorResponse<E> customError(String message, E errors) {
        return new ErrorResponse<>(false, message, errors);
    }
}
