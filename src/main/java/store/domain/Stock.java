package store.domain;

import java.util.List;

public class Stock {

    private List<Product> products;

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

    public boolean hasEnoughStock(String name, int quantity) {
        if (quantity > findQuantityByName(name)) {
            throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
        return true;
    }

    private int findQuantityByName(String name) {
        return products.stream().filter(
                product -> product.isSameName(name)
        ).mapToInt(
                Product::getQuantity
        ).sum();
    }

    public int findPriceByName(String name) {
        return products.stream()
                .filter(product -> product.isSameName(name))
                .map(Product::getPrice)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요."));
    }
}
