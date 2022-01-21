package algorithms.patterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class StringPermutation {

  public static void main(String[] args) {
    System.out.println(findPermutation("oidbcaf", "abc"));
    System.out.println(findPermutation("odicf","dc"));
    System.out.println(findPermutation("bcdxabcdy","bcdyabcdx"));
    System.out.println(findPermutation("aaacb","abc"));
  }

  public static boolean findPermutation(String str, String pattern) {
    Map<Character, Integer> characterFrequencyInPattern = new HashMap<>();
    for (int i = 0; i < pattern.length(); i++) {
      characterFrequencyInPattern.put(pattern.charAt(i),
          characterFrequencyInPattern.getOrDefault(pattern.charAt(i), 0) + 1);
    }
    int windowStart = 0;
    int matched = 0;
    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
      char right = str.charAt(windowEnd);
      if (characterFrequencyInPattern.containsKey(right)) {
        characterFrequencyInPattern.put(right, characterFrequencyInPattern.get(right) - 1);
        if (characterFrequencyInPattern.get(right) == 0) {
          matched++;
        }
      }
      if (matched == characterFrequencyInPattern.size()) {
        return true;
      }
      if(windowEnd >= pattern.length() -1) {
        char left = str.charAt(windowStart++);
        if(characterFrequencyInPattern.containsKey(left)) {
          if (characterFrequencyInPattern.get(left) == 0) {
            matched--;
          }
          characterFrequencyInPattern.put(left, characterFrequencyInPattern.get(left) + 1);
        }
      }
    }
    return false;
  }


}
