package com.yagi.mapper;

import com.yagi.model.Store;
import com.yagi.model.User;
import com.yagi.payload.dto.StoreDto;

public class StoreMapper {

    public static StoreDto toDTO(Store store) {
        StoreDto storeDto = new StoreDto();
        storeDto.setId(store.getId());
        storeDto.setBrand(store.getBrand());
        storeDto.setStoreAdmin(UserMapper.toDTO(store.getStoreAdmin()));
        storeDto.setCreatedAt(store.getCreatedAt());
        storeDto.setUpdatedAt(store.getUpdatedAt());
        storeDto.setDescription(store.getDescription());
        storeDto.setStoreType(store.getStoreType());
        storeDto.setStatus(store.getStatus());
        storeDto.setContact(store.getContact());
        return storeDto;
    }

    public static Store toEntity(StoreDto storeDto, User storeAdmin) {
        Store store = new Store();
        // 新規作成時はIDを設定しない（自動生成される）
        // store.setId(storeDto.getId());
        store.setBrand(storeDto.getBrand());
        store.setStoreAdmin(storeAdmin);
        // createdAtとupdatedAtは@PrePersist/@PreUpdateで自動設定される
        // store.setCreatedAt(storeDto.getCreatedAt());
        // store.setUpdatedAt(storeDto.getUpdatedAt());
        store.setDescription(storeDto.getDescription());
        store.setStoreType(storeDto.getStoreType());
        // statusは@PrePersistで自動設定される（PENDING）
        // store.setStatus(storeDto.getStatus());

        // contactがnullの場合は空のオブジェクトを設定
        if (storeDto.getContact() != null) {
            store.setContact(storeDto.getContact());
        }
        return store;
    }
}
