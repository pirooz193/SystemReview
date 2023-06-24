package com.example.systemreview.domain;

import com.example.systemreview.domain.enums.ReactionRules;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 300)
    private String name;

    @Column(name = "isPresentable")
    private boolean isPresentable;

    @Column(name = "commenting_enabled")
    private boolean commentingEnabled;

    @Column(name = "voting_enabled")
    private boolean votingEnabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "reaction_rule")
    private ReactionRules reactionRules;

    @ManyToMany(mappedBy = "purchasedProducts")
    private List<User> buyers;


    public boolean isCommentingAllowed(User user) {
        return reactionRules != ReactionRules.BUYERS_ONLY || buyers.contains(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isPresentable == product.isPresentable && commentingEnabled == product.commentingEnabled && votingEnabled == product.votingEnabled && Objects.equals(id, product.id) && Objects.equals(name, product.name) && reactionRules == product.reactionRules && Objects.equals(buyers, product.buyers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isPresentable, commentingEnabled, votingEnabled, reactionRules, buyers);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isPresentable=" + isPresentable +
                ", commentingEnabled=" + commentingEnabled +
                ", votingEnabled=" + votingEnabled +
                ", reactionRules=" + reactionRules +
                ", buyers=" + buyers +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReactionRules getCommentingRule() {
        return reactionRules;
    }

    public void setCommentingRule(ReactionRules reactionRules) {
        this.reactionRules = reactionRules;
    }

    public List<User> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<User> buyers) {
        this.buyers = buyers;
    }

    public boolean isPresentable() {
        return isPresentable;
    }

    public void setPresentable(boolean presentable) {
        isPresentable = presentable;
    }

    public boolean isCommentingEnabled() {
        return commentingEnabled;
    }

    public void setCommentingEnabled(boolean commentingEnabled) {
        this.commentingEnabled = commentingEnabled;
    }

    public boolean isVotingEnabled() {
        return votingEnabled;
    }

    public void setVotingEnabled(boolean votingEnabled) {
        this.votingEnabled = votingEnabled;
    }

    public ReactionRules getReactionRules() {
        return reactionRules;
    }

    public void setReactionRules(ReactionRules reactionRules) {
        this.reactionRules = reactionRules;
    }
}
