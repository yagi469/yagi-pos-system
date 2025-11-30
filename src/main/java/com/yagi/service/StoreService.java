package com.yagi.service;

import java.util.List;

import com.yagi.domain.StoreStatus;
import com.yagi.exception.UserException;
import com.yagi.model.User;
import com.yagi.payload.dto.StoreDto;

public interface StoreService {

    StoreDto createStore(StoreDto store, User user);

    StoreDto getStoreById(Long id) throws Exception;

    List<StoreDto> getAllStores();

    List<StoreDto> getStoreByAdmin() throws UserException;

    StoreDto updateStore(Long id, StoreDto store) throws Exception;

    void deleteStore(Long id) throws Exception;

    StoreDto getStoreByEmployee() throws UserException;

    StoreDto moderateStore(Long id, StoreStatus status) throws Exception;

}
