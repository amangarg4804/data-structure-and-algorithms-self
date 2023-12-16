package algorithms.leetcodedp;

//There are a row of n houses, each house can be painted with one of the k colors.
// The cost of painting each house with a certain color is different.
// You have to paint all the houses such that no two adjacent houses have the same color.
//
//The cost of painting each house with a certain color is represented by an n x k cost matrix costs.
//
//For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1
// with color 2, and so on...
//Return the minimum cost to paint all houses.
public class PaintHouseII {
    public int minCostII(int[][] costs) {
        return minCostII(costs, -1, 0);
    }

    private int minCostII(int[][] costs, int previous, int i) {
        // TLE 13 / 103 testcases passed
        if(i==costs.length) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int c=0; c< costs[0].length; c++) {
            if(previous ==c) {
                continue;
            }
            int cost = costs[i][c] + minCostII(costs, c, i+1);
            min =Math.min(cost, min);
        }
        return min;
    }

    public int minCostII2(int[][] costs) {
        int[][] dp = new int[costs.length][costs[0].length];
        return dp(costs, -1, 0, dp);
    }

    private int dp(int[][] costs, int previous, int i, int[][] dp) {
        // top down dp
        if(i==costs.length) {
            return 0;
        }
        if(previous !=-1 && dp[i][previous]!=0) { // cost is atleast 1, 1 <= costs[i][j] <= 20
            return dp[i][previous];
        }
        int min = Integer.MAX_VALUE;
        for(int c=0; c< costs[0].length; c++) {
            if(previous ==c) {
                continue;
            }
            int cost = costs[i][c] + dp(costs, c, i+1, dp);
            min =Math.min(cost, min);
        }
        if(previous ==-1) {
            return min;
        }
        dp[i][previous] =min;
        return dp[i][previous];
    }

    public int minCostII3(int[][] costs) {
        // bottom up dp
        int[][] dp = new int[costs.length][costs[0].length];
        // last house painted
        for(int c=0; c< costs[0].length; c++) {
            dp[costs.length-1][c] = costs[costs.length-1][c];
        }
        for(int i=costs.length-2; i>=0; i--) {
            for(int c=0; c < costs[0].length; c++) {
                int min = Integer.MAX_VALUE;
                for(int next=0; next < costs[0].length; next++) {
                    if(c==next) {
                        continue;
                    }
                    min = Math.min(min, dp[i+1][next] + costs[i][c]);
                }
               dp[i][c] = min;
            }
        }
        int min = Integer.MAX_VALUE;
        for(int c=0; c < costs[0].length; c++) {
            min = Math.min(dp[0][c], min);
        }
        return min;
    }

    public static void main(String[] args) {
        int[][] costs = {{1,3},{2,4}};
        System.out.println(new PaintHouseII().minCostII3(costs));
    }
}
