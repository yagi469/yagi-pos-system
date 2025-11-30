package com.yagi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yagi.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByStoreAdminId(Long adminId);

    java.util.List<Store> findAllByStoreAdminId(Long adminId);
}
