package com.yagi.service;

import java.util.List;

import com.yagi.payload.dto.CategoryDto;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto) throws Exception;

    List<CategoryDto> getCategoriesByStore(Long storeId);

    CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws Exception;

    void deleteCategory(Long id) throws Exception;
}
