package com.example.systemreview.service.impl;

import com.example.systemreview.domain.Product;
import com.example.systemreview.domain.User;
import com.example.systemreview.domain.Vote;
import com.example.systemreview.domain.constants.Constants;
import com.example.systemreview.repository.ProductRepository;
import com.example.systemreview.repository.UserRepository;
import com.example.systemreview.repository.VoteRepository;
import com.example.systemreview.service.VoteService;
import com.example.systemreview.service.dto.VoteDTO;
import com.example.systemreview.service.mapper.VoteMapper;
import com.example.systemreview.web.error.NotFoundException;
import com.example.systemreview.web.error.VotingIsClosedException;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public VoteServiceImpl(VoteRepository voteRepository, VoteMapper voteMapper, ProductRepository productRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.voteMapper = voteMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    /**
     * Saves a vote based on the provided VoteDTO.
     *
     * @param voteDTO The VoteDTO containing the data for the vote.
     * @return The VoteDTO representing the saved vote.
     * @throws NotFoundException       if the product or user with the specified ID is not found.
     * @throws VotingIsClosedException if voting is closed for the associated product.
     */
    @Override
    public VoteDTO saveVote(VoteDTO voteDTO) {
        Vote vote = voteMapper.toEntity(voteDTO);
        Product product = productRepository.findById(voteDTO.getProductId())
                .orElseThrow(() -> new NotFoundException(Constants.PRODUCT + "{" + voteDTO.getProductId() + "}"));
        User user = userRepository.findById(voteDTO.getUserId())
                .orElseThrow(() -> new NotFoundException(Constants.USER + "{" + voteDTO.getUserId() + "}"));
        if (product.isVotingEnabled()) {
            vote.setUser(user);
            vote.setProduct(product);
            voteRepository.save(vote);
        } else throw new VotingIsClosedException();
        return voteMapper.toDTO(vote);
    }

    /**
     * Approves a vote with the specified vote ID.
     *
     * @param voteId The ID of the vote to be approved.
     * @return The VoteDTO representing the approved vote.
     * @throws NotFoundException if the vote with the specified ID is not found.
     */
    @Override
    public VoteDTO approveVote(Long voteId) {
        Vote requiredVote = voteRepository.findById(voteId)
                .orElseThrow(() -> new NotFoundException(Constants.VOTE + "{" + voteId + "}"));
        requiredVote.setApprovalStatus(true);
        Vote savedVote = voteRepository.save(requiredVote);
        return voteMapper.toDTO(savedVote);
    }
}
