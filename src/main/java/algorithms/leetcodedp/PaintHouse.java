package algorithms.leetcodedp;

//There is a row of n houses, where each house can be painted one of three colors: red, blue, or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
//
//The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.
//
//For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
//Return the minimum cost to paint all houses.

//Example 1:
//
//Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
//Output: 10
//Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
//Minimum cost: 2 + 5 + 3 = 10.
//Example 2:
//Input: costs = [[7,6,2]]
//Output: 2
//Constraints:
//costs.length == n
//costs[i].length == 3
//1 <= n <= 100
//1 <= costs[i][j] <= 20
public class PaintHouse {

    public int minCost(int[][] costs) {
        return minCost(costs, 0, -1);
    }

    private int minCost(int[][] costs, int i, int previous) {
        // TLE 27 / 100 testcases passed
        if(i==costs.length) {
            return 0;
        }
        int result = Integer.MAX_VALUE; // cost[i] is always greater than 0
        for(int j=0; j< 3; j++) {
            if(previous==j) {
                continue;
            }
            int cost = costs[i][j] + minCost(costs, i+1, j);
            result = Math.min(cost, result);
        }
        return result;
    }

    public int minCost2(int[][] costs) {
        int[][] dp = new int[costs.length][3];
        return dp(costs, 0, -1, dp); // can't use -1 as array index so use 0 as previous initially
    }

    private int dp(int[][] costs, int i, int previous, int[][] dp) {
        // top down dp
        if(i==costs.length) {
            return 0;
        }
        if(previous!=-1 && dp[i][previous]!=0) {
            return dp[i][previous];
        }
        int result = Integer.MAX_VALUE; // cost[i] is always greater than 0
        for(int j=0; j< 3; j++) {
            if(previous==j) {
                continue;
            }
            int cost = costs[i][j] + dp(costs, i+1, j, dp);
            result = Math.min(cost, result);
        }
        if(previous== -1) {
            return result;
        }
        dp[i][previous] = result;
        return dp[i][previous];
    }
}
