package algorithms.patterns.cyclicsort;

import java.util.Arrays;

public class CorruptPair {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(findNumbers(new int[]{3, 1, 2, 5, 2})));
    System.out.println(Arrays.toString(findNumbers(new int[]{3, 1, 2, 3, 6, 4})));
  }

  public static int[] findNumbers(int[] nums) {
    int i = 0;
    int duplicate = -1;
    int missing = -1;
    while (i < nums.length) {
      int j = nums[i] - 1;
      if (nums[i] != nums[j]) {
        swap(nums, i, j);
      } else {
        if (i!=j) {
          duplicate = nums[i];
        }
          i++;
      }
    }

    for (int j = 0; j < nums.length; j++) {
      if (nums[j] != j + 1) {
        missing = j+1;
        break;
      }
    }
    return new int[]{duplicate, missing};
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[j];
    nums[j] = nums[i];
    nums[i] = temp;
  }
}
