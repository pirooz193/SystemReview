package com.example.systemreview.service;

import com.example.systemreview.service.dto.ProductDTO;

public interface ProductService {
    ProductDTO updateProductDetails(ProductDTO productDTO);

    ProductDTO getRequiredProduct(Long productId);

    ProductDTO createProduct(ProductDTO productDTO);
}
