package com.rifqio.springsecurity.commons.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.Random;

@Data
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private String transaction_id;

    public ApiResponse(boolean success, String message) {
        this.transaction_id = GenerateTransactionId();
        this.success = success;
        this.message = message;
    }

    private String GenerateTransactionId() {
        return new Date().getTime() + "-" + new Random().nextInt(1000);
    }
}
