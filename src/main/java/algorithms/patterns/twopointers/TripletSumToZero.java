package algorithms.patterns.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
public class TripletSumToZero {

  public static void main(String[] args) {
    System.out.println(searchTriplets(new int[]{-3, 0, 1, 2, -1, 1, -2}));
    System.out.println(searchTriplets(new int[]{-5, 2, -1, -2, 3}));
  }

  public static List<List<Integer>> searchTriplets(int[] arr) {
    Arrays.sort(arr); // -3, -2, -1, 0, 1, 1, 2
    List<List<Integer>> triplets = new ArrayList<>();
    for (int i = 0; i < arr.length - 2; i++) {
      if (arr[i + 1] == arr[i]) {
        continue;
      }
      searchPair(arr, -arr[i], i + 1, triplets);
    }
    return triplets;
  }

  public static void searchPair(int[] arr, int targetSum, int left,
      List<List<Integer>> triplets) {
    int right = arr.length - 1;
    while (left < right) {
      int currentSum = arr[left] + arr[right];
      if (currentSum < targetSum) {
        left++;
      } else if (currentSum > targetSum) {
        right--;
      } else {
        triplets.add(List.of(-targetSum, arr[left], arr[right]));
        left++;
        right--;
        while (left < right && arr[left] == arr[left - 1]) {
          left++;
        }
        while (left < right && arr[right] == arr[right + 1]) {
          right--;
        }
      }
    }
  }

}
