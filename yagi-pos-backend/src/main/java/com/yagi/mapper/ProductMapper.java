package com.yagi.mapper;

import com.yagi.model.Category;
import com.yagi.model.Product;
import com.yagi.model.Store;
import com.yagi.payload.dto.ProductDto;

public class ProductMapper {

    public static ProductDto toDTO(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .category(CategoryMapper.toDTO(product.getCategory()))
                .storeId(product.getStore() != null ? product.getStore().getId() : null)
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static Product toEntity(
            ProductDto productDto, Store store, Category category) {
        return Product.builder()
                .name(productDto.getName())
                .brand(productDto.getBrand())
                .sku(productDto.getSku())
                .description(productDto.getDescription())
                .mrp(productDto.getMrp())
                .sellingPrice(productDto.getSellingPrice())
                .image(productDto.getImage())
                .store(store)
                .category(category)
                .build();
    }
}
