package store.domain.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.stock.Product;
import store.domain.stock.Stock;

class OrderTest {
    private Order order;

    @BeforeEach
    void setUp() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("콜라", "10"),
                new OrderItem("탄산수", "3")
        );
        List<Product> products = List.of(
                new Product("콜라", 1000, 10, "탄산2+1"),
                new Product("콜라", 1000, 10, null),
                new Product("탄산수", 1200, 5, "탄산2+1")
        );
        order = new Order(orderItems, new Stock(products));
    }

    @DisplayName("주문 상품 이름 목록을 구한다.")
    @Test
    void findOrderItemNamesTest() {
        List<String> orderItemNames = order.findOrderItemNames();

        assertThat(orderItemNames).isEqualTo(List.of("콜라", "탄산수"));
    }

    @DisplayName("주문 수량을 확인한다.")
    @Test
    void findCountByNameTest() {
        int orderQuantity = order.findCountByName("콜라");

        assertThat(orderQuantity).isEqualTo(10);
    }

//    @DisplayName("총구매액을 계산한다.")
//    @Test
//    void calculateTotalPurchaseAmountTest() {
//        int totalPurchaseAmount = order.calculateTotalPurchaseAmount();
//
//        assertThat(totalPurchaseAmount).isEqualTo(13600);
//    }
}
