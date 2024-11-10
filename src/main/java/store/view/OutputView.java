package store.view;

import java.text.NumberFormat;
import java.util.List;
import store.domain.receipt.Receipt;
import store.domain.stock.Product;

public class OutputView {
    public void printStock(List<Product> products) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.\n");

        products.forEach(product -> {
            String formattedPrice = numberFormat(product.getPrice()) + "원";
            String promotionName = product.getPromotionName() != null ? product.getPromotionName() : "";
            String quantityText = product.getQuantity() > 0
                    ? product.getQuantity() + "개"
                    : "재고 없음";
            System.out.println(
                    "- " + product.getName() + " " + formattedPrice + " " + quantityText + " " + promotionName);
        });
        System.out.println();
    }

    public void printReceipt(Receipt receipt) {
        System.out.println("===========W 편의점=============");
        System.out.println("상품명\t\t수량\t금액");
        receipt.getPurchaseHistories().forEach(purchaseHistory -> {
            System.out.println(purchaseHistory.getName() + "\t\t" + purchaseHistory.getCount() + " \t"
                    + numberFormat(purchaseHistory.getAmount()));
        });
        System.out.println("===========증\t정==============");
        receipt.getGivenProducts().forEach(givenProduct -> {
            System.out.println(givenProduct.getName() + "\t\t" + givenProduct.getCount());
        });
        System.out.println("==============================");
        System.out.println(
                "총구매액\t\t" + receipt.getPurchaseCount() + "\t" + numberFormat(receipt.getTotalPurchaseAmount()));
        System.out.println("행사할인\t\t\t-" + numberFormat(receipt.getPromotionDiscount()));
        System.out.println("멤버십할인\t\t\t-" + numberFormat(receipt.getMembershipDiscount()));
        System.out.println("내실돈\t\t\t " + numberFormat(receipt.getPaymentAmount()));
    }

    private String numberFormat(int number) {
        return NumberFormat.getInstance().format(number);
    }
}
