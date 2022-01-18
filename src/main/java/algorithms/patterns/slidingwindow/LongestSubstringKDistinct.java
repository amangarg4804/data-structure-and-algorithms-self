package algorithms.patterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKDistinct {

  // Given a string, find the length of the longest substring in it with no more than K distinct characters.

  public static void main(String[] args) {
    System.out.println(longestSubstring("araaci", 2));
    System.out.println(longestSubstring("araaci", 1));
    System.out.println(longestSubstring("cbbebi", 3));
  }

  public static int longestSubstring(String input, int k) {
    if (k <= 0 || input == null) {
      return 0;
    }
    Map<Character, Integer> map = new HashMap<>();
    int resultLength = 0;
    int windowStart = 0;
    for (int windowEnd = 0; windowEnd < input.length(); windowEnd++) {
      Character character = input.charAt(windowEnd);
      if (map.containsKey(character)) {
        Integer count = map.get(character);
        map.put(character, count + 1);
      } else {
        map.put(input.charAt(windowEnd), 1);
      }
      while (map.size() > k) {
        Integer count = map.get(input.charAt(windowStart));
        if (count == 1) {
          map.remove(input.charAt(windowStart));
        } else {
          map.put(input.charAt(windowStart), count - 1);
        }
        windowStart++;
      }
      resultLength = Math.max(resultLength, windowEnd - windowStart + 1);
    }
    return resultLength;
  }
}
