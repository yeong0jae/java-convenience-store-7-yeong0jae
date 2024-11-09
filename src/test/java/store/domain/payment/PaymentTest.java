package store.domain.payment;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.order.Order;
import store.domain.order.OrderItem;
import store.domain.stock.Product;
import store.domain.stock.Stock;

class PaymentTest {

    @DisplayName("총구매액을 계산한다.")
    @Test
    void calculateTotalPurchaseAmountTest() {
        Order order = new Order(List.of(
                new OrderItem("콜라", 10),
                new OrderItem("탄산수", 3)));
        Stock stock = new Stock(List.of(
                new Product("콜라", 1000, 20, "탄산2+1"),
                new Product("탄산수", 1000, 5, "탄산2+1")));
        Payment payment = new Payment(order, stock);

        int totalPurchaseAmount = payment.calculateTotalPurchaseAmount();

        assertThat(totalPurchaseAmount).isEqualTo(13000);
    }
}
