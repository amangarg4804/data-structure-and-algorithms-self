package algorithms.leetcodedp;

import java.util.Arrays;

public class CoinChangeII {
    public int change(int amount, int[] coins) {
        // wrong solution
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

       if(amount==0) {
           return 1;
       }
       if(amount < 0 || i ==coins.length) {
           return 0;
       }
       // take this coin or don't take this coin.
        // if we take this coin, we don't increase the index, because we can reuse the same coin as many times as per problem statement
        int take = change(amount-coins[i], coins, i);
       int notTake = change(amount, coins, i+1);// amount remains same, index increases
        int total = take + notTake;

        return total;
    }

    public int change3(int amount, int[] coins) {
        // top down dp
        // if we don't want to add duplicates to solution, we can keep moving forward, never using the coins at previous indexes
        // two variables- amount and index- two dimensional dp
        int[][] dp = new int[amount+1][coins.length]; // amount+1 because we want to save amount too
        for(int[]a : dp) {
            Arrays.fill(a, -1);
        }
        return dp(amount, coins, 0, dp);
    }

    private int dp(int amount, int[] coins, int i, int[][] dp) {
        if(amount==0) {
            return 1;
        }
        if(amount < 0 || i ==coins.length) {
            return 0;
        }
        if(dp[amount][i]!=-1) {
            return dp[amount][i];
        }
        int take = dp(amount-coins[i], coins, i, dp);
        int notTake = dp(amount, coins, i+1, dp);// amount remains same, index increases
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
        //The memo array takes O(n⋅amount)O(n \cdot \text{amount})O(n⋅amount) space.
        //The recursion stack can grow up to a maximum size of O(n+amount) as we are reducing either the number of coins or the required amount
        // while going from one recursive call to another.
    }
}
