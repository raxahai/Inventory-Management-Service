package com.sadapay.inventoryservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadapay.inventoryservice.dto.request.InventoryRequestDto;
import com.sadapay.inventoryservice.dto.response.InventoryItemDto;
import com.sadapay.inventoryservice.dto.response.InventoryResponseDto;
import com.sadapay.inventoryservice.entity.Inventory;
import com.sadapay.inventoryservice.entity.Item;
import com.sadapay.inventoryservice.exceptions.CustomResponseEntityExceptionHandler;
import com.sadapay.inventoryservice.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@WebMvcTest
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        createInventory();
    }
    ArrayList<Inventory> inventories;
    public void createInventory(){
        inventories = new ArrayList<Inventory>();
        inventories.add(new Inventory(
                1L, new Item(
                1L, "Tang", "Tang is a juice", 123.213, 1323.21)
                , 2023123, Timestamp.from(Instant.now()), Timestamp.from(Instant.now())));
        inventories.add(new Inventory(
                1L, new Item(
                1L, "Mango", "Mango is a fruit", 131232.2, 12312.321)
                , 32234314, Timestamp.from(Instant.now()), Timestamp.from(Instant.now())));
    }

    @Test
    void shouldGetAllItems() throws Exception {
        // given
        given(itemService.getItems()).willReturn(inventories);

        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/items-management"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(itemService).getItems();
    }

    @Test
    void shouldCreateItems() throws Exception{
        // given
        InventoryResponseDto inventoryResponseDto = new InventoryResponseDto();
        inventoryResponseDto.setLines(inventories);
        given(itemService.create(inventories)).willReturn(inventories);
        InventoryRequestDto inventoryRequestDto = new InventoryRequestDto();
        inventoryRequestDto.setLines(inventories);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(inventoryRequestDto);

        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/items-management")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(itemService).create(any());
    }

    @Test
    void ShouldDeleteItems() throws Exception{
        // given
        List<Long> ids = new ArrayList<Long>(Arrays.asList(1L,2L,3L));
        InventoryItemDto inventoryItemDto = new InventoryItemDto();
        inventoryItemDto.setItemIds(ids);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(inventoryItemDto);

        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/items-management")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(itemService).deleteItems(any());

    }
}