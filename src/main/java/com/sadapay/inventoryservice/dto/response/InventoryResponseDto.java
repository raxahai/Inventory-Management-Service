package com.sadapay.inventoryservice.dto.response;

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
public class InventoryResponseDto {
    private List<Inventory> lines;
}
