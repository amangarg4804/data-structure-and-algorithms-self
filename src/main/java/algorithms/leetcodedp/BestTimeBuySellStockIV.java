package algorithms.leetcodedp;

//You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
//
//Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.
//
//Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
//
//Example 1:
//Input: k = 2, prices = [2,4,1]
//Output: 2
//Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
//Example 2:
//Input: k = 2, prices = [3,2,6,5,0,3]
//Output: 7
//Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//
//Constraints:
//1 <= k <= 100
//1 <= prices.length <= 1000
//0 <= prices[i] <= 1000
public class BestTimeBuySellStockIV {
    public int maxProfit(int k, int[] prices) {
        // we require a function, a recurrence relation and base cases
        // what is varying? - either we are holding the stock or not
        // if we are holding, we can either we can sell or not sell
        // if we are not holding, we can either buy or not buy
        // whenever we sell, profit increases by prices[i]
        // whenever we buy , profit decreases by prices[i]
        //we use int variable holding.0 - not holding. 1-holding. initially we aren't holding
//        return topDown(k, prices, 0, 0);
        // 3 dimentional dp. transactions, holding, index
        int[][][] dp = new int[prices.length][k+1][2];
        return topDownDp(k, prices, 0, 0, dp);
    }

    int topDown(int transactions, int[] prices, int holding, int index) {
        if(transactions ==0 || index ==prices.length) { //no more transactions allowed or we are out of days
            return 0;
        }
        // 3 options-
        // Options 1: neither buy nor sell - index moves to next day nothing else changes
        int notBuyOrSell = topDown(transactions, prices, holding, index+1);
        int buyOrSell = 0;
        // Option 2: Buy (only allowed when holding = 0) (you must sell the stock before you buy again      )
        if(holding == 0) {
            buyOrSell = -prices[index] +  topDown(transactions, prices, 1, index+1);
        } else {
            // Option 3: Sell (only allowed when holding = 1)
            // a sell decreases transaction
            buyOrSell = prices[index] +  topDown(transactions-1, prices, 0, index+1);
        }
        return Math.max(notBuyOrSell, buyOrSell);
    }
    int topDownDp(int transactions, int[] prices, int holding, int index, int[][][] dp) {
        if(transactions ==0 || index ==prices.length) { //no more transactions allowed or we are out of days
            return 0;
        }
        if(dp[index][transactions][holding]!=0) {
            return dp[index][transactions][holding];
        }

        // 3 options-
        // Options 1: neither buy nor sell - index moves to next day nothing else changes
        int notBuyOrSell = topDownDp(transactions, prices, holding, index+1, dp);
        int buyOrSell = 0;
        // Option 2: Buy (only allowed when holding = 0) (you must sell the stock before you buy again      )
        if(holding == 0) {
            buyOrSell = -prices[index] +  topDownDp(transactions, prices, 1, index+1, dp);
        } else {
            // Option 3: Sell (only allowed when holding = 1)
            // a sell decreases transaction
            buyOrSell = prices[index] +  topDownDp(transactions-1, prices, 0, index+1, dp);
        }
        dp[index][transactions][holding] =Math.max(notBuyOrSell, buyOrSell);
        return dp[index][transactions][holding];
        // The time and space complexity of this problem for both implementations is the number of states since the recurrence relation is just a constant time formula. If
        // n = prices.length, then this means the time and space complexity is
        // O(n⋅k⋅2)=O(n⋅k).
    }

    public int maxProfit2(int k, int[] prices) {
        //bottom up dp
        // we start in reverse direction, i.e. when transaction remaining is 1. For 0, by default it is 0
        int[][][] dp = new int[prices.length +1][k +1][2];

        for(int i=prices.length-1; i >=0; i--) {
            for( int trasactionsRemaining =1; trasactionsRemaining <=k; trasactionsRemaining++) {
                for(int holding =0; holding < 2; holding ++) {
                    int dontDoAnything = dp[i+1][trasactionsRemaining][holding];
                    int buyOrSell;
                    if(holding ==0) {
                        // buy
                        buyOrSell = -prices[i] + dp[i+1][trasactionsRemaining][1];
                    } else {
                        //sell
                        buyOrSell = prices[i] + dp[i+1][trasactionsRemaining-1][0];
                    }
                    dp[i][trasactionsRemaining][holding] = Math.max(dontDoAnything, buyOrSell);
                }
            }
        }
        return dp[0][k][0]; // k transactions remaining
    }

    public int maxProfit3(int k, int[] prices) {
        //bottom up dp
        int[][][] dp = new int[prices.length +1][k +1][2];
        for(int i=prices.length-1; i >=0; i--) {
            for( int trasactionsRemaining =k-1; trasactionsRemaining >=0; trasactionsRemaining--) { // notice k-1 because of 0 based index
                for(int holding =0; holding < 2; holding ++) {
                    int dontDoAnything = dp[i+1][trasactionsRemaining][holding];
                    int buyOrSell;
                    if(holding ==0) {
                        // buy
                        buyOrSell = -prices[i] + dp[i+1][trasactionsRemaining][1];
                    } else {
                        //sell
                        buyOrSell = prices[i] + dp[i+1][trasactionsRemaining+1][0];
                    }
                    dp[i][trasactionsRemaining][holding] = Math.max(dontDoAnything, buyOrSell);
                }
            }
        }
        return dp[0][0][0]; // k transactions remaining
    }

    public int maxProfit4(int k, int[] prices) {
        //bottom up dp
        int[][][] dp = new int[prices.length +1][k +1][2];
        for(int i=prices.length-1; i >=0; i--) {
            for( int trasactionsRemaining =k-1; trasactionsRemaining >=0; trasactionsRemaining--) { // notice k-1 because of 0 based index
                for(int holding =0; holding < 2; holding ++) {
                    int dontDoAnything = dp[i+1][trasactionsRemaining][holding];
                    int buyOrSell;
                    if(holding ==0) {
                        // buy
                        buyOrSell = -prices[i] + dp[i+1][trasactionsRemaining][1];
                    } else {
                        //sell
                        buyOrSell = prices[i] + dp[i+1][trasactionsRemaining+1][0];
                    }
                    dp[i][trasactionsRemaining][holding] = Math.max(dontDoAnything, buyOrSell);
                }
            }
        }
        return dp[0][0][0]; // k transactions remaining
    }
}
