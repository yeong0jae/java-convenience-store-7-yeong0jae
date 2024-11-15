package store.domain.receipt;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private static final int MEMBERSHIP_DISCOUNT_MAX = 8000;
    private static final double MEMBERSHIP_DISCOUNT_PERCENT = 0.3;

    private List<PurchaseHistory> purchaseHistories = new ArrayList<>();
    private List<GivenProduct> givenProducts = new ArrayList<>();
    private int promotionDiscount;
    private int membershipDiscount;

    public void applyPromotion(String givenProductName, int givenProductCount, int discountAmount) {
        addGivenProduct(givenProductName, givenProductCount);
        addPromotionDiscount(discountAmount);
    }

    public int getPaymentAmount() {
        return getTotalPurchaseAmount() - promotionDiscount - membershipDiscount;
    }

    public int getTotalPurchaseAmount() {
        return purchaseHistories.stream()
                .mapToInt(PurchaseHistory::getAmount)
                .sum();
    }

    public int getPurchaseCount() {
        return purchaseHistories.stream()
                .mapToInt(PurchaseHistory::getCount)
                .sum();
    }

    public void addPurchaseHistory(String name, int count, int price) {
        purchaseHistories.add(new PurchaseHistory(name, count, price));
    }

    public List<PurchaseHistory> getPurchaseHistories() {
        return purchaseHistories;
    }

    public void disagreeMembershipDiscount() {
        membershipDiscount = 0;
    }

    private void addPromotionDiscount(int amount) {
        promotionDiscount += amount;
    }

    public void addMembershipDiscount(int amount) {
        membershipDiscount += (int) (amount * MEMBERSHIP_DISCOUNT_PERCENT);
        if (membershipDiscount >= MEMBERSHIP_DISCOUNT_MAX) {
            membershipDiscount = MEMBERSHIP_DISCOUNT_MAX;
        }
    }

    private void addGivenProduct(String givenProductName, int givenProductCount) {
        givenProducts.add(new GivenProduct(givenProductName, givenProductCount));
    }

    public int getPromotionDiscount() {
        return promotionDiscount;
    }

    public int getMembershipDiscount() {
        return membershipDiscount;
    }

    public List<GivenProduct> getGivenProducts() {
        return givenProducts;
    }

    public void updatePurchaseHistoryCount(String name, int count) {
        purchaseHistories.stream()
                .filter(purchaseHistory -> purchaseHistory.matchesName(name))
                .findFirst()
                .get()
                .updateCount(count);
    }
}
