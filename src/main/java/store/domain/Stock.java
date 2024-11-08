package store.domain;

import java.util.List;

public class Stock {

    List<Product> products;

    public Stock(List<Product> products) {
        this.products = products;
    }
}
