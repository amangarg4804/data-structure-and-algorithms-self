package algorithms.patterns.twopointers;

import java.util.Arrays;

public class TripletSumCloseToTarget {

  public static void main(String[] args) {
    System.out.println(tripletSum(new int[]{-2, 0, 1, 2}, 2));
    System.out.println(tripletSum(new int[]{-3, -1, 1, 2}, 1));
    System.out.println(tripletSum(new int[]{1, 0, 1, 1}, 100));

    System.out.println(tripletSum(new int[]{-1, 2, 1, -4}, 1));
  }

  static int tripletSum(int[] arr, int targetSum) {
    int smallestDiff = Integer.MAX_VALUE;
    Arrays.sort(arr);
    for (int i = 0; i < arr.length - 2; i++) {
      int left = i + 1;
      int right = arr.length - 1;
      while (left < right) {
        int currentDiff = targetSum - arr[i] - arr[left] - arr[right];
        if (currentDiff == 0) {
          return targetSum - currentDiff; // triplet with exact sum
        }
        if (Math.abs(currentDiff) < Math.abs(smallestDiff)) {
          smallestDiff = currentDiff;
        }
        if (currentDiff > 0) {
          left++;
        } else {
          right--;
        }
      }
    }
    return targetSum - smallestDiff;
  }
}
