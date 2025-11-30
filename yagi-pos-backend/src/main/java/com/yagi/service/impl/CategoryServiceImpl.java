package com.yagi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yagi.domain.UserRole;
import com.yagi.exception.CategoryNotFoundException;
import com.yagi.exception.StoreNotFoundException;
import com.yagi.mapper.CategoryMapper;
import com.yagi.model.Category;
import com.yagi.model.Store;
import com.yagi.model.User;
import com.yagi.payload.dto.CategoryDto;
import com.yagi.repository.CategoryRepository;
import com.yagi.repository.StoreRepository;
import com.yagi.service.CategoryService;
import com.yagi.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception {
        User user = userService.getCurrentUser();
        Store store = storeRepository.findById(categoryDto.getStoreId()).orElseThrow(
                () -> new StoreNotFoundException("Store not found with id: " + categoryDto.getStoreId()));
        Category category = Category.builder()
                .name(categoryDto.getName())
                .store(store)
                .build();
        checkAuthority(user, store);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toDTO(savedCategory);
    }

    @Override
    public List<CategoryDto> getCategoriesByStore(Long storeId) {
        return categoryRepository.findByStoreId(storeId)
                .stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category not exist"));

        User user = userService.getCurrentUser();
        category.setName(categoryDto.getName());
        checkAuthority(user, category.getStore());
        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapper.toDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category not exist"));

        User user = userService.getCurrentUser();
        checkAuthority(user, category.getStore());
        categoryRepository.delete(category);
    }

    private void checkAuthority(User user, Store store) throws Exception {
        boolean isAdmin = UserRole.ROLE_STORE_ADMIN.equals(user.getRole());
        boolean isManager = UserRole.ROLE_STORE_MANAGER.equals(user.getRole());
        boolean isSameStore = user.equals(store.getStoreAdmin());
        if (!(isAdmin && isSameStore) && !isManager) {
            throw new Exception("You don't have permission to manage this category");
        }
    }
}
