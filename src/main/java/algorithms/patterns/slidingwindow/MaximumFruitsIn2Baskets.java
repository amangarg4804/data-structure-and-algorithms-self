package algorithms.patterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MaximumFruitsIn2Baskets {

  public static void main(String[] args) {
    System.out.println(findMax(new char[]{'A', 'B', 'C', 'A', 'C'}));
    System.out.println(findMax(new char[]{'A', 'B', 'C', 'B', 'B', 'C'}));
  }

  public static int findMax(char[] arr) {
    Map<Character, Integer> map = new HashMap<>();
    int resultLength = 0;
    int windowStart = 0;
    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
      map.put(arr[windowEnd], map.getOrDefault(arr[windowEnd], 1));
      while (map.size() > 2) {
        Integer count = map.get(arr[windowStart]);
        if (count == 1) {
          map.remove(arr[windowStart]);
        } else {
          map.put(arr[windowStart], count - 1);
        }
        windowStart++;
      }
      resultLength = Math.max(resultLength, windowEnd - windowStart + 1);
    }
    return resultLength;
  }
}
