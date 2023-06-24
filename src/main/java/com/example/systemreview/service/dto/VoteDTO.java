package com.example.systemreview.service.dto;

import com.example.systemreview.domain.Product;
import com.example.systemreview.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonProperty(value = "product")
    @JsonIgnore
    private Product product;
    @JsonProperty(value = "user")
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        return "VoteDTO{" +
                "id=" + id +
                ", score=" + score +
                ", approvalStatus=" + approvalStatus +
                ", product=" + product +
                ", user=" + user +
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
