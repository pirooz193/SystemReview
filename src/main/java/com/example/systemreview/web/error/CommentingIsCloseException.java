package com.example.systemreview.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class CommentingIsCloseException extends HttpClientErrorException {
    public CommentingIsCloseException() {
        super(HttpStatus.FORBIDDEN, "Commenting is closed");
    }
}
