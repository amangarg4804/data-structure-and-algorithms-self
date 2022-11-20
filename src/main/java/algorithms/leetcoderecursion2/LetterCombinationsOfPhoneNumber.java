package algorithms.leetcoderecursion2;

import java.util.*;

public class LetterCombinationsOfPhoneNumber {
    private static final Map<Character, String> digitToAlpha = Map.of('2', "abc",
            '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz"); // We could also use an array instead of map String[] keypad = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private static final String[] keypad = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
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

    public List<String> letterCombinationsIterative(String digits) {
        List<String> result = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        if(digits.length()==0) {
            return result;
        }
        String zeroString = keypad[digits.charAt(0)-'0'];
        for(int i=0; i< zeroString.length(); i++) {
            q.offer(String.valueOf(zeroString.charAt(i))); // NOTE: String doesn't have a constructor that accepts character. String.valueOf is used to convert char to String
        }
        int currentIndex = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i< size ; i++) {
                String s = q.poll();
                if(currentIndex >= digits.length()) {
                    result.add(s);
                } else {
                    String nextString = keypad[digits.charAt(currentIndex)-'0'];
                    for(int j=0; j< nextString.length();j++) {
                        q.offer(s+ nextString.charAt(j));
                    }
                }
            }
            currentIndex++;
        }
        return result;
    }

}
