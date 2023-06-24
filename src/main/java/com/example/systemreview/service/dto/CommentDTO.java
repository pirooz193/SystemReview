package com.example.systemreview.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {
    @JsonProperty(value = "is")
    private Long id;
    @JsonProperty(value = "content")
    private String content;
    @JsonProperty(value = "isApproved")
    @JsonIgnore
    private boolean isApproved;
    @JsonProperty(value = "product")
    @JsonIgnore
    private ProductDTO product;
    @JsonProperty(value = "user")
    @JsonIgnore
    private UserDTO user;

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", isApproved=" + isApproved +
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
