package algorithms.patterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class ReplaceOnesWithZeros {

  public static void main(String[] args) {
    System.out.println(findLength(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 2));
    System.out.println(findLength(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}, 3));

    System.out
        .println(findLengthWithoutUsingExtraSpace(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 2));
    System.out.println(
        findLengthWithoutUsingExtraSpace(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}, 3));
  }

  public static int findLength(int[] arr, int k) {
    // 1 1 0 0 1
    //k=2
    Map<Integer, Integer> countInCurrentWindow = new HashMap<>();
    int maxLength = 0;
    int windowStart = 0;
    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
      int right = arr[windowEnd];
      countInCurrentWindow.put(right, countInCurrentWindow.getOrDefault(right, 0) + 1);
      if (windowEnd - windowStart + 1 - countInCurrentWindow.getOrDefault(1, 0) > k) {
        int left = arr[windowStart];
        countInCurrentWindow.put(left, countInCurrentWindow.get(left) - 1);
        windowStart++;
      }
      maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
    }
    return maxLength;
  }

  public static int findLengthWithoutUsingExtraSpace(int[] arr, int k) {
    // 1 1 0 0 1
    //k=2
    int maxOnesCount = 0;
    int maxLength = 0;
    int windowStart = 0;
    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
      if (arr[windowEnd] == 1) {
        maxOnesCount++;
      }
      if (windowEnd - windowStart + 1 - maxOnesCount > k) {
        if (arr[windowStart] == 1) {
          maxOnesCount--;
        }
        windowStart++;
      }
      maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
    }
    return maxLength;
  }

}
