package algorithms.leetcodedp;

//You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
//
//On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
// However, you can buy it then immediately sell it on the same day.
//
//Find and return the maximum profit you can achieve.
//
//Example 1:
//Input: prices = [7,1,5,3,6,4]
//Output: 7
//Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
//Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
//Total profit is 4 + 3 = 7.

//Example 2:
//Input: prices = [1,2,3,4,5]
//Output: 4
//Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
//Total profit is 4.

import java.util.Arrays;

//Example 3:
//Input: prices = [7,6,4,3,1]
//Output: 0
//Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
//
//
//Constraints:
//1 <= prices.length <= 3 * 104
//0 <= prices[i] <= 104
public class BuyAndSellStockII {
    public int maxProfit(int[] prices) {
        //The key point is we need to consider every peak immediately following a valley to maximize the profit.
        //In case we skip one of the peaks (trying to obtain more profit), we will end up losing the profit over one of the transactions leading to an overall lesser profit.
        //we can directly keep on adding the difference between the consecutive numbers of the array if the second number is larger than the first one,
        // and at the total sum we obtain will be the maximum profit.
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                profit += prices[i] - min;
                min = prices[i];
            }
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        //simpler. Simply add to profit when current is larger than previous
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

    public int maxProfit3(int[] prices) {
        // recursion
        return maxProfit3(prices, 0, 0);
    }

    private int maxProfit3(int[] prices, int i, int holding) {
        // Without memoization 198 / 200 testcases passed
        if (i == prices.length) {
            return 0;
        }
        int doNotBuyOrSell = maxProfit3(prices, i + 1, holding);
        int buyOrSell;
        if (holding == 0) {
            // if we are not holding the stock, then we can buy
            buyOrSell = -prices[i] + maxProfit3(prices, i + 1, 1);
        } else {
            // if we are holding stock, we can sell
            buyOrSell = prices[i] + maxProfit3(prices, i + 1, 0);
        }
        return Math.max(doNotBuyOrSell, buyOrSell);
    }

    public int maxProfit4(int[] prices) {
        // recursion, top down dp
        // two dimension dp because two things vary
        int dp[][] = new int[prices.length][2];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1); // Gives TLE if we don't initialize with 0
        }
        return maxProfit4(prices, 0, 0, dp);
    }

    private int maxProfit4(int[] prices, int i, int holding, int[][] dp) {
        if (i == prices.length) {
            return 0;
        }
        if (dp[i][holding] != -1) {
            return dp[i][holding];
        }
        int doNotBuyOrSell = maxProfit4(prices, i + 1, holding, dp);
        int buyOrSell;
        if (holding == 0) {
            // if we are not holding the stock, then we can buy
            buyOrSell = -prices[i] + maxProfit4(prices, i + 1, 1, dp);
        } else {
            // if we are holding stock, we can sell
            buyOrSell = prices[i] + maxProfit4(prices, i + 1, 0, dp);
        }
        dp[i][holding] = Math.max(doNotBuyOrSell, buyOrSell);
        return dp[i][holding];
    }


    public int maxProfit5(int[] prices) {
        // bottom up dp
        // two dimension dp because two things vary
        int dp[][] = new int[prices.length + 1][2];
        // reverse the direction from top down.
        // for index = prices.length, profit is 0.
        // for index = prices.length -1. profit depends on whether we are holding the stock at prices.length index or not
        // for index i , profit depends on whether we are holding stock at index i+1 or not
        // if holding, then we can sell or not sell
        // if not holding, we can buy or not buy
        // not buy and not sell are same- not do anything, in this case profit wil be same as profit at index i+1 with same value of holding

        for (int i = prices.length - 1; i >= 0; i--) {
            for (int holding = 0; holding < 2; holding++) {
                int notBuyOrSell = dp[i + 1][holding]; // profit is same as next index, holding value remains same as we don't do any action
                if (holding == 0) {
                    // if current holding is 0, we can buy the stock or not do anything
                    // profit will be maximum of both
                    dp[i][holding] = Math.max(notBuyOrSell, -prices[i] + dp[i + 1][1]); // if we are buying the stock, we must have sold the stock at next index. So we subtract the cutrent cost from previous profits when we sold the stock
                    System.out.println("Holding: " + dp[i][holding]);
                } else {
                    // if current holding is 1, we can sell the stock or not do anything
                    // profit will be maximum of both
                    dp[i][holding] = Math.max(notBuyOrSell, prices[i] + dp[i + 1][0]);
                    System.out.print("Not: " + dp[i][holding]);
                }
            }
        }
        return dp[0][0];// To maximize profit, we are not holding any stock at index 0
    }

    public int maxProfit6(int[] prices) {
        // bottom up dp
        // from previous solution, we can see that we onlty require two values and not the whole array
        // first value is when index is i+1 and holding =0
        // second value is when index is i+1 and holding 1
        int holding = 0;
        int notHolding = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            // if current holding is 0, we can buy the stock or not do anything
            // profit will be maximum of both
            int nextHolding = holding;
            int nextNotHolding = notHolding;
            holding = Math.max(nextHolding, -prices[i] + nextNotHolding); // if we are buying the stock, we must have sold the stock at next index. So we subtract the cutrent cost from previous profits when we sold the stock
            notHolding = Math.max(nextNotHolding, prices[i] + holding);
            System.out.println("Holding: " + holding + "Not: " + notHolding);
        }
        return holding;// In this case, output is holding. Holding contains the overall profit. Think of †he case with array length 1. notHolding will add the price, but we can't sell before buysing
    }
}
