package com.bank.api;

import com.bank.domain.exception.account.AccountNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice(basePackages = "com.bank.api")
public class ApiExceptionHandler {

    // =========================
    // DOMAIN / BUSINESS ERRORS
    // =========================

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleAccountNotFound(
            AccountNotFoundException e
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", e.getMessage()));
    }

    // =========================
    // TECHNICAL / UNEXPECTED
    // =========================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneric(
            Exception e,
            HttpServletRequest request
    ) {
        if (request.getRequestURI().startsWith("/actuator")) {
            throw new RuntimeException(e);
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Internal error"));
    }
}
