package algorithms.leetcodedp;

import java.lang.reflect.Array;
import java.util.Arrays;

//Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
//
//Example 1:
//Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
//Output: 3
//Explanation: The repeated subarray with maximum length is [3,2,1].
//Example 2:
//Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
//Output: 5
//Explanation: The repeated subarray with maximum length is [0,0,0,0,0].
//Constraints:
//1 <= nums1.length, nums2.length <= 1000
//0 <= nums1[i], nums2[i] <= 100
public class MaxLengthRepeatedSubarray {

    public int findLength(int[] nums1, int[] nums2) {
        int max = Integer.MIN_VALUE;
        // arrays have same length
        for(int i=0; i < nums1.length; i++) {
            for(int j=0; j< nums2.length; j++) {
                int subArrayLength = findLength(nums1, nums2, i, j);
                max = Math.max(max, subArrayLength);
            }
        }
        return max;
    }

    private int findLength(int[] nums1, int[] nums2, int i, int j) {
        // TLE 51 / 53 testcases passed
        if(i==nums1.length || j ==nums2.length) {
            return 0;
        }
        if(nums1[i]!=nums2[j]) {
            return 0;
        }
        return 1+ findLength(nums1, nums2, i+1, j+1);
    }

    public int findLength2(int[] nums1, int[] nums2) {
        int max = Integer.MIN_VALUE;
        // arrays have same length
        // i and j vary so two dimensional dp
        int[][] dp = new int[nums1.length][nums2.length];
        for(int[] i : dp) {
            Arrays.fill(i, -1);
        }
        for(int i=0; i < nums1.length; i++) {
            for(int j=0; j< nums2.length; j++) {
                int subArrayLength = dp(nums1, nums2, i, j, dp);
                max = Math.max(max, subArrayLength);
            }
        }
        return max;
    }

    private int dp(int[] nums1, int[] nums2, int i, int j, int[][] dp) {
        if(i==nums1.length || j ==nums2.length) {
            return 0;
        }
        if(nums1[i]!=nums2[j]) {
            return 0;
        }
        if(dp[i][j] !=-1) {
            return dp[i][j];
        }
        dp[i][j] = 1+ dp(nums1, nums2, i+1, j+1, dp);
        return dp[i][j];
    }
}
