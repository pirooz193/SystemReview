package com.example.systemreview.web.rest;

import com.example.systemreview.service.CommentService;
import com.example.systemreview.service.dto.CommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentResource {

    private final CommentService commentService;

    public CommentResource(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO savedComment = commentService.createComment(commentDTO);
        return ResponseEntity.created(URI.create("/api/comment")).body(savedComment);
    }

    @GetMapping("{productId}")
    public ResponseEntity<List<CommentDTO>> getRequiredProductComments(@PathVariable Long productId) {
        List<CommentDTO> comments = commentService.getRequiredProductComments(productId);
        return ResponseEntity.ok().body(comments);
    }

}
