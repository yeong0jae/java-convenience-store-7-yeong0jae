package store.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {
    private Product product;

    @BeforeEach
    void setUp() {
        String name = "콜라";
        int price = 1000;
        int quantity = 10;
        String promotion = "탄산2+1";

        product = new Product(name, price, quantity, promotion);
    }

    @DisplayName("상품은 이름, 가격, 재고, 프로모션을 가진다.")
    @Test
    void productTest() {
        assertAll("product",
                () -> assertEquals("콜라", product.name),
                () -> assertEquals(1000, product.price),
                () -> assertEquals(10, product.quantity),
                () -> assertEquals("탄산2+1", product.promotion)
        );
    }

    @DisplayName("상품명이 같은지 확인한다.")
    @Test
    void equalNameTest() {
        boolean isSameName = product.isSameName("콜라");

        assertThat(isSameName).isTrue();
    }
}
