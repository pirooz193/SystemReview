package com.example.systemreview.domain;

import com.example.systemreview.domain.enums.CommentingRule;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "commenting_rule")
    private CommentingRule commentingRule;

    @ManyToMany(mappedBy = "purchasedProducts")
    private List<User> buyers;


    public boolean isCommentingAllowed(User user) {
        return commentingRule != CommentingRule.BUYERS_ONLY || buyers.contains(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isPresentable == product.isPresentable && Objects.equals(id, product.id) && Objects.equals(name, product.name) && commentingRule == product.commentingRule && Objects.equals(buyers, product.buyers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isPresentable, commentingRule, buyers);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isPresentable=" + isPresentable +
                ", commentingRule=" + commentingRule +
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

    public CommentingRule getCommentingRule() {
        return commentingRule;
    }

    public void setCommentingRule(CommentingRule commentingRule) {
        this.commentingRule = commentingRule;
    }

    public List<User> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<User> buyers) {
        this.buyers = buyers;
    }
}
