package store.domain.receipt;

public class Receipt {
    private int promotionDiscount;
    private int membershipDiscount;

    public void addPromotionDiscount(int amount) {
        promotionDiscount += amount;
    }

    public void addMembershipDisCount(int amount) {
        membershipDiscount += amount;
    }

    public int getPromotionDiscount() {
        return promotionDiscount;
    }

    public int getMembershipDiscount() {
        return membershipDiscount;
    }
}
