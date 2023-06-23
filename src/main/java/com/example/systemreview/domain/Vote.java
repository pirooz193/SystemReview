package com.example.systemreview.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score")
    private int score;

    @Column(name = "approval_status")
    private boolean approvalStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return score == vote.score && approvalStatus == vote.approvalStatus && Objects.equals(id, vote.id) && Objects.equals(product, vote.product) && Objects.equals(user, vote.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, approvalStatus, product, user);
    }

    @Override
    public String toString() {
        return "Vote{" +
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
