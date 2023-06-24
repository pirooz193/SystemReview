package com.example.systemreview.web.rest;

import com.example.systemreview.service.ProductService;
import com.example.systemreview.service.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/product")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product-details/{productId}")
    public ResponseEntity<ProductDTO> getRequiredProductDetails(@PathVariable Long productId) {
        ProductDTO requiredProduct = productService.getRequiredProduct(productId);
        return ResponseEntity.ok(requiredProduct);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO savedProduct = productService.createProduct(productDTO);
        return ResponseEntity.created(URI.create("/api/product")).body(savedProduct);
    }

    @PatchMapping("/update-details")
    public ResponseEntity<ProductDTO> updateProductDetails(@RequestBody ProductDTO productDto) {
        ProductDTO updatedProduct = productService.updateProductDetails(productDto);
        return ResponseEntity.ok(updatedProduct);
    }


}
