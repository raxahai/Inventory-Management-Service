package com.sadapay.inventoryservice.dto.request;

import com.sadapay.inventoryservice.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequestDto {
    private List<Inventory> lines;
}
