package com.sadapay.inventoryservice.service.impl;

import com.sadapay.inventoryservice.dto.response.InventoryResponseDto;
import com.sadapay.inventoryservice.entity.Inventory;
import com.sadapay.inventoryservice.repository.ItemRepository;
import com.sadapay.inventoryservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public List<Inventory> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Inventory> create(List<Inventory> inventory) {
        return itemRepository.saveAll(inventory);
    }

    @Override
    public void deleteItems(List<Long> id) {
       itemRepository.deleteAllById(id);
    }
}
