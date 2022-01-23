package algorithms.patterns.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Given a string and a pattern, find the smallest substring in the given string which has all the characters(matching frequency) of the given pattern.

public class SmallestWindowContainingSubstring {

  public static void main(String[] args) {

    System.out.println(smallestSubstringContainingPattern("aabdec", "abc"));
    System.out.println(smallestSubstringContainingPattern("aabdec", "abc"));
    System.out.println(smallestSubstringContainingPattern("abdabca", "abc"));
    System.out.println(smallestSubstringContainingPattern("adcad", "abc"));

  }

  public static String smallestSubstringContainingPattern(String str, String pattern) {
    Map<Character, Integer> characterFrequency = new HashMap<>();
    for (int i = 0; i < pattern.length(); i++) {
      characterFrequency
          .put(pattern.charAt(i), characterFrequency.getOrDefault(pattern.charAt(i), 0) + 1);
    }

    int windowStart = 0;
    int leftIndex = 0;
    int matched = 0;
    int minWindowSize = str.length() + 1;

    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
      char right = str.charAt(windowEnd);
      if (characterFrequency.containsKey(right)) {
        characterFrequency.put(right, characterFrequency.get(right) - 1);
        if (characterFrequency.get(right) >= 0) {
          matched++;
        }
      }

      while (matched == pattern.length()) {
        if (minWindowSize > windowEnd - windowStart + 1) {
          leftIndex = windowStart;
          minWindowSize = windowEnd - windowStart + 1;
        }
        char left = str.charAt(windowStart);
        if(characterFrequency.containsKey(left)) {
          if(characterFrequency.get(left) ==0) {
            matched--;
          }
          characterFrequency.put(left, characterFrequency.get(left) + 1);
        }
        windowStart++;
      }
    }
    StringBuilder sb = new StringBuilder();
    if (minWindowSize != str.length() + 1) {
      for (int i = leftIndex; i < leftIndex +minWindowSize; i++) {
        sb.append(str.charAt(i));
      }
    }
    return sb.toString();
  }
}
