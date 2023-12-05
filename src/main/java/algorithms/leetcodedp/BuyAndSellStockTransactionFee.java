package algorithms.leetcodedp;

import java.util.Arrays;

public class BuyAndSellStockTransactionFee {
    int fee=0;
    public int maxProfit(int[] prices, int fee) {
        this.fee = fee;
        return maxProfit(prices, 0, 0);
    }
    private int maxProfit(int[] prices, int holding, int i) {
        // TLE 19 / 44 testcases passed
        if (i==prices.length) {
            return 0;
        }
        int notBuyOrSell = maxProfit(prices, holding, i+1);
        int buyOrSell;
        if(holding==0) {
            // buy
            buyOrSell = -prices[i] + maxProfit(prices, 1, i+1);
        } else {
            // sell
            buyOrSell = prices[i] + maxProfit(prices, 0, i+1) -fee;
        }
        return Math.max(notBuyOrSell, buyOrSell);
    }

    public int maxProfit2(int[] prices, int fee) {
        this.fee = fee;
        int[][] dp = new int[prices.length][2];
        for(int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        return dp(prices, 0, 0, dp);
    }
    private int dp(int[] prices, int holding, int i, int[][] dp) {
        // Top down dp
        if (i==prices.length) {
            return 0;
        }
        if(dp[i][holding] != -1) {
            return dp[i][holding];
        }
        int notBuyOrSell = dp(prices, holding, i+1, dp);
        int buyOrSell;
        if(holding==0) {
            // buy
            buyOrSell = -prices[i] + dp(prices, 1, i+1, dp);
        } else {
            // sell
            buyOrSell = prices[i] + dp(prices, 0, i+1, dp) -fee;
        }
        dp[i][holding] = Math.max(notBuyOrSell, buyOrSell);
        return dp[i][holding];
    }

    public int maxProfit3(int[] prices, int fee) {
        int[][] dp = new int[prices.length +1][2];

        for(int i=prices.length-1; i>=0; i--) {
            for(int holding =0; holding < 2; holding++) {
                int notBuyOrSell = dp[i+1][holding];
                int buyOrSell;
                if(holding ==0) {
                    // buy
                    buyOrSell = -prices[i] + dp[i+1][1];
                } else {
                    buyOrSell = prices[i] + dp[i+1][0] -fee;
                }
                dp[i][holding] = Math.max(notBuyOrSell, buyOrSell);
            }
        }
        return dp[0][0];
    }
}
