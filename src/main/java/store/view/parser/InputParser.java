package store.view.parser;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    public List<String> parseOrderItem(String rawOrderItem) {
        String replacedInput = rawOrderItem.replace("[", "").replace("]", "");
        List<String> parts = Arrays.asList(replacedInput.split("-"));

        if (parts.size() != 2 || parts.get(0).isEmpty() || !isNumeric(parts.get(1))) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }

        return parts;
    }

    private boolean isNumeric(String str) {
        return str.chars().allMatch(Character::isDigit);
    }
}
