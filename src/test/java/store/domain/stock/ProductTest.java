package store.domain.stock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    @DisplayName("상품명이 같은지 확인한다.")
    @Test
    void equalNameTest() {
        boolean isSameName = product.matchesName("콜라");

        assertThat(isSameName).isTrue();
    }
}
