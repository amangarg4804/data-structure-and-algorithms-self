package algorithms.patterns.cyclicsort;

// We are given an array containing ‘n’ distinct numbers taken from the range 0 to ‘n’.
// Since the array has only ‘n’ numbers out of the total ‘n+1’ numbers, find the missing number.
public class MissingNumber {

  public static void main(String[] args) {
    System.out.println(missingNumber(new int[]{4, 0, 3, 1}));
    System.out.println(missingNumber(new int[]{8, 3, 5, 2, 4, 6, 0}));
  }

  public static int missingNumber(int[] nums) {
    int i = 0;

    while (i < nums.length) {
      if (nums[i] < nums.length && i != nums[i]) {
        swap(nums, i, nums[i]);
      } else {
        i++;
      }
    }

    for (int j = 0; j < nums.length; j++) {
      if (j != nums[j]) {
        return j;
      }
    }
    return nums.length;
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[j];
    nums[j] = nums[i];
    nums[i] = temp;
  }

}
