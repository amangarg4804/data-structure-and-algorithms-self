package algorithms.leetcode;

import java.util.Arrays;

public class NextPermutation {

  public static void main(String[] args) {

  }

  public void nextPermutation(int[] nums) {
    if(nums.length < 2) {
      return;
    }
    int fixed = nums.length-2;
    while (fixed >=0 && nums[fixed] >= nums[fixed+1]) {
      fixed--;
    }

    if(fixed >=0) {

      for(int i = nums.length -1; i> fixed; i--) {
        if(nums[i] > nums[fixed]) {
          swap(nums, i, fixed);
          break;
        }
      }
    }
    reverse(nums, fixed +1);

  }

  private void reverse(int[] nums, int start) {
    int end = nums.length -1;
    while (start < end) {
      swap(nums, start, end);
      start++;
      end--;
    }
  }

  private void swap(int[] nums, int left, int right) {
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;
  }
}
