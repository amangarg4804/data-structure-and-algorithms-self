package algorithms.leetcodebinarysearch;

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

  public int[] searchRange2(int[] nums, int target) {
    // using a single method with boolean flag for both indexes
    if(nums.length==0) {
      return new int[]{-1, -1};
    }
    int firstOccurrence = findOccurrence(nums, target, true);
    if(firstOccurrence ==-1) {
      return new int[]{-1, -1};
    }
    int lastOccurrence = findOccurrence(nums, target, false);
    return new int[]{firstOccurrence, lastOccurrence};
  }

  private int findOccurrence(int[] nums, int target, boolean isFirst) {
    int start =0;
    int end = nums.length -1;
    int ans =-1;
    while (start <= end) {
      int mid = start + (end - start)/2;
      if(nums[mid]==target) {
        ans = mid;
        if(isFirst) {
          end = mid -1; // because we want to find first occurrence, we change end to mid -1 to search left of the array

        } else {
          start = mid+1;
        }
      } else if(nums[mid] >  target) {
        end = mid -1;
      } else {
        start = mid+1;
      }
    }
    return ans;
  }
  //Binary Search and Bidirectional Scan
  //
  //A naive way to use binary search to find the first and the last position of a target is to first determine the index of any occurrence of the given target.
  // Suppose we know that the target is at the index i in the array. From there on, we do a linear scan to the left
  // and keep going until we find the first occurrence of this target. Similarly, we do a linear scan to the right to find the last position. This works just fine.
  // However, in the worst case when our entire array (or say 90% or more of it) is filled with the target, then this is a linear-time algorithm.
}
