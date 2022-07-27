package algorithms.leetcodeTop100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LetterCombinationOfNumbers {
  private static final Map<Character, String> digitToAlpha = Map.of('2', "abc",
      '3', "def",
      '4', "ghi",
      '5', "jkl",
      '6', "mno",
      '7', "pqrs",
      '8', "tuv",
      '9', "wxyz");

  public static void main(String[] args) {
    String digits = "23";
    System.out.println(letterCombinations(digits));
  }

  public static List<String> letterCombinations(String digits) {
    if(digits.length() ==0) {
      return new ArrayList<>();
    }
    List<String> inputs = new ArrayList<>();
    for(int i=0; i< digits.length(); i++) {
      inputs.add(digitToAlpha.get(digits.charAt(i)));
    }
    List<String> result = new ArrayList<>();
    Queue<StringBuilder> queue = new LinkedList<>();
    queue.add(new StringBuilder());
    while (!queue.isEmpty()) {
      StringBuilder sb = queue.poll();
      if(sb.length()==digits.length()) {
        result.add(sb.toString());
      } else {
        for(int i = 0; i< inputs.get(sb.length()).length(); i++) {
          StringBuilder stringBuilder = new StringBuilder(sb);
          queue.add(stringBuilder.append(inputs.get(sb.length()).charAt(i)));
        }

      }
    }
    return result;
  }
}
