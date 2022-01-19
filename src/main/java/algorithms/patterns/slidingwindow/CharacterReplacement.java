package algorithms.patterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class CharacterReplacement {

  //  Given a string with lowercase letters only, if you are allowed to replace no more than ‘k’ letters with any letter,
  //  find the length of the longest substring having the same letters after replacement.
  public static void main(String[] args) {
    System.out.println(findLength("aabccbb", 2));
    System.out.println(findLength("abbcb", 1));
    System.out.println(findLength("abccde", 1));
  }

  public static int findLength(String input, int k) {
    Map<Character, Integer> characterFrequencyMapForCurrentWindow = new HashMap<>();
    int maxLength = 0;
    int windowStart = 0;
    int maxOccurences = 0;
    for (int windowEnd = 0; windowEnd < input.length(); windowEnd++) {
      Character rightChar = input.charAt(windowEnd);
      characterFrequencyMapForCurrentWindow.put(
          rightChar, characterFrequencyMapForCurrentWindow.getOrDefault(rightChar, 0) +1);
      maxOccurences = Math.max(maxOccurences, characterFrequencyMapForCurrentWindow.get(rightChar));
      if(windowEnd-windowStart+1 - characterFrequencyMapForCurrentWindow.get(rightChar) > k) {
        Character leftChar = input.charAt(windowStart);
        characterFrequencyMapForCurrentWindow.put(leftChar, characterFrequencyMapForCurrentWindow.get(leftChar)-1);
        windowStart++;
      }
      maxLength = Math.max(maxLength, windowEnd-windowStart+1);
    }
    return maxLength;
  }

}
