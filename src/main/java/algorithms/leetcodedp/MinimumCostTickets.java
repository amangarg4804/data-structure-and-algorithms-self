package algorithms.leetcodedp;

//You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days.
// Each day is an integer from 1 to 365.
//Train tickets are sold in three different ways:
//a 1-day pass is sold for costs[0] dollars,
//a 7-day pass is sold for costs[1] dollars, and
//a 30-day pass is sold for costs[2] dollars.
//The passes allow that many days of consecutive travel.
//For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
//Return the minimum number of dollars you need to travel every day in the given list of days.
//
//Example 1:
//Input: days = [1,4,6,7,8,20], costs = [2,7,15]
//Output: 11
//Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
//On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
//On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
//On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
//In total, you spent $11 and covered all the days of your travel.
//Example 2:
//
//Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
//Output: 17
//Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
//On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
//On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
//In total, you spent $17 and covered all the days of your travel.
//
//Constraints:
//1 <= days.length <= 365
//1 <= days[i] <= 365
//days is in strictly increasing order.
//costs.length == 3
//1 <= costs[i] <= 1000
public class MinimumCostTickets {
    int[] passValidities = new int[]{1, 7, 30};
    public int mincostTickets(int[] days, int[] costs) {
        return mincostTickets(days, costs, 0, 0);
    }

    private int mincostTickets(int[] days, int[] costs,int i, int passValidity) {
        //TLE
        if(i==days.length) {
            return 0;
        }
        if(passValidity >=days[i]) {
            return mincostTickets(days, costs, i+1, passValidity);
        }
        int minCost = Integer.MAX_VALUE;
        for(int c = 0; c< costs.length; c++) {
            // if we buy a pass on day 3 with validity 7 days, it will be valid till 3+7-1= day 9
            int currentCost = costs[c] + mincostTickets(days, costs, i+1, days[i] + passValidities[c]-1);
            minCost = Math.min(currentCost, minCost);
        }
        return minCost;
    }

    public int mincostTickets2(int[] days, int[] costs) {
        // two things vary, index and passValidity, so 2-dimensional DP
        // what should be the size for passVailidiy index?
        // we add days[i] to PassValidity, PassValidity can be max 30, days[i] can be max 365
        // so 365 + 30= 395
        int[][] dp = new int[days.length][395];
        return dp(days, costs, 0, 0, dp);
    }

    private int dp(int[] days, int[] costs,int i, int passValidity, int[][] dp ) {
        // top down dp
        if(i==days.length) {
            return 0;
        }
        if(dp[i][passValidity]!=0) { // 1 <= costs[i] <= 1000, cost is never 0
            return dp[i][passValidity];
        }
        if(passValidity >=days[i]) {
            dp[i][passValidity] = dp(days, costs, i+1, passValidity, dp);
            return dp[i][passValidity];
        }
        int minCost = Integer.MAX_VALUE;
        for(int c = 0; c< costs.length; c++) {
            // if we buy a pass on day 3 with validity 7 days, it will be valid till 3+7-1= day 9
            int currentCost = costs[c] + dp(days, costs, i+1, days[i] + passValidities[c]-1, dp);
            minCost = Math.min(currentCost, minCost);
        }
        dp[i][passValidity]= minCost;
        return dp[i][passValidity];
    }
    
}
