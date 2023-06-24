package com.example.systemreview.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class VotingIsClosedException extends HttpClientErrorException {
    public VotingIsClosedException() {
        super(HttpStatus.FORBIDDEN, "Voting is closed");
    }
}
