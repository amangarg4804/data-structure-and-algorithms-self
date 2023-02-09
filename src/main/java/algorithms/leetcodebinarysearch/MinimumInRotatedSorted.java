package algorithms.leetcodebinarysearch;

//Input: nums = [3,4,5,1,2]
//Output: 1
//Explanation: The original array was [1,2,3,4,5] rotated 3 times.
public class MinimumInRotatedSorted {

    public int findMin(int[] nums) {
        //  8 9 25 1 2 5 7
        int start = 0;
        int end = nums.length-1;
        while (start < end) { // run through the example where array as only 2 integers {25, 20} and {20, 25}
            int mid = start + (end-start)/2;
            if(nums[end] > nums[mid]) { // always search on right side in case of binary search problems because in case of array with 2 items. 0th index is mid
                // right side is sorted
                end = mid;
            } else {
                start = mid +1;
            }
        }
        return nums[start];
    }
}
