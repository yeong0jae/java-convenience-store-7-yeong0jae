package store.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderItemTest {
    
    @DisplayName("주문 상품의 가격을 계산한다.")
    @Test
    void calculateOrderItemAmount() {
        String name = "콜라";
        int quantity = 10;
        int price = 1000;
        OrderItem orderItem = new OrderItem(name, quantity);

        int totalPrice = orderItem.calculateTotalPrice(price);

        assertThat(totalPrice).isEqualTo(10000);
    }
}
