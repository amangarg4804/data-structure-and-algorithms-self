package algorithms.leetcodedp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
//Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
//You may assume that you have an infinite number of each kind of coin.
//
//Example 1:
//Input: coins = [1,2,5], amount = 11
//Output: 3
//Explanation: 11 = 5 + 5 + 1
//Example 2:
//
//Input: coins = [2], amount = 3
//Output: -1
//Example 3:
//
//Input: coins = [1], amount = 0
//Output: 0
//
//Constraints:
//1 <= coins.length <= 12
//1 <= coins[i] <= 2^31 - 1
//0 <= amount <= 10^4
public class CoinChange {
    int[] dp;

    public int coinChange(int[] coins, int amount) {
        // we will have to take each coin at each step
        // A small optimization could be to not take coins with value more than remaining amount
        // when amount reaches 0 , it means we found a possible combination of coins
        // base case
        // recursive relation = Math.min(take nth coin, amount-nth coin value) for all values for n from coins[0] to coint[n-1], where n is length of coins array
        dp = new int[amount + 1]; // we can store result for each amount value;

        return dpTopDown(coins, amount);
    }

    private int dpTopDown(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        //Input: coins = [2], amount = 3
        //Output: -1
        if (dp[amount] == 0) {      // got TLE when initializing array with -1, and changing the condition to dp[amount] =-1, because the condition -1 was true multiple time and the code kept calculating values when it should have skipped calculations
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i++) {
                int result = dpTopDown(coins, amount - coins[i]);
                // result could be 0- when the amount =coins[i] becomes 0.e.g in case coins =[2] and amount =2
                // result > 0, would mean that it took more than 1 coin but we found the result;
                // in case result < 0, we didn't find the result
                if (result >= 0 && result < min) {
                    min = 1 + result;
                }
            }
            dp[amount] = min == Integer.MAX_VALUE ? -1 : min;
        }
        return dp[amount];
        //Complexity Analysis
        //Time complexity : O(S*n). where S is the amount, n is denomination count.
        //In the worst case the recursive tree of the algorithm has height of S (think coin value 1 and amount 100 or 200 or x) and the algorithm solves only S subproblems because it caches precalculated solutions in a table.
        // Each subproblem is computed with n iterations, one by coin denomination. Therefore there is O(S*n) time complexity.
        //
        //Space complexity : O(S), where S is the amount to change
        //We use extra space for the memoization table.
    }


    public int coinChange2(int[] coins, int amount) {
        // using BFS
        // at each level of BFS, try all coins
        // if amount becomes 0, return the level
        if (amount == 0) {
            return 0;
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(amount);
        int level = 1;
        boolean[] visited = new boolean[amount + 1];
        visited[amount] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int remainingAmountOption = q.poll(); // at every level, there can be multiple options of remaining amount depending on which coin we chose
                for (int coin : coins) {
                    int remaining = remainingAmountOption - coin;
                    if (remaining == 0) {
                        return level;
                    }
                    if (remaining > 0 && !visited[remaining]) {
                        q.offer(remaining);
                        visited[remaining] = true;
                    }
                }
            }
            level++;
        }
        return -1;
//Complexity Analysis
        //Time complexity : O(S*n). where S is the amount, n is denomination count.
        //In the worst case the BFS has height of S (think coin value 1 and amount 100 or 200 or x)
        // Each subproblem is computed with n iterations, one by coin denomination. Therefore there is O(S*n) time complexity.
        //
        //Space complexity : O(S), where S is the amount to change
        //We use extra space for the visited and queue
    }

}
