package store.view;

import java.text.NumberFormat;
import java.util.List;
import store.domain.receipt.Receipt;
import store.domain.stock.Product;

public class OutputView {
    public void printStock(List<Product> products) {
        print("안녕하세요. W편의점입니다.");
        print("현재 보유하고 있는 상품입니다.\n");
        products.forEach(product -> {
            String formattedPrice = numberFormat(product.getPrice()) + "원";
            String promotionName = product.getPromotionName() != null ? product.getPromotionName() : "";
            String quantityText = product.getQuantity() > 0
                    ? product.getQuantity() + "개"
                    : "재고 없음";
            print("- " + product.getName() + " " + formattedPrice + " " + quantityText + " " + promotionName);
        });
        printEnter();
    }

    public void printReceipt(Receipt receipt) {
        print("===========W 편의점=============");
        printPurchaseHistories(receipt);
        printGivenProducts(receipt);
        printDiscounts(receipt);
    }

    private void printPurchaseHistories(Receipt receipt) {
        print("상품명\t\t수량\t금액");
        receipt.getPurchaseHistories().forEach(purchaseHistory ->
                print(purchaseHistory.getName() + "\t\t" + purchaseHistory.getCount() + " \t"
                        + numberFormat(purchaseHistory.getAmount()))
        );
    }

    private void printGivenProducts(Receipt receipt) {
        print("===========증\t정==============");
        receipt.getGivenProducts().forEach(
                givenProduct -> print(givenProduct.getName() + "\t\t" + givenProduct.getCount()));
        print("==============================");
    }

    private void printDiscounts(Receipt receipt) {
        print("총구매액\t\t" + receipt.getPurchaseCount() + "\t" + numberFormat(receipt.getTotalPurchaseAmount()));
        print("행사할인\t\t\t-" + numberFormat(receipt.getPromotionDiscount()));
        print("멤버십할인\t\t\t-" + numberFormat(receipt.getMembershipDiscount()));
        print("내실돈\t\t\t " + numberFormat(receipt.getPaymentAmount()));
    }

    private String numberFormat(int number) {
        return NumberFormat.getInstance().format(number);
    }

    private void print(String output) {
        System.out.println(output);
    }

    private void printEnter() {
        System.out.println();
    }
}
