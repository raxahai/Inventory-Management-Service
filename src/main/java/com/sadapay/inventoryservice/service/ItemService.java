package com.sadapay.inventoryservice.service;

import com.sadapay.inventoryservice.dto.response.InventoryResponseDto;
import com.sadapay.inventoryservice.entity.Inventory;

import java.util.List;

public interface ItemService {
    List<Inventory> getItems();
    List<Inventory> create(List<Inventory> inventory);
    void deleteItems(List<Long> id);
}
