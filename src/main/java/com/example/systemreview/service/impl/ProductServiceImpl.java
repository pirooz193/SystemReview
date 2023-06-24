package com.example.systemreview.service.impl;

import com.example.systemreview.domain.Product;
import com.example.systemreview.domain.constants.Constants;
import com.example.systemreview.repository.ProductRepository;
import com.example.systemreview.service.ProductService;
import com.example.systemreview.service.dto.ProductDTO;
import com.example.systemreview.service.mapper.ProductMapper;
import com.example.systemreview.web.error.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO updateProductDetails(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new NotFoundException(Constants.PRODUCT + "{" + productDTO.getId() + "}"));
        product = productMapper.updateProductDetailsFromDto(productDTO, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toDTO(updatedProduct);
    }

    @Override
    public ProductDTO getRequiredProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(Constants.PRODUCT + "{" + productId + "}"));
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }
}
