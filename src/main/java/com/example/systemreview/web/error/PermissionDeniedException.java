package com.example.systemreview.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class PermissionDeniedException extends HttpClientErrorException {
    public PermissionDeniedException(String text) {
        super(HttpStatus.FORBIDDEN, text + " - Do not have permission");
    }
}
