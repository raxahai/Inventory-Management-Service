package com.sadapay.inventoryservice.repository;

import com.sadapay.inventoryservice.entity.Inventory;
import com.sadapay.inventoryservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Inventory,Long> {
}
