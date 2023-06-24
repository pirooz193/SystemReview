package com.example.systemreview.service;

import com.example.systemreview.service.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO updateProductDetails(ProductDTO productDTO);

    ProductDTO getRequiredProduct(Long productId);

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();
}
