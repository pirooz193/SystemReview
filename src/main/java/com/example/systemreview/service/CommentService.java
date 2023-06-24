package com.example.systemreview.service;

import com.example.systemreview.service.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO);

    List<CommentDTO> getRequiredProductComments(Long productId);

    CommentDTO approveComment(Long commentId);
}
