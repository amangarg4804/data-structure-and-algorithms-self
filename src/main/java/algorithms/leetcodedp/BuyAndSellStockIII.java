package algorithms.leetcodedp;

import java.util.Arrays;

//You are given an array prices where prices[i] is the price of a given stock on the ith day.
//Find the maximum profit you can achieve. You may complete at most two transactions.
//Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
//
//Example 1:
//Input: prices = [3,3,5,0,0,3,1,4]
//Output: 6
//Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
//Example 2:
//Input: prices = [1,2,3,4,5]
//Output: 4
//Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
//Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
//Example 3:
//Input: prices = [7,6,4,3,1]
//Output: 0
//Explanation: In this case, no transaction is done, i.e. max profit = 0.
//
//Constraints:
//1 <= prices.length <= 105
//0 <= prices[i] <= 105
public class BuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        // top down dp
        int[][][] dp  = new int[prices.length][3][2];
        // 3 dimension dp
        // variables- index, transactionsRemaining and holding
        for(int[][] arr1: dp) {
            for(int[] arr2: arr1) {
                Arrays.fill(arr2, -1);
            }
        }
        return maxProfit(prices,0, 0, 2, dp);
    }

    private int maxProfit(int[] prices, int i, int holding, int transactionsRemaining, int[][][] dp) {
        if(transactionsRemaining ==0 || i == prices.length) {
            return 0;
        }
        if(dp[i][transactionsRemaining][holding] !=-1) {
            return dp[i][transactionsRemaining][holding];
        }
        int notBuyOrSell = maxProfit(prices, i+1, holding, transactionsRemaining, dp);
        int buyOrSell;
        if(holding==0) {
            //buy
            buyOrSell = -prices[i] + maxProfit(prices, i+1, 1, transactionsRemaining, dp);
        } else {
            buyOrSell = prices[i] + maxProfit(prices, i+1, 0, transactionsRemaining-1, dp);
        }
        dp[i][transactionsRemaining][holding] = Math.max(notBuyOrSell, buyOrSell);
        return dp[i][transactionsRemaining][holding];
    }

    public int maxProfit2(int[] prices) {
        // bottom up
        int[][][] dp  = new int[prices.length+1][3][2]; // Why prices.length+1 ? we will require day after last day to calculate profit of last day
        // iterate in reverse of top-down approach
        // transactions remaining 2
        // index - last day
        for(int transactionsRemaining=1; transactionsRemaining >=0; transactionsRemaining--) {
            for(int i =prices.length-1;i>=0; i-- ) {
                for(int holding = 0; holding <2; holding++) {
                    int notBuyOrSell = dp[i+1][transactionsRemaining][holding]; // profit is same as next index, holding value remains same as we don't do any action
                    if (holding == 0) {
                        // if current holding is 0, we can buy the stock or not do anything
                        // profit will be maximum of both
                        dp[i][transactionsRemaining][holding] = Math.max(notBuyOrSell, -prices[i] + dp[i + 1][transactionsRemaining][1]); // if we are buying the stock, we must have sold the stock at next index. So we subtract the cutrent cost from previous profits when we sold the stock
                    } else {
                        // if current holding is 1, we can sell the stock or not do anything
                        // profit will be maximum of both
                        // if we are selling the stock, the transaction gets decremented. The next day would have transactionsRemaining+1
                        dp[i][transactionsRemaining][holding] = Math.max(notBuyOrSell, prices[i] + dp[i + 1][transactionsRemaining+1][0]);
                    }
                }
            }
        }
        return dp[0][0][0];
    }


    public int maxProfit3(int[] prices) {
        // bottom up
        int[][][] dp  = new int[prices.length+1][3][2]; // Why prices.length+1 ? we will require day after last day to calculate profit of last day
        // iterate in reverse of top-down approach
        // transactions remaining 2
        // index - last day
        for(int transactionsRemaining=1; transactionsRemaining >=0; transactionsRemaining--) {
            for(int i =prices.length-1;i>=0; i-- ) {
                for(int holding = 0; holding <2; holding++) {
                    int notBuyOrSell = dp[i+1][transactionsRemaining][holding]; // profit is same as next index, holding value remains same as we don't do any action
                    if (holding == 0) {
                        // if current holding is 0, we can buy the stock or not do anything
                        // profit will be maximum of both
                        dp[i][transactionsRemaining][holding] = Math.max(notBuyOrSell, -prices[i] + dp[i + 1][transactionsRemaining][1]); // if we are buying the stock, we must have sold the stock at next index. So we subtract the cutrent cost from previous profits when we sold the stock
                    } else {
                        // if current holding is 1, we can sell the stock or not do anything
                        // profit will be maximum of both
                        // if we are selling the stock, the transaction gets decremented. The next day would have transactionsRemaining+1
                        dp[i][transactionsRemaining][holding] = Math.max(notBuyOrSell, prices[i] + dp[i + 1][transactionsRemaining+1][0]);
                    }
                }
            }
        }
        return dp[0][0][0];
    }

    public int maxProfit4(int[] prices) {
        // bottom up without array
        int[][][] dp  = new int[prices.length+1][3][2]; // Why prices.length+1 ? we will require day after last day to calculate profit of last day
        // iterate in reverse of top-down approach
        // transactions remaining 2
        // index - last day
        for(int transactionsRemaining=1; transactionsRemaining >=0; transactionsRemaining--) {
            for(int i =prices.length-1;i>=0; i-- ) {
                for(int holding = 0; holding <2; holding++) {
                    int notBuyOrSell = dp[i+1][transactionsRemaining][holding]; // profit is same as next index, holding value remains same as we don't do any action
                    if (holding == 0) {
                        // if current holding is 0, we can buy the stock or not do anything
                        // profit will be maximum of both
                        dp[i][transactionsRemaining][holding] = Math.max(notBuyOrSell, -prices[i] + dp[i + 1][transactionsRemaining][1]); // if we are buying the stock, we must have sold the stock at next index. So we subtract the cutrent cost from previous profits when we sold the stock
                    } else {
                        // if current holding is 1, we can sell the stock or not do anything
                        // profit will be maximum of both
                        // if we are selling the stock, the transaction gets decremented. The next day would have transactionsRemaining+1
                        dp[i][transactionsRemaining][holding] = Math.max(notBuyOrSell, prices[i] + dp[i + 1][transactionsRemaining+1][0]);
                    }
                }
            }
        }
        return dp[0][0][0];
    }
}
