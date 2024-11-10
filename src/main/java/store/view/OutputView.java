package store.view;

import java.text.NumberFormat;
import java.util.List;
import store.domain.stock.Product;

public class OutputView {
    public void printStock(List<Product> products) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.\n");

        products.forEach(product -> {
            String formattedPrice = NumberFormat.getInstance().format(product.getPrice()) + "원";
            String promotionName = product.getPromotionName() != null ? product.getPromotionName() : "";
            String quantityText = product.getQuantity() > 0
                    ? product.getQuantity() + "개"
                    : "재고 없음";
            System.out.println(
                    "- " + product.getName() + " " + formattedPrice + " " + product.getQuantity() + "개 "
                            + promotionName);
        });
    }
}

