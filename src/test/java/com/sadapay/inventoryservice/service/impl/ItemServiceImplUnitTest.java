package com.sadapay.inventoryservice.service.impl;

import com.sadapay.inventoryservice.entity.Inventory;
import com.sadapay.inventoryservice.entity.Item;
import com.sadapay.inventoryservice.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplUnitTest {

    @Mock
    private ItemRepository itemRepository;
    private ItemServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new ItemServiceImpl(itemRepository);
    }

    @Test
    void canGetAllItems() {
        // when
        underTest.getItems();
        // then
        verify(itemRepository).findAll();
    }

    @Test
    void createItem() {
        // given
        ArrayList<Inventory> inventories = new ArrayList<Inventory>();
        inventories.add(new Inventory(
                1L,new Item(
                        1L,"Tang","Tang is a juice",123.213,1323.21)
                ,2023123,  Timestamp.from(Instant.now()),Timestamp.from(Instant.now())));
        inventories.add(new Inventory(
                1L,new Item(
                1L,"Mango","Mango is a fruit",131232.2,12312.321)
                ,32234314,Timestamp.from(Instant.now()),Timestamp.from(Instant.now())));

        // when
        underTest.create(inventories);

        // then
        ArgumentCaptor<List<Inventory>> argumentCaptor =  ArgumentCaptor.forClass((Class) List.class);
        verify(itemRepository).saveAll(argumentCaptor.capture());
        List<Inventory> capturedInventories = argumentCaptor.getValue();
        assertThat(capturedInventories).isEqualTo(inventories);
    }

    @Test
    void deleteItems() {
        // given
        List<Long> ids = new ArrayList<Long>(Arrays.asList(1L,2L,3L));

        // when
        underTest.deleteItems(ids);

        // then
        ArgumentCaptor<List<Long>> argumentCaptor = ArgumentCaptor.forClass((Class) List.class);
        verify(itemRepository).deleteAllById(argumentCaptor.capture());
        List<Long> capturedIds = argumentCaptor.getValue();
        assertThat(capturedIds).isEqualTo(ids);
    }
}