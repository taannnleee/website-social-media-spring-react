package org.example.socialmedia.authentication.dto.reponse;

import org.springframework.http.ResponseEntity;

public class ResponseData<T>  {
    private final int status;
    private final String message;
    private T data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public ResponseData(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseData(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
