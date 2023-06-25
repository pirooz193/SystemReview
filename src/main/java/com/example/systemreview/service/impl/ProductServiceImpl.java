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


    /**
     * Updates the details of a product based on the provided ProductDTO.
     *
     * @param productDTO The ProductDTO containing the updated product details.
     * @return The ProductDTO representing the updated product.
     * @throws NotFoundException if the product with the specified ID is not found.
     */
    @Override
    public ProductDTO updateProductDetails(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new NotFoundException(Constants.PRODUCT + "{" + productDTO.getId() + "}"));
        product = productMapper.updateProductDetailsFromDto(productDTO, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toDTO(updatedProduct);
    }

    /**
     * Retrieves the product with the specified product ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The ProductDTO representing the retrieved product.
     * @throws NotFoundException if the product with the specified ID is not found.
     */
    @Override
    public ProductDTO getRequiredProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(Constants.PRODUCT + "{" + productId + "}"));
        return getProductDTOExtraDetails(productMapper.toDTO(product));
    }

    /**
     * Retrieves additional details for a ProductDTO object.
     *
     * @param productDTO The ProductDTO object to retrieve additional details for.
     * @return The updated ProductDTO object with extra details.
     */
    private ProductDTO getProductDTOExtraDetails(ProductDTO productDTO) {
        List<Comment> comments = commentRepository.findTop3ByProductIdAndIsApprovedOrderByCreatedDateTimeDesc(productDTO.getId(), true);
        List<CommentDTO> commentDTOS = commentMapper.toDTOList(comments);
        productDTO.setLastThreeCommentDTOS(commentDTOS);
        productDTO.setNumOfComments(commentRepository.countAllByProduct(productMapper.toEntity(productDTO)));
        Float averageScore = voteRepository.findAverageScoreByProductId(productDTO.getId(), true);
        productDTO.setAverageScore(averageScore == null ? 0 : averageScore);
        return productDTO;
    }

    /**
     * Creates a new product based on the provided ProductDTO.
     *
     * @param productDTO The ProductDTO containing the data for the new product.
     * @return The ProductDTO representing the created product.
     */
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }


    /**
     * Retrieves all products that are presentable along with additional information.
     *
     * @return A list of ProductDTO objects representing the retrieved products.
     */
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAllByIsPresentable(true).stream()
                .map(product -> {
                    ProductDTO productDTO = productMapper.toDTO(product);
                    return getProductDTOExtraDetails(productDTO);
                })
                .collect(Collectors.toList());
    }


    /**
     * Allows a user to purchase a product with the specified product ID.
     *
     * @param productId The ID of the product to be purchased.
     * @param userId    The ID of the user making the purchase.
     * @return The ProductDTO representing the purchased product.
     * @throws NotFoundException if the product or user with the specified ID is not found.
     */
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

    /**
     * Changes the commenting status of a product with the specified product ID.
     * If the commenting is currently enabled, it will be disabled, and vice versa.
     *
     * @param productId The ID of the product for which to change the commenting status.
     * @return The ProductDTO representing the product with the updated commenting status.
     * @throws NotFoundException if the product with the specified ID is not found.
     */
    @Override
    public ProductDTO changeCommentingStatus(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(Constants.PRODUCT + "{" + productId + "}"));
        product.setCommentingEnabled(!product.isCommentingEnabled());
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }
}
