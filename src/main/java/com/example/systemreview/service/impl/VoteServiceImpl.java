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
}
