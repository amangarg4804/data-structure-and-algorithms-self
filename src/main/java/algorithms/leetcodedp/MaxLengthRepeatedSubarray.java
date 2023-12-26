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
        //The function dp is called for each pair of indices (i, j) in the nested loops of the findLength function.
        // In the worst case, the function dp is called for every combination of i and j. The recursion explores all possible pairs of indices in nums1 and nums2.
        //
        //The time complexity of this algorithm is O(m * n * min(m, n)), where m is the length of nums1 and n is the length of nums2.
        // This is because there are m * n unique pairs of indices (i, j) to explore, and for each pair, the recursion may take up to min(m, n)
        // steps to reach the base case.
        //
        //The space complexity is also O(m * n) due to the 2D array dp, which is used for memoization.
    }

    public int findLength3(int[] nums1, int[] nums2) {
        int max = Integer.MIN_VALUE;
        // arrays have same length
        // i and j vary so two dimensional dp
        int[][] dp = new int[nums1.length+1][nums2.length+1];

        for(int i=nums1.length-1; i >= 0; i--) {
            for(int j=nums2.length-1; j >=0; j--) {
                // 0 1 2
                // 1 2 3
                // 3 2 3
                // dp[2][2]=1, dp[2][1] =0, dp[2][0] = 1
                // dp[1][2]=0, dp[1][1] = 2
                if(nums1[i] ==nums2[j]) {
                    dp[i][j] = dp[i+1][j+1] +1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
        //time complexity is O(m * n), where m =length of nums1 and n=length of nums2.
    }



}
