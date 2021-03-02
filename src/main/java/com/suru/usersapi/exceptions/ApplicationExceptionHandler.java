package com.suru.usersapi.exceptions;

import com.suru.usersapi.domain.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleExceptions(Exception exception) {
        LOGGER.error("Something went wrong!", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Bad Request", exception));
    }
}
