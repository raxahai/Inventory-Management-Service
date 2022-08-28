package com.sadapay.inventoryservice.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ItemEntityUnitTest {

    @Test
    void shouldCheckType(){
        // given
        Long id = 1L;
        String name = "Dummy";
        String description = "Dummy Test";
        Double price = 123d;
        Double cost = 123d;

        // when
        Item underTest = new Item(id,name,description,price,cost);

        // then
        assertThat(underTest.getId()).isInstanceOf(Long.class);
        assertThat(underTest.getName()).isInstanceOf(String.class);
        assertThat(underTest.getDescription()).isInstanceOf(String.class);
        assertThat(underTest.getCost()).isInstanceOf(Double.class);
        assertThat(underTest.getPrice()).isInstanceOf(Double.class);
    }
}