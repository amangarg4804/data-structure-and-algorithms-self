package algorithms.leetcode;

public class FirstAndLastIndex {

  public int[] searchRange(int[] nums, int target) {
    int[] range = new int[2];

    range[0] = calculateStartIndex(nums, target);
    range[1] = calculateEndIndex(nums, target);

    return range;
  }

  private int calculateStartIndex(int[] nums, int target) {
    int startIndex = -1;
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (target <= nums[mid]) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
      if (nums[mid] == target) {
        startIndex = mid;
      }
    }
    return startIndex;
  }

  private int calculateEndIndex(int[] nums, int target) {
    int endIndex = -1;
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (target >= nums[mid]) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
      if (nums[mid] == target) {
        endIndex = mid;
      }
    }
    return endIndex;
  }
}
