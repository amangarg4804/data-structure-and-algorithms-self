package algorithms.leetcodedp;

import java.util.Arrays;

//You have n dice, and each die has k faces numbered from 1 to k.
//Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice,
// so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 10^9 + 7.
//Example 1:
//Input: n = 1, k = 6, target = 3
//Output: 1
//Explanation: You throw one die with 6 faces.
//There is only one way to get a sum of 3.
//Example 2:
//Input: n = 2, k = 6, target = 7
//Output: 6
//Explanation: You throw two dice, each with 6 faces.
//There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
//Example 3:
//Input: n = 30, k = 30, target = 500
//Output: 222616187
//Explanation: The answer must be returned modulo 109 + 7.
//Constraints:
//1 <= n, k <= 30
//1 <= target <= 1000
public class DiceRollsTargetSum {
    public int numRollsToTarget(int n, int k, int target) {
        // TLE
        if(n==0) {
            return target==0? 1: 0;
        }
        if(target < 0){
            return 0;
        }
        int count =0;
        for(int i=1; i<=k; i++) {
            count += numRollsToTarget(n-1, k, target-i);
        }
        return count;
    }

    public int numRollsToTarget2(int n, int k, int target) {
        // n and target both change. k doesn't change. So 2-dimensional dp
        int[][] dp = new int[n+1][target+1];
        for(int[] i: dp) {
            Arrays.fill(i, -1);
        }
        return dp(n, k, target, dp);
    }
    private static final int MOD = 1000000007;
    private int dp(int n, int k, int target, int[][] dp) {
        if(n==0) {
            return target==0? 1: 0;
        }
        if(target < 0){
            return 0;
        }
        if(dp[n][target]!=-1) {
            return dp[n][target];
        }
        long count =0;
        for(int i=1; i<=k; i++) {
            count += dp(n-1, k, target-i, dp);
        }
        dp[n][target] = (int)(count % MOD);
        return dp[n][target];
        //Time comolexity: The for loop iterates k times, leading to a factor of k in the time complexity.
        //Each recursive call dp(n-1, k, target-i) reduces the value of n by 1, moving towards the base cases where n == 0.
        //The depth of the recursion tree is bounded by n, as each recursive call decrements n.
        //Therefore, the total number of recursive calls is roughly proportional to n * k * target,
        // giving the overall time complexity of O(n * target * k).
        // Space Complexity: The combined space complexity from the DP table and the recursive stack is O(n * target) + O(n) = O(n * target),
        // as the additional O(n) term doesn't affect the asymptotic order of growth.
    }

    public int numRollsToTarget3(int n, int k, int target) {
        // n and target both change. k doesn't change. So 2-dimensional dp
        int[][] dp = new int[n+1][target+1];
        for(int i=n-1; i >=1; i--) {
            // for first dice, how can target vary? - Target can vary from (target- minimum face value) to (target - max face value)
            for(int j=1; j<=k; j++) {

            }
        }
        return 0;
    }
}
