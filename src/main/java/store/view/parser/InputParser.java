package store.view.parser;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    public List<String> parseOrderItem(String rawOrderItem) {
        validateBrackets(rawOrderItem);

        String replacedInput = rawOrderItem.replace("[", "").replace("]", "");
        List<String> parts = Arrays.asList(replacedInput.split("-"));

        validatePartsSize(parts);
        validateProductName(parts);
        validateQuantity(parts);

        return parts;
    }

    private void validateBrackets(String rawOrderItem) {
        if (!rawOrderItem.startsWith("[") || !rawOrderItem.endsWith("]")) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }

    private void validatePartsSize(List<String> parts) {
        if (parts.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }

    private void validateProductName(List<String> parts) {
        if (parts.get(0).isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }

    private void validateQuantity(List<String> parts) {
        if (!isNumeric(parts.get(1))) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }
    
    private boolean isNumeric(String str) {
        return str.chars().allMatch(Character::isDigit);
    }
}
