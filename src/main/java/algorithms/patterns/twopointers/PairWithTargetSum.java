package algorithms.patterns.twopointers;

import java.util.Arrays;

public class PairWithTargetSum {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(search(new int[]{1, 2, 3, 4, 6}, 6)));
    System.out.println(Arrays.toString(search(new int[]{2, 5, 9, 11}, 11)));
  }

  public static int[] search(int[] arr, int targetSum) {
    int left = 0;
    int right = arr.length - 1;

    while (left < right) {
      int currentSum = arr[left] + arr[right];
      if (currentSum > targetSum) {
        right--;
      } else if (currentSum < targetSum) {
        left++;
      } else {
        return new int[]{left, right};
      }
    }
    return new int[]{-1, -1};
  }
}
