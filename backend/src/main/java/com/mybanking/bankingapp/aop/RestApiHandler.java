package com.mybanking.bankingapp.aop;

import com.mybanking.bankingapp.exception.ShowUserException;
import com.mybanking.bankingapp.exception.TransactionException;
import com.mybanking.bankingapp.exception.UsedUsernameException;
import com.mybanking.bankingapp.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestApiHandler {

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ApiResponse<?>> handleException(TransactionException e){
        ApiResponse<?> response = new ApiResponse<>(0, e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShowUserException.class)
    public ResponseEntity<ApiResponse<?>> handleShowUserException(ShowUserException e){
        ApiResponse<?> response = new ApiResponse<>(0, e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsedUsernameException.class)
    public ResponseEntity<ApiResponse<?>> handleShowUserException(UsedUsernameException e){
        ApiResponse<?> response = new ApiResponse<>(0, e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
