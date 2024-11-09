package store.domain.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("주문 상품 이름 목록을 구한다.")
    @Test
    void getOrderItemNamesTest() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("콜라", 10),
                new OrderItem("탄산수", 3)
        );
        Order order = new Order(orderItems);

        List<String> orderItemNames = order.getOrderItemNames();

        assertThat(orderItemNames).isEqualTo(List.of("콜라", "탄산수"));
    }

    @DisplayName("주문 수량을 확인한다.")
    @Test
    void findQuantityByNameTest() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("콜라", 10),
                new OrderItem("탄산수", 3)
        );
        Order order = new Order(orderItems);

        int orderQuantity = order.findQuantityByName("콜라");

        assertThat(orderQuantity).isEqualTo(10);
    }
}
