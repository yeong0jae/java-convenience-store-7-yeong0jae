package store.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import store.view.parser.InputParser;

public class InputView {
    private final InputParser inputParser;

    public InputView(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public List<List<String>> readOrderItems() {
        print("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String input = Console.readLine();

        return Arrays.stream(input.split(","))
                .map(inputParser::parseOrderItem)
                .toList();
    }

    public boolean readAdditionalPromotion(String name, int count) {
        print("현재 " + name + "은(는) " + count + "개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
        return isYes();
    }

    public boolean readNoApplicablePromotion(String name, int count) {
        print("현재 " + name + " " + count + "개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
        return isYes();
    }

    public boolean readMembershipDiscountAgree() {
        print("\n멤버십 할인을 받으시겠습니까? (Y/N)");
        return isYes();
    }

    public boolean readPurchaseAnother() {
        print("\n감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        return isYes();
    }

    private boolean isYes() {
        return Console.readLine().equalsIgnoreCase("Y");
    }

    private void print(String output) {
        System.out.println(output);
    }
}
