package com.example.systemreview.web.rest;

import com.example.systemreview.service.VoteService;
import com.example.systemreview.service.dto.VoteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/approve/{voteId}")
    public ResponseEntity<VoteDTO> approveVote(@PathVariable Long voteId) {
        VoteDTO approvedVote = voteService.approveVote(voteId);
        return ResponseEntity.ok(approvedVote);
    }

}
