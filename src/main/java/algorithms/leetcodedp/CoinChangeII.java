package algorithms.leetcodedp;

import java.util.Arrays;

//You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
//
//Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
//
//You may assume that you have an infinite number of each kind of coin.
//The answer is guaranteed to fit into a signed 32-bit integer.
//
//Example 1:
//
//Input: amount = 5, coins = [1,2,5]
//Output: 4
//Explanation: there are four ways to make up the amount:
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
//Example 2:
//Input: amount = 3, coins = [2]
//Output: 0
//Explanation: the amount of 3 cannot be made up just with coins of 2.
//Example 3:
//Input: amount = 10, coins = [10]
//Output: 1
//Constraints:
//1 <= coins.length <= 300
//1 <= coins[i] <= 5000
//All the values of coins are unique.
//0 <= amount <= 5000
public class CoinChangeII {
    public int change(int amount, int[] coins) {
        // WRONG solution
        // amount 3, coins: 1, 2
        // with this solution we are adding duplicate coin combinations also to the answer
        // 1, 1,1 =3 , 2, 1 = 3, 1, 2, =3
        //
        if (amount == 0) {
            return 1;
        }
        if (amount < 0) {
            return 0;
        }
        int ways = 0;
        for (int i = 0; i < coins.length; i++) {
            int answer = change(amount - coins[i], coins);
            ways += answer;
        }
        return ways;
    }

    public int change2(int amount, int[] coins) {
        // TLE
        // if we don't want to add duplicates to solution, we can keep moving forward, never using the coins at previous indexes
        return change(amount, coins, 0);
    }

    private int change(int amount, int[] coins, int i) {

        if (amount == 0) {
            return 1;
        }
        if (amount < 0 || i == coins.length) {
            return 0;
        }
        // take this coin or don't take this coin.
        // if we take this coin, we don't increase the index, because we can reuse the same coin as many times as per problem statement
        int take = change(amount - coins[i], coins, i);
        int notTake = change(amount, coins, i + 1);// amount remains same, index increases
        int total = take + notTake;

        return total;
    }
    // counting dp- base cases are not set to 0, The recurrence relation usually only includes addition terms between states, so if the base cases were set to 0, then all states would be stuck at 0.
    public int change3(int amount, int[] coins) {
        // top down dp
        // if we don't want to add duplicates to solution, we can keep moving forward, never using the coins at previous indexes
        // two variables- amount and index- two dimensional dp
        int[][] dp = new int[amount + 1][coins.length]; // amount+1 because we want to save amount too
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        return dp(amount, coins, 0, dp);
    }

    private int dp(int amount, int[] coins, int i, int[][] dp) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0 || i == coins.length) {
            return 0;
        }
        if (dp[amount][i] != -1) {
            return dp[amount][i];
        }
        int take = dp(amount - coins[i], coins, i, dp);
        int notTake = dp(amount, coins, i + 1, dp);// amount remains same, index increases
        dp[amount][i] = take + notTake;
        return dp[amount][i];
        //Here n is the size of coins.
        //Time complexity: O(n⋅amount).
        //We take O(n⋅amount) to initialize the memo array.
        //There are total of O(n⋅amount) states that are computed.
        // There is no loop inside the recursive method. Each state is computed once and it takes an average of O(1) time
        // to compute the answer of a state using the smaller states. So, it takes O(n⋅amount) time to compute values for all the states.
        //Space complexity: O(n⋅amount).
        //
        //The memo array takes O(n⋅amountspace.
        //The recursion stack can grow up to a maximum size of O(n+amount) as we are reducing either the number of coins or the required amount
        // while going from one recursive call to another.
    }

    public int change4(int amount, int[] coins) {
        // bottom up
        // iteration in reverse order as top down
        // first we check for last index and when amount is 0
        // two-dimensional dp


        int[][] dp = new int[amount + 1][coins.length + 1];
        // NOTE: if the amount is 0, then as per the problem output should be 1
        for (int i = 0; i < coins.length + 1; i++) {
            dp[0][i] = 1;
        }
        // We don't start with amount 0 but 1. dp array for amount 0 is already populated in for loop above
        // how do we know that we don't start iterating from amount given in problem but 0? - in bottom up approach we start from base case
        for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {
            for (int i = coins.length - 1; i >= 0; i--) {
                int notTake = dp[currentAmount][i + 1];
                // we can only take this coin if currentAmount is less than or equal to the value of the coin
                int take = 0;
                if (currentAmount >= coins[i]) {
                    take = dp[currentAmount - coins[i]][i];
                }
                dp[currentAmount][i] = notTake + take;
            }
        }
        return dp[amount][0];
    }

    public int change5(int amount, int[] coins) {
        // bottom up with space optimization
        // as we can see from above solution, we only require values of i and i+1
        // dp[amount] will represent dp[amount][i]
        // We initialize dp[0] = 1 since we can always make up the amount 0 by not selecting any coins.
        // It acts as a base case. This is similar to setting dp[0][i] = 1 in the previous approach.


        int[] dp = new int[amount + 1];

        // NOTE: if the amount is 0, then as per the problem output should be 1
        dp[0] = 1;
        // We don't start with amount 0 but 1. dp array for amount 0 is already populated in for loop above
        // how do we know that we don't start iterating from amount given in problem but 0? - in bottom up approach we start from base case
        //It is important to note that changing the order of the nested loops would produce an incorrect answer. We must iterate over the coins in the outer loop, not the amount.
        //
        //If we change the ordering of the two loops, the outer loop would run from i = 1 to amount and the inner loop would execute from j = n - 1 to 0. We would perform the same operation dp[i] += dp[i - coins[j]] inside the loops.
        // After the ith iteration, dp[i] would store all the ways to make up the amount i using all the coins.
        //
        //Let's take an example where coins = [1, 2] and amount = 3. The correct answer for this case is 2 (1 + 1 + 1 and 1 + 2).
        //
        //However, if we look at the last iteration of the outer loop when i = 3, we will execute dp[3] = dp[3] + dp[3 - 1] when the first coin is selected
        // using the inner loop. dp[2] would be equal to 2 as there are two ways to make up amount 2 (1 + 1 and 2). This way we selected two cases 1 + 1 + 1 and 1 + 2.
        //We also execute dp[3] = dp[3] + dp[3 - 2] when the second coin is selected. dp[1] would be equal to 1 as there is just one way to make up amount 1.
        // This way we counted the 1 + 2 case. We counted the 1 + 2 case twice.
        //
        //Overall, the returned answer would be 3 which is incorrect. Switching the ordering of the loops returns all the permutations (1 + 1 + 1, 1 + 2, 2 + 1)
        // as the answer instead of the combinations where 1 + 2 and 2 + 1 are not considered as separate cases.
        for (int i = coins.length - 1; i >= 0; i--) {
            for (int currentAmount = coins[i]; currentAmount <= amount; currentAmount++) {
                dp[currentAmount]+= dp[currentAmount-coins[i]];
            }
        }
        return dp[amount];
    }
}
