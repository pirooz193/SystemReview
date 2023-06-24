package com.example.systemreview.repository;

import com.example.systemreview.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    //    Float calculateAverageScoreByProductId(Long productId);
    @Query("SELECT AVG(v.score) FROM Vote v WHERE v.product.id = :productId and v.approvalStatus = :approvalStatus")
    Float findAverageScoreByProductId(@Param("productId") Long productId, @Param("approvalStatus") Boolean approvalStatus);
}
