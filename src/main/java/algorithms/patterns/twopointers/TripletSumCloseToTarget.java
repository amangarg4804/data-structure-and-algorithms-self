package algorithms.patterns.twopointers;

import java.util.Arrays;

public class TripletSumCloseToTarget {

  public static void main(String[] args) {
    System.out.println(tripletSum(new int[]{-2, 0, 1, 2}, 2));
    System.out.println(tripletSum(new int[]{-3, -1, 1, 2}, 1));
    System.out.println(tripletSum(new int[]{1, 0, 1, 1}, 100));

    System.out.println(tripletSum(new int[]{-1,2,1,-4}, 1));
  }

  static int tripletSum(int[] arr, int targetSum) {
    int smallestDiff = Integer.MAX_VALUE;
    int tripletSum = Integer.MAX_VALUE;
    Arrays.sort(arr);

    for (int i = 0; i < arr.length - 2; i++) {
      int closestSumWithCurrentIndex = tripletSum(arr,    i, tripletSum, smallestDiff, targetSum);
      int diff = Math.min(Math.abs(closestSumWithCurrentIndex - targetSum),
          Math.abs(targetSum - closestSumWithCurrentIndex));
      if (diff < smallestDiff) {
        smallestDiff = diff;
        tripletSum = closestSumWithCurrentIndex;
      } else if (diff == smallestDiff) {
        tripletSum = Math.min(tripletSum, closestSumWithCurrentIndex);

      }
    }
    return tripletSum;

  }

  private static int tripletSum(int[] arr, int currentIndex, int tripletSum, int smallestDiff,
      int targetSum) {
    int left = currentIndex + 1;
    int right = currentIndex + 2;
    while (right < arr.length) {
      int diff = Math.abs(targetSum - arr[currentIndex] - arr[left] - arr[right]);
      if (diff <= smallestDiff) {
        smallestDiff = diff;
        tripletSum = arr[currentIndex] + arr[left] + arr[right];
      } else {
        break;
      }
      right++;
      left++;
    }
    return tripletSum;
  }
}
