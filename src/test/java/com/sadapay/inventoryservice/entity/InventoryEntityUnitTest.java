package com.sadapay.inventoryservice.entity;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InventoryEntityUnitTest {
    @Test
    void shouldCheckType() {
        // given
        Long id = 1L;
        Item item = new Item(1L, "Dummy", "Dummy Test", 123d, 123d);
        Integer quantity = 123;

        // when
        Inventory underTest = new Inventory(
                id,
                item,
                quantity,
                Timestamp.valueOf( LocalDateTime.now()),
                Timestamp.valueOf( LocalDateTime.now())
        );

        // then
        assertThat(underTest.getId()).isInstanceOf(Long.class);
        assertThat(underTest.getItem()).isInstanceOf(Item.class);
        assertThat(underTest.getQuantity()).isInstanceOf(Integer.class);
    }
}