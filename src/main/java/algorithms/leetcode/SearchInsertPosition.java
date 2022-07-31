package algorithms.leetcode;

// Given a sorted array of distinct integers and a target value, return the index if the target is found.
// If not, return the index where it would be if it were inserted in order.
public class SearchInsertPosition {

  public int searchInsert(int[] nums, int target) {
    if (target <= nums[0]) {
      return 0;
    }
    if (target > nums[nums.length - 1]) {
      return nums.length;
    }
    int start = 0;
    int end = nums.length - 1;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      if (nums[mid] < target) {
        start = mid + 1;
      } else {
        end = mid;
      }
    }

    return start;
  }
}
