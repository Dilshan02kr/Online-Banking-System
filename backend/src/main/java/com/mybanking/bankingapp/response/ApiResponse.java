package com.mybanking.bankingapp.response;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private int status;
    private T data;
    private String message;
    private LocalDateTime timeStamp;

    public ApiResponse(T data, int status, String message){
        this.data = data;
        this.status = status;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(int status, String message){
        this.status = status;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
