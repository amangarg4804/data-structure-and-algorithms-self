package algorithms.leetcoderecursion2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfPhoneNumber {
    private static final Map<Character, String> digitToAlpha = Map.of('2', "abc",
            '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz"); // We could also use an array instead of map String[] keypad = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits.length()==0) {
            return result;
        }
        backtrack(result, digits, 0, new StringBuilder());
        return result;
    }

    private void backtrack(List<String> result, String inputDigits, int digitIndex, StringBuilder current) {
        if(current.length() ==inputDigits.length()) {
            result.add(current.toString());
            return;
        }

        String charactersForDigit = digitToAlpha.get(inputDigits.charAt(digitIndex)); // keypad[digits.charAt[i] -'0') in case of using keypad array
        for(int i = 0; i< charactersForDigit.length(); i++) {
            current.append(charactersForDigit.charAt(i));
            backtrack(result, inputDigits, digitIndex+1, current);
            current.deleteCharAt(current.length()-1);
        }
    }

}
