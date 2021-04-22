package algorithms.sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class SortRoyalNames {

  /*
   * Complete the 'sortRoman' function below.
   *
   * The function is expected to return a STRING_ARRAY.
   * The function accepts STRING_ARRAY names as parameter.
   */
  public static final Map<Character, Integer> ROMAN_MAP = new HashMap<>();

  static {
    ROMAN_MAP.put('I', 1);
    ROMAN_MAP.put('X', 10);
    ROMAN_MAP.put('V', 5);
    ROMAN_MAP.put('L', 50);

  }

  public static List<String> sortRoman(List<String> names) {
    // Write your code here
    List<String> result = new ArrayList<>();

    PriorityQueue<RoyalName> pq = new PriorityQueue<>((name1, name2) -> {
      if (name1.name.equals(name2.name)) {
        return name1.numeral - name2.numeral;
      }
      return name1.name.compareTo(name2.name);
    });

    for (String name : names) {
      String[] nameAndRoman = name.split(" ");
      String onlyName = nameAndRoman[0];
      String onlyRoman = nameAndRoman[1];
      pq.add(new RoyalName(onlyName, onlyRoman, getInt(onlyRoman)));

    }
    while ( !pq.isEmpty()) {
      RoyalName rn = pq.poll();
      result.add(rn.name + " " + rn.roman);
    }
    return result;
  }

  public static Integer getInt(String roman) {
    int result = 0;
    char[] c = roman.toCharArray();
    for (int i = 0; i < roman.length(); i++) {
      result += ROMAN_MAP.get(c[i]);
      if (i > 0 && ROMAN_MAP.get(c[i]) > ROMAN_MAP.get(c[i - 1])) {
        result = result - 2 * ROMAN_MAP.get(c[i - 1]);
      }
    }
    return result;
  }

  public static class RoyalName {

    String name;
    String roman;
    int numeral;

    public RoyalName(String name, String roman, int numeral) {
      this.name = name;
      this.roman = roman;
      this.numeral = numeral;
    }

  }

  public static void main(String[] args) {
    List<String> inputList = List.of("Philippe VI",
        "Jean II",
        "Charles V",
        "Charles VI",
        "Charles VII",
        "Louis XI");
    System.out.println(sortRoman(inputList));
  }

}
