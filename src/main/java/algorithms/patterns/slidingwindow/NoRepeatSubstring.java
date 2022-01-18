package algorithms.patterns.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class NoRepeatSubstring {

  public static void main(String[] args) {
    System.out.println(longestSubstring("aabccbb"));
    System.out.println(longestSubstring("abbbb"));
    System.out.println(longestSubstring("abccde"));

    System.out.println(longestSubstringWithoutNestedLoop("aabccbb"));
    System.out.println(longestSubstringWithoutNestedLoop("abbbb"));
    System.out.println(longestSubstringWithoutNestedLoop("abccde"));
  }

  public static int longestSubstring(String input) {
    //acbdcde
    Set<Character> set = new LinkedHashSet<>();

    int windowStart = 0;
    int longest = 0;
    for (int windowEnd = 0; windowEnd < input.length(); windowEnd++) {
      if (set.contains(input.charAt(windowEnd))) {
        Iterator<Character> iterator = set.iterator();
        while (iterator.hasNext()) {
          Character c = iterator.next();
          iterator.remove();
          if (c.equals(input.charAt(windowEnd))) {
            break;
          }
        }
      }
      set.add(input.charAt(windowEnd));
      longest = Math.max(longest, set.size());
    }
    return longest;
  }

  public static int longestSubstringWithoutNestedLoop(String input) {
    //acbdcde
    Map<Character, Integer> characterIndexMap = new HashMap<>();
    int windowStart = 0;
    int longest = 0;
    for (int windowEnd = 0; windowEnd < input.length(); windowEnd++) {
      if (characterIndexMap.containsKey(input.charAt(windowEnd))) {
        windowStart = Math.max(windowStart, characterIndexMap.get(input.charAt(windowEnd)) + 1);
      }
      characterIndexMap.put(input.charAt(windowEnd), windowEnd);
      longest = Math.max(longest, windowEnd - windowStart + 1);
    }
    return longest;
  }

}
