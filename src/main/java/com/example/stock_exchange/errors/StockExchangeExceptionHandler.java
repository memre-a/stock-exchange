package com.example.stock_exchange.errors;

import jakarta.persistence.OptimisticLockException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class StockExchangeExceptionHandler {

    @ExceptionHandler(OptimisticLockException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ErrorResponse handleOptimisticLockException(OptimisticLockException ex, WebRequest req) {
        String msg = "Update conflict: Stock Exchange was modified by another transaction.";
        return new ErrorResponse(msg, HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleNotFoundException(NotFoundException ex, WebRequest req) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleControllerValidation(MethodArgumentNotValidException ex, WebRequest req) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ErrorResponse(errors, HttpStatus.BAD_REQUEST.value());
    }

    public static class ErrorResponse {
        private Object message;
        private int status;

        public ErrorResponse(Object message, int status) {
            this.message = message;
            this.status = status;
        }

        public Object getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
