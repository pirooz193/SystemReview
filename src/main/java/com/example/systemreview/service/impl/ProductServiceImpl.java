package com.example.systemreview.service.impl;

import com.example.systemreview.domain.Comment;
import com.example.systemreview.domain.Product;
import com.example.systemreview.domain.User;
import com.example.systemreview.domain.constants.Constants;
import com.example.systemreview.repository.CommentRepository;
import com.example.systemreview.repository.ProductRepository;
import com.example.systemreview.repository.UserRepository;
import com.example.systemreview.repository.VoteRepository;
import com.example.systemreview.service.ProductService;
import com.example.systemreview.service.dto.CommentDTO;
import com.example.systemreview.service.dto.ProductDTO;
import com.example.systemreview.service.mapper.CommentMapper;
import com.example.systemreview.service.mapper.ProductMapper;
import com.example.systemreview.web.error.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ProductMapper productMapper;

    private final VoteRepository voteRepository;

    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, CommentRepository commentRepository, CommentMapper commentMapper, ProductMapper productMapper, VoteRepository voteRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.productMapper = productMapper;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
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

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = productRepository.findAllByIsPresentable(true).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
        for (ProductDTO productDTO : products) {
            List<Comment> comments = commentRepository.findTop3ByProductIdAndIsApprovedOrderByCreatedDateTimeDesc(productDTO.getId(), true);
            List<CommentDTO> commentDTOS = commentMapper.toDTOList(comments);
            productDTO.setLastThreeCommentDTOS(commentDTOS);
//            productDTO.setNumOfComments(productDTO.);
            Float averageScore = voteRepository.findAverageScoreByProductId(productDTO.getId(), true);
            productDTO.setAverageScore(averageScore == null ? 0 : averageScore);
        }
        return products;
    }

    @Override
    public ProductDTO buyProduct(Long productId, Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(Constants.PRODUCT + "{" + productId + "}"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Constants.USER + "{" + userId + "}"));
        user.getPurchasedProducts().add(product);
        product.getBuyers().add(user);
        userRepository.save(user);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO changeCommentingStatus(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(Constants.PRODUCT + "{" + productId + "}"));
        product.setCommentingEnabled(!product.isCommentingEnabled());
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }
}
