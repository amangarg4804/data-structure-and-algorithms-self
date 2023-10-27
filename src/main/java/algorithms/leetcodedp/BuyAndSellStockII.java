package algorithms.leetcodedp;

public class BuyAndSellStockII {
    public int maxProfit(int[] prices) {
        //The key point is we need to consider every peak immediately following a valley to maximize the profit.
        //In case we skip one of the peaks (trying to obtain more profit), we will end up losing the profit over one of the transactions leading to an overall lesser profit.
        //we can directly keep on adding the difference between the consecutive numbers of the array if the second number is larger than the first one,
        // and at the total sum we obtain will be the maximum profit.
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for( int i =0; i< prices.length; i++) {
            if(prices[i] < min) {
                min= prices[i];
            } else {
                profit += prices[i] -min;
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
}
