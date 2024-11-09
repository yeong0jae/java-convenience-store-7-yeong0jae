package store.domain;

import java.util.List;

public class Order {

    List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
