package com.suru.usersapi.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private List<String> details;

    public ErrorResponse() {
        this.details = new ArrayList<>();
    }

    public ErrorResponse(String message, Exception exception) {
        this();
        this.message = message;
        this.details.add(exception.getMessage());
    }
}
