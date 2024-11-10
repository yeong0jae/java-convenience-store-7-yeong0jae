package store.domain.stock;

import java.util.List;
import store.util.ErrorMessage;

public class Stock {
    private List<Product> products;

    public Stock(List<Product> products) {
        this.products = products;
    }

    public boolean hasPromotion(String name) {
        return findByName(name).stream()
                .anyMatch(Product::promotionIsNotNull);
    }

    public String findPromotionNameByName(String name) {
        return findByName(name).stream()
                .filter(Product::promotionIsNotNull)
                .findFirst()
                .get()
                .getPromotionName();
    }

    public int findPriceByName(String name) {
        return findByName(name).stream()
                .map(Product::getPrice)
                .findFirst()
                .get();
    }

    public void existsByName(String name) {
        if (findByName(name).isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.PREFIX + "존재하지 않는 상품입니다. 다시 입력해 주세요.");
        }
    }

    public void hasEnoughQuantity(String name, int count) {
        if (count > findQuantityByName(name)) {
            throw new IllegalArgumentException(ErrorMessage.PREFIX + "재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    private int findQuantityByName(String name) {
        return findByName(name).stream()
                .mapToInt(Product::getQuantity)
                .sum();
    }

    private List<Product> findByName(String name) {
        return products.stream()
                .filter(product -> product.matchesName(name))
                .toList();
    }
}
