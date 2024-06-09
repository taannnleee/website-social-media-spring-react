package org.example.socialmedia.authentication.dto.reponse;

public class ResponseError extends ResponseData{
    public ResponseError(int status, String message) {
        super(status, message);
    }
}
