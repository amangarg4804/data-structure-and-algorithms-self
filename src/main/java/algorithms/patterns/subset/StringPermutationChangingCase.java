package algorithms.patterns.subset;

import java.util.ArrayList;
import java.util.List;

public class StringPermutationChangingCase {

  public static void main(String[] args) {
    System.out.println(stringPermutations("ad52"));
    System.out.println(stringPermutations("ab7c"));
  }

  private static List<String> stringPermutations(String str) {
    List<String> result = new ArrayList<>();
    result.add(str);
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (Character.isLetter(c)) {
        int resultSize = result.size();
        for (int j = 0; j < resultSize; j++) {
          char[] existing = result.get(j).toCharArray();
          if (Character.isUpperCase(c)) {
            existing[i] = Character.toLowerCase(c);
          } else {
            existing[i] = Character.toUpperCase(c);
          }
          result.add(String.valueOf(existing));
        }
      }
    }
    return result;
  }


}
