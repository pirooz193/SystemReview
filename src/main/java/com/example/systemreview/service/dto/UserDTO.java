package com.example.systemreview.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "password")
    private String password;
    @JsonProperty(value = "purchasedProducts")
    private List<ProductDTO> purchasedProducts;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", purchasedProducts=" + purchasedProducts +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ProductDTO> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<ProductDTO> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }
}
