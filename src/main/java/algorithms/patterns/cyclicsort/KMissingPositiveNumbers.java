package algorithms.patterns.cyclicsort;

import java.util.ArrayList;
import java.util.List;

public class KMissingPositiveNumbers {

  public static void main(String[] args) {
    System.out.println(findMissingNumbers(new int[]{3, -1, 4, 5, 5}, 3));
    System.out.println(findMissingNumbers(new int[]{2, 3, 4}, 3));
    System.out.println(findMissingNumbers(new int[]{-2, -3, 4}, 2));
    System.out.println(findMissingNumbers(new int[]{2, 1, 3, 8, 5}, 2));
  }

  public static List<Integer> findMissingNumbers(int[] nums, int k) {
    int i = 0;
    List<Integer> list = new ArrayList<>();
    while (i < nums.length) {
      int j = nums[i] - 1;
      if (j >= 0 && j < nums.length && nums[i] != nums[j]) {
        swap(nums, i, j);
      } else {
        i++;
      }
    }

    return list;
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}
