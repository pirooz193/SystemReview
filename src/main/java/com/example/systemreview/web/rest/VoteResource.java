package com.example.systemreview.web.rest;

import com.example.systemreview.service.VoteService;
import com.example.systemreview.service.dto.VoteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/vote")
public class VoteResource {

    private final VoteService voteService;

    public VoteResource(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<VoteDTO> createVote(@RequestBody VoteDTO voteDTO) {
        VoteDTO savedVote = voteService.saveVote(voteDTO);
        return ResponseEntity.created(URI.create("/api/vote")).body(savedVote);
    }
}
