package algorithms.patterns.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramStartIndexes {

  public static void main(String[] args) {
    System.out.println(findIndexes("ppqp", "pq"));
    System.out.println(findIndexes("abbcabc", "abc"));
  }

  public static List<Integer> findIndexes(String str, String pattern) {
    Map<Character, Integer> characterFrequency = new HashMap<>();

    for (int i = 0; i < pattern.length(); i++) {
      characterFrequency
          .put(pattern.charAt(i), characterFrequency.getOrDefault(pattern.charAt(i), 0) + 1);
    }
    int windowStart = 0;
    int matchCount = 0;
    List<Integer> resultList = new ArrayList<>();
    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
      char right = str.charAt(windowEnd);
      if (characterFrequency.containsKey(right)) {
        characterFrequency.put(right, characterFrequency.get(right) - 1);
        if (characterFrequency.get(right) == 0) {
          // matchCount is incremented only when frequency of a character is 0 in map
          // (meaning the frequency of character is same in current window as the given pattern)
          matchCount++;
        }
      }
      if (matchCount == characterFrequency.size()) {
        // because map size is equal to no of characters in pattern.
        // Map size doesn't change even though the value for each key is 0
        resultList.add(windowStart);
      }
      // pattern =abc, windowEnd = 2;
      if (windowEnd >= pattern.length() - 1) {
        char left = str.charAt(windowStart);
        if (characterFrequency.containsKey(left)) {
          if (characterFrequency.get(left) == 0) {
            matchCount--;
          }
          characterFrequency.put(left, characterFrequency.get(left) + 1);
        }
        windowStart++;
      }
    }
    return resultList;

  }
}
