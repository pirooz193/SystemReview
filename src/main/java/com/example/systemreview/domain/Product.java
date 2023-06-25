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

    @Column(name = "is_presentable")
    private Boolean isPresentable;

    @Column(name = "commenting_enabled")
    private Boolean commentingEnabled;

    @Column(name = "voting_enabled")
    private Boolean votingEnabled;

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
                (buyers != null ? ", buyers=" + buyers.size() + " users" : "") +
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

    public List<User> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<User> buyers) {
        this.buyers = buyers;
    }

    public Boolean isPresentable() {
        return isPresentable;
    }

    public void setPresentable(Boolean presentable) {
        isPresentable = presentable;
    }

    public Boolean isCommentingEnabled() {
        return commentingEnabled;
    }

    public void setCommentingEnabled(Boolean commentingEnabled) {
        this.commentingEnabled = commentingEnabled;
    }

    public Boolean isVotingEnabled() {
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
