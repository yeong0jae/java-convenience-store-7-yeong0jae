package store.domain.receipt;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<PurchaseHistory> purchaseHistories = new ArrayList<>();
    private int promotionDiscount;
    private int membershipDiscount;
    private List<String> givenProductNames = new ArrayList<>();
    private List<Integer> givenProductCounts = new ArrayList<>();

    public void addPurchaseHistory(String name, int count, int price) {
        purchaseHistories.add(new PurchaseHistory(name, count, price));
    }

    public List<PurchaseHistory> getPurchaseHistories() {
        return purchaseHistories;
    }

    public void disagreeMembershipDiscount() {
        membershipDiscount = 0;
    }

    public void addPromotionDiscount(int amount) {
        promotionDiscount += amount;
    }

    public void addMembershipDiscount(int amount) {
        membershipDiscount += (int) (amount * 0.3);
        if (membershipDiscount >= 8000) {
            membershipDiscount = 8000;
        }
    }

    public void addGivenProduct(String givenProductName, int givenProductCount) {
        givenProductNames.add(givenProductName);
        givenProductCounts.add(givenProductCount);
    }

    public int getPromotionDiscount() {
        return promotionDiscount;
    }

    public int getMembershipDiscount() {
        return membershipDiscount;
    }
}
