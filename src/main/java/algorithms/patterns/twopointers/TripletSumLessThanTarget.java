package algorithms.patterns.twopointers;

import java.util.Arrays;

// Given an array arr of unsorted numbers and a target sum, count all triplets in it such that arr[i] + arr[j] + arr[k] < target where i, j, and k are three different indices.
// Write a function to return the count of such triplets.
public class TripletSumLessThanTarget {

  public static void main(String[] args) {
    System.out.println(searchTriplets(new int[]{-1, 0, 2, 3}, 3));
    System.out.println(searchTriplets(new int[]{-1, 4, 2, 1, 3}, 5));
  }

  public static int searchTriplets(int[] arr, int target) {
    Arrays.sort(arr);
    int count = 0;
    for (int i = 0; i < arr.length - 2; i++) {
      int left = i + 1;
      int right = arr[arr.length - 1];
      while (left < right) {
        int diff = arr[i] + arr[left] + arr[right] - target;
        if (diff < 0) {
          // because the array is sorted. arr[right] is greater than arr[left].
            // So, arr[right] can be replaced with any value between left and right to get a sum less that current sum
          count += right - left;
          left++;
        } else {
          right--;
        }
      }
    }
    return count;
  }

}
