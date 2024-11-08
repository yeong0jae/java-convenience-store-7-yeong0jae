package store.domain;

import java.util.List;

public class Stock {

    List<Product> products;

    public Stock(List<Product> products) {
        this.products = products;
    }

    public boolean existsByName(String name) {
        if (products.stream().noneMatch(
                product -> product.isSameName(name)
        )) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
        }
        return true;
    }
}
