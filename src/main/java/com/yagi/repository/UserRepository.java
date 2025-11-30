package com.yagi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yagi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    java.util.List<User> findByStoreId(Long storeId);
}
