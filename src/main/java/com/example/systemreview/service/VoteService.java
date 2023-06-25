package com.example.systemreview.service;

import com.example.systemreview.service.dto.VoteDTO;

public interface VoteService {
    VoteDTO saveVote(VoteDTO voteDTO);

    VoteDTO approveVote(Long voteId);
}
