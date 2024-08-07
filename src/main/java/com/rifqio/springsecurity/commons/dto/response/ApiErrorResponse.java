package com.rifqio.springsecurity.commons.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse<E> {
    private boolean success = false;
    private String message;
    private E errors;

    public static <E> ApiErrorResponse<E> badRequest(String message, E errors) {
        return new ApiErrorResponse<>(false, message, errors);
    }

    public static ApiErrorResponse<String> internalServerError() {
        return new ApiErrorResponse<>(false, "Internal Server Error", null);
    }

    public static ApiErrorResponse<String> notFound() {
        return new ApiErrorResponse<>(false, "Resource not found", null);
    }

    public static ApiErrorResponse<String> unauthorized() {
        return new ApiErrorResponse<>(false, "Unauthorized", null);
    }

    public static <E> ApiErrorResponse<E> customError(String message, E errors) {
        return new ApiErrorResponse<>(false, message, errors);
    }
}
