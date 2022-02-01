package algorithms.patterns.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given an array of unsorted numbers and a target number, find all unique quadruplets in it,
// whose sum is equal to the target number.
public class QuadrupleSumToTarget {

  public static void main(String[] args) {
    System.out.println(searchQuadruplets(new int[]{4, 1, 2, -1, 1, -3}, 1));
    System.out.println(searchQuadruplets(new int[]{2, 0, -1, 1, -2, 2}, 2));
  }

  public static List<List<Integer>> searchQuadruplets(int[] arr, int sum) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(arr);
    for (int i = 0; i < arr.length - 3; i++) {
      if (i > 0 && arr[i] == arr[i - 1]) {
        continue;
      }
      for (int j = i + 1; j < arr.length - 2; j++) {
        if (j > i + 1 && arr[j] == arr[j - 1]) {
          continue;
        }
        searchQuadruplets(arr, sum, list, i, j);
      }
    }
    return list;
  }

  private static void searchQuadruplets(int[] arr, int targetSum, List<List<Integer>> list,
      int first, int second) {
    int left = second + 1;
    int right = arr.length - 1;

    while (left < right) {
      int currentSum = arr[first] + arr[second] + arr[left] + arr[right];
      if (currentSum < targetSum) {
        left++;
      } else if (currentSum > targetSum) {
        right--;
      } else {
        list.add(Arrays.asList(arr[first], arr[second], arr[left], arr[right]));
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
