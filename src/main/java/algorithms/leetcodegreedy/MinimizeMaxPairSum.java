package algorithms.leetcodegreedy;

//Input: nums = [3,5,2,3]
//Output: 7
//Explanation: The elements can be paired up into pairs (3,3) and (5,2).
//The maximum pair sum is max(3+3, 5+2) = max(6, 7) = 7.

import java.util.Arrays;

//Input: nums = [3,5,4,2,4,6]
//Output: 8
//Explanation: The elements can be paired up into pairs (3,5), (4,4), and (6,2).
//The maximum pair sum is max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8.
public class MinimizeMaxPairSum {

    public int minPairSum(int[] nums) {
        //1 4 9 11
        // to minimize maximum pai sum, we should form pair of max number with min number, 2nd max number with 2nd min no and so on
        // so we can sort the array and use two pointers
        Arrays.sort(nums);
        int start =0; int end = nums.length-1;
        int maxSum = Integer.MIN_VALUE;
        while (start<end) { // nums.length is even
            maxSum = Math.max(maxSum, nums[start]+nums[end]);
            start++;
            end--;
        }
        return maxSum;
    }
}
