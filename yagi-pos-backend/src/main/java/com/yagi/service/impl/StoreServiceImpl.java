package com.yagi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yagi.domain.StoreStatus;
import com.yagi.exception.UserException;
import com.yagi.mapper.StoreMapper;
import com.yagi.model.Store;
import com.yagi.model.StoreContact;
import com.yagi.model.User;
import com.yagi.payload.dto.StoreDto;
import com.yagi.repository.StoreRepository;
import com.yagi.service.StoreService;
import com.yagi.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final UserService userService;
    private final com.yagi.repository.UserRepository userRepository;

    @Override
    @Transactional
    public StoreDto createStore(StoreDto storeDto, User user) {
        // 1. まずStoreを作成（Adminなしで保存してIDを確定させる）
        Store store = StoreMapper.toEntity(storeDto, null);
        Store savedStore = storeRepository.save(store);

        // 2. Adminをセットして更新
        User managedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserException("User not found"));
        savedStore.setStoreAdmin(managedUser);
        Store updatedStore = storeRepository.save(savedStore);

        return StoreMapper.toDTO(updatedStore);
    }

    @Override
    public StoreDto getStoreById(Long id) throws Exception {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new Exception("Store not found..."));
        return StoreMapper.toDTO(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
        return storeRepository.findAll()
                .stream()
                .map(StoreMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StoreDto> getStoreByAdmin() throws UserException {
        User admin = userService.getCurrentUser();
        if (admin == null) {
            throw new UserException("User not authenticated");
        }
        return storeRepository.findAllByStoreAdminId(admin.getId())
                .stream()
                .map(StoreMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws Exception {
        User currentUser = userService.getCurrentUser();

        // idパラメータを使って店舗を取得
        Store existing = storeRepository.findById(id)
                .orElseThrow(() -> new Exception("Store not found..."));

        // 権限チェック: 更新しようとしている店舗の管理者が現在のユーザーか確認
        if (!existing.getStoreAdmin().getId().equals(currentUser.getId())) {
            throw new UserException("You don't have permission to update this store");
        }

        existing.setBrand(storeDto.getBrand());
        existing.setDescription(storeDto.getDescription());
        if (storeDto.getStoreType() != null) {
            existing.setStoreType(storeDto.getStoreType());
        }
        if (storeDto.getContact() != null) {
            StoreContact contact = StoreContact.builder()
                    .address(storeDto.getContact().getAddress())
                    .email(storeDto.getContact().getEmail())
                    .phone(storeDto.getContact().getPhone())
                    .build();
            existing.setContact(contact);
        }
        existing.setStatus(storeDto.getStatus());
        Store updatedStore = storeRepository.save(existing);
        return StoreMapper.toDTO(updatedStore);
    }

    @Override
    public void deleteStore(Long id) throws Exception {
        User currentUser = userService.getCurrentUser();

        // idパラメータを使って店舗を取得
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new Exception("Store not found..."));

        // 権限チェック: 削除しようとしている店舗の管理者が現在のユーザーか確認
        if (!store.getStoreAdmin().getId().equals(currentUser.getId())) {
            throw new UserException("You don't have permission to delete this store");
        }

        // 店舗に関連付けられているユーザーの紐付けを解除
        List<User> employees = userRepository.findByStoreId(store.getId());
        for (User employee : employees) {
            employee.setStore(null);
            userRepository.save(employee);
        }

        storeRepository.delete(store);
    }

    @Override
    public StoreDto getStoreByEmployee() throws UserException {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new UserException("You don't have permission to access this store");
        }
        if (currentUser.getStore() == null) {
            throw new UserException("This user is not associated with any store");
        }
        return StoreMapper.toDTO(currentUser.getStore());
    }

    @Override
    public StoreDto moderateStore(Long id, StoreStatus status) throws Exception {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new Exception("Store not found..."));
        store.setStatus(status);
        Store updatedStore = storeRepository.save(store);
        return StoreMapper.toDTO(updatedStore);
    }
}
