package com.yagi.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yagi.mapper.ProductMapper;
import com.yagi.model.Product;
import com.yagi.model.Store;
import com.yagi.model.User;
import com.yagi.payload.dto.ProductDto;
import com.yagi.repository.ProductRepository;
import com.yagi.repository.StoreRepository;
import com.yagi.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
        Store store = storeRepository.findById(productDto.getStoreId())
                .orElseThrow(() -> new Exception("Store not found"));

        Product product = ProductMapper.toEntity(productDto, store);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found"));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        product.setImage(productDto.getImage());
        product.setMrp(productDto.getMrp());
        product.setSellingPrice(productDto.getSellingPrice());
        product.setBrand(productDto.getBrand());
        product.setUpdatedAt(LocalDateTime.now());
        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found"));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> getProductsByStoreId(Long storeId) {
        return productRepository.findByStoreId(storeId).stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByKeyword(Long storeId, String keyword) {
        return productRepository.searchByKeyword(storeId, keyword).stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}
