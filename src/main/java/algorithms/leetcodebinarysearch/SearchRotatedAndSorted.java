package algorithms.leetcodebinarysearch;

public class SearchRotatedAndSorted {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            /*if(nums[left] < nums[right]) {
                // array is sorted, do normal binary search
                if(target < nums[mid]) {
                    right = mid -1;
                } else {
                    left = mid +1;
                }
            } else   */ //This block is not required

            if (nums[right] > nums[mid]) { // nums array has distinct values

                // right part is sorted in ascending
                if (target > nums[mid] && target <= nums[right]) { // [4,5,6,7,0,1,2]
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {  // This condition must come last because our mid is lower value, in case of even number of integers in array. Otherwise we won't get correct result for array {3,1}
                // left part is sorted in ascending order
                // [4,5,6,7,0,1,2], assume mid is integer 6
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
