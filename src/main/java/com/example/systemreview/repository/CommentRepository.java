package com.example.systemreview.repository;

import com.example.systemreview.domain.Comment;
import com.example.systemreview.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findTop3ByProductIdAndIsApprovedOrderByCreatedDateTimeDesc(Long productId, Boolean isApproved);

    List<Comment> findAllByProductAndIsApproved(Product product, Boolean approved);

    Integer countAllByProduct(Product product);
}
