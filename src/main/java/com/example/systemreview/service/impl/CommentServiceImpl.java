package com.example.systemreview.service.impl;

import com.example.systemreview.domain.Comment;
import com.example.systemreview.domain.Product;
import com.example.systemreview.domain.User;
import com.example.systemreview.domain.constants.Constants;
import com.example.systemreview.repository.CommentRepository;
import com.example.systemreview.repository.ProductRepository;
import com.example.systemreview.repository.UserRepository;
import com.example.systemreview.service.CommentService;
import com.example.systemreview.service.dto.CommentDTO;
import com.example.systemreview.service.mapper.CommentMapper;
import com.example.systemreview.web.error.NotFoundException;
import com.example.systemreview.web.error.PermissionDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, ProductRepository productRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = commentMapper.toEntity(commentDTO);
        Product product = productRepository.findById(commentDTO.getProductId())
                .orElseThrow(() -> new NotFoundException(Constants.PRODUCT + "{" + commentDTO.getProductId() + "}"));
        User user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new NotFoundException(Constants.USER + "{" + commentDTO.getUserId() + "}"));
        if (product.isCommentingAllowed(user)) {
            comment.setUser(user);
            comment.setProduct(product);
            commentRepository.save(comment);
        } else throw new PermissionDeniedException(Constants.USER + "{" + user.getUsername() + "}");
        return null;
    }

    @Override
    public List<CommentDTO> getRequiredProductComments(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(Constants.PRODUCT + "{" + productId + "}"));
        List<Comment> requiredProductComments = commentRepository.findAllByProduct(product);
        return commentMapper.toDTOList(requiredProductComments);
    }
}
