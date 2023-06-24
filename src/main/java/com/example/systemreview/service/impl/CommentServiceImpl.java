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
import com.example.systemreview.web.error.CommentingIsCloseException;
import com.example.systemreview.web.error.NotFoundException;
import com.example.systemreview.web.error.PermissionDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    /**
     * Creates a new comment based on the provided CommentDTO.
     *
     * @param commentDTO The CommentDTO containing the data for the new comment.
     * @return The CommentDTO representing the created comment.
     * @throws NotFoundException          if the associated product or user is not found.
     * @throws PermissionDeniedException  if the commenting is not allowed for the user.
     * @throws CommentingIsCloseException if commenting is disabled for the product.
     */
    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = commentMapper.toEntity(commentDTO);
        Product product = productRepository.findById(commentDTO.getProductId())
                .orElseThrow(() -> new NotFoundException(Constants.PRODUCT + "{" + commentDTO.getProductId() + "}"));
        User user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new NotFoundException(Constants.USER + "{" + commentDTO.getUserId() + "}"));
        if (product.isCommentingEnabled()) {
            if (product.isCommentingAllowed(user)) {
                comment.setUser(user);
                comment.setProduct(product);
                comment.setCreatedDateTime(LocalDateTime.now());
                commentRepository.save(comment);
            } else throw new PermissionDeniedException(Constants.USER + "{" + user.getUsername() + "}");
        } else throw new CommentingIsCloseException();
        return commentMapper.toDTO(comment);
    }

    /**
     * Retrieves the comments associated with the specified product.
     *
     * @param productId The ID of the product for which to retrieve the comments.
     * @return A list of CommentDTO objects representing the comments of the specified product.
     * @throws NotFoundException if the product with the specified ID is not found.
     */
    @Override
    public List<CommentDTO> getRequiredProductComments(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(Constants.PRODUCT + "{" + productId + "}"));
        List<Comment> requiredProductComments = commentRepository.findAllByProduct(product);
        return commentMapper.toDTOList(requiredProductComments);
    }

    /**
     * Approves a comment with the specified comment ID.
     *
     * @param commentId The ID of the comment to be approved.
     * @return The CommentDTO representing the approved comment.
     * @throws NotFoundException if the comment with the specified ID is not found.
     */
    @Override
    public CommentDTO approveComment(Long commentId) {
        Comment requiredComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(Constants.COMMENT + "{" + commentId + "}"));
        requiredComment.setApproved(true);
        Comment savedComment = commentRepository.save(requiredComment);
        return commentMapper.toDTO(savedComment);
    }
}
