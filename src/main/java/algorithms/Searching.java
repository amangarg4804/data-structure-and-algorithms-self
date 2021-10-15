package algorithms;

public class Searching {

  // Binary Search
  public int search(int[] nums, int target) {

    int low = 0;
    int high = nums.length - 1;
    int mid;
    while (low <= high) {
      mid = (low + high) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      if (target < nums[mid]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return -1;
  }

}
