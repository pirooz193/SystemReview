package com.example.systemreview.service.mapper;

import com.example.systemreview.domain.Product;
import com.example.systemreview.service.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductDTO productDTO);

    ProductDTO toDTO(Product product);

    default Product updateProductDetailsFromDto(ProductDTO productDTO, Product product) {
        if (productDTO.getName() != null) product.setName(productDTO.getName());
        if (productDTO.getReactionRules() != null) product.setReactionRules(productDTO.getReactionRules());
        if (productDTO.getPresentable() != null) product.setPresentable(productDTO.getPresentable());
        if (productDTO.isCommentingEnabled() != null) product.setCommentingEnabled(productDTO.isCommentingEnabled());
        if (productDTO.isVotingEnabled() != null) product.setVotingEnabled(productDTO.isVotingEnabled());
        return product;
    }
}
