package com.sadapay.inventoryservice.controller;

import com.sadapay.inventoryservice.dto.request.InventoryRequestDto;
import com.sadapay.inventoryservice.dto.response.InventoryItemDto;
import com.sadapay.inventoryservice.dto.response.InventoryResponseDto;
import com.sadapay.inventoryservice.entity.Inventory;
import com.sadapay.inventoryservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items-management")
public class InventoryController {

    private ItemService itemService;

    @Autowired
    public InventoryController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<InventoryResponseDto> getItems(){
        return new ResponseEntity(
                InventoryResponseDto.builder()
                .lines(itemService.getItems())
                .build()
                ,HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<InventoryResponseDto> create(@RequestBody InventoryRequestDto inventory){
        return new ResponseEntity(
                InventoryResponseDto
                        .builder()
                        .lines(itemService.create(inventory.getLines()))
                        .build()
                ,HttpStatus.CREATED
        );
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody InventoryItemDto inventoryItemDto){
        itemService.deleteItems(
                inventoryItemDto.getItemIds()
        );
        return ResponseEntity.ok(inventoryItemDto);
    }
}
