package algorithms.leetcodedp;

import java.util.Arrays;

//You are given an array prices where prices[i] is the price of a given stock on the ith day.
//Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times)
// with the following restrictions:
//After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
//Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
//
//Example 1:
//
//Input: prices = [1,2,3,0,2]
//Output: 3
//Explanation: transactions = [buy, sell, cooldown, buy, sell]
//Example 2:
//
//Input: prices = [1]
//Output: 0
//
//
//Constraints:
//
//1 <= prices.length <= 5000
//0 <= prices[i] <= 1000
public class BuyAndSellStockWthCooldown {
    public int maxProfit(int[] prices) {
        // here we have 3 variables: index (the day we are on), holding, cooldown
        // As we can do as many transactions as we want, no need to track
        // After further analysis, it is clear that we don't have to track cooldown. We simply increase index by 2 whenever we sell
        // two dimenstional top down dp
        int[][] dp = new int[prices.length][2];
        for(int[] i: dp) {
            Arrays.fill(i,-1);
        }
        return maxProfit(prices,0, 0, dp);
    }

    int maxProfit(int[] prices, int i, int holding, int[][] dp) {
        if(i>=prices.length) {// can't use == because it is possible that we sold the stock at the last index, so 2 was added to index
            return 0;
        }
        if(dp[i][holding]!=-1) {
            return dp[i][holding];
        }
        int notBuyOrSell = maxProfit(prices, i+1, holding, dp);
        int buyOrSell;
        if(holding==0) {
            // buy
            buyOrSell = -prices[i] + maxProfit(prices, i+1, 1, dp);
        } else {
            buyOrSell = prices[i] + maxProfit(prices, i+2, 0, dp);
        }
        dp[i][holding] = Math.max(notBuyOrSell, buyOrSell);
        return dp[i][holding];
    }

    public int maxProfit2(int[] prices) {
        // here we have 3 variables: index (the day we are on), holding, cooldown
        // As we can do as many transactions as we want, no need to track
        // After further analysis, it is clear that we don't have to track cooldown. We simply increase index by 2 whenever we sell
        // two dimenstional bottom up dp
        int[][] dp = new int[prices.length +2][2];
        for(int i=prices.length-1; i>=0; i--) {
            for(int holding = 0; holding < 2; holding++) {
                int notBuyOrSell = dp[i+1][holding];
                int buyOrSell;
                if (holding==0) {
                    // buy
                    buyOrSell= Math.max(notBuyOrSell, -prices[i] + dp[i+1][1]); // Notice on the left side that holding 0 is being set for buy transaction
                } else {
                    // sell
                        buyOrSell = Math.max(notBuyOrSell, prices[i] + dp[i+2][0]); //if we are selling the stock, we can't buy for 2 days. we use the buy transaction here (holding =0)
                }
                dp[i][holding] = Math.max(notBuyOrSell, buyOrSell);
            }
        }
        return dp[0][0];
    }

}
