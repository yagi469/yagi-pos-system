package com.yagi.service;

import java.util.List;

import com.yagi.model.User;
import com.yagi.payload.dto.ProductDto;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto, User user) throws Exception;

    ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception;

    void deleteProduct(Long id, User user) throws Exception;

    List<ProductDto> getProductsByStoreId(Long storeId);

    List<ProductDto> searchByKeyword(Long storeId, String keyword);
}
