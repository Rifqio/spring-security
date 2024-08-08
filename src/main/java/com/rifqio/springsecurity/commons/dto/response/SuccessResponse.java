package com.rifqio.springsecurity.commons.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class SuccessResponse<T> extends ApiResponse {
    private T data;

    public SuccessResponse(String message, T data) {
        super(true, message);
        this.data = data;
    }

    public static <T> SuccessResponse<T> success(String message, T data) {
        return new SuccessResponse<>(message, data);
    }

    public static <T> SuccessResponse<T> success(String message) {
        return new SuccessResponse<>(message, null);
    }
}
