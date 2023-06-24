package com.example.systemreview.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoteDTO {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "score")
    private int score;
    @JsonProperty(value = "approvalStatus")
    private boolean approvalStatus;
    @JsonProperty(value = "productId")
    private Long productId;
    @JsonProperty(value = "userId")
    private Long userId;

    @Override
    public String toString() {
        return "VoteDTO{" +
                "id=" + id +
                ", score=" + score +
                ", approvalStatus=" + approvalStatus +
                ", productId=" + productId +
                ", userId=" + userId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
