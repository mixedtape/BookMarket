package com.sparta.market.product;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    @Transactional
    public List<Product> getAllProducts() {
            return productRepository.findAll();
    }
    @Transactional
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("해당 상품은 존재하지 않습니다!"));
    }
    @Transactional
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    @Transactional
    public Product updateProduct(Long productId, Product product) {
        getProductById(productId);
        if(product != null){
            return productRepository.save(product);
        } else{
            throw new IllegalArgumentException("해당 상품은 존재하지 않습니다!");
        }
    }
    @Transactional
    public void deleteProduct(Long productId) {
        getProductById(productId);
        if(productId!=null)
        {
            productRepository.deleteById(productId);
        } else {
            throw new IllegalArgumentException("해당 상품은 없습니다!");
        }
    }
}
