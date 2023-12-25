package algorithms.leetcodedp;

//There is a row of m houses in a small city, each house must be painted with one of the n colors (labeled from 1 to n), some houses that have been painted last summer should not be painted again.
//
//A neighborhood is a maximal group of continuous houses that are painted with the same color.
//
//For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods [{1}, {2,2}, {3,3}, {2}, {1,1}].
//Given an array houses, an m x n matrix cost and an integer target where:
//
//houses[i]: is the color of the house i, and 0 if the house is not painted yet.
//cost[i][j]: is the cost of paint the house i with the color j + 1.
//Return the minimum cost of painting all the remaining houses in such a way that there are exactly target neighborhoods. If it is not possible, return -1.
//
//
//
//Example 1:
//
//Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
//Output: 9
//Explanation: Paint houses of this way [1,2,2,1,1]
//This array contains target = 3 neighborhoods, [{1}, {2,2}, {1,1}].
//Cost of paint all houses (1 + 1 + 1 + 1 + 5) = 9.
//Example 2:
//
//Input: houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
//Output: 11
//Explanation: Some houses are already painted, Paint the houses of this way [2,2,1,2,2]
//This array contains target = 3 neighborhoods, [{2,2}, {1}, {2,2}].
//Cost of paint the first and last house (10 + 1) = 11.
//Example 3:
//
//Input: houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
//Output: -1
//Explanation: Houses are already painted with a total of 4 neighborhoods [{3},{1},{2},{3}] different of target = 3.
//
//Constraints:
//
//m == houses.length == cost.length
//n == cost[i].length
//1 <= m <= 100
//1 <= n <= 20
//1 <= target <= m
//0 <= houses[i] <= n
//1 <= cost[i][j] <= 104
public class PaintHouseIII {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        // let's start by painting first house
        // m and n are confusing, don't use them, use length functions directly
        return minCostRecursive(houses, cost, target, 0, -1);
    }

    private int minCostRecursive(int[] houses, int[][] cost, int target, int index, int previous) {
        // TLE 9 / 62 testcases passed
        if(index >= houses.length) { // we painted all houses
            if(target==0) {
                return 0;
            }
            return -1;
        }
        // neighbourhoods have to be EXACTLY same as target
        if(target <0) {
            return -1;
        }
        int min = -1;
        // Case 1: house is already painted
        if(houses[index]!=0) {
            int neighbourhoods = target;
            if(previous != houses[index]) {
                neighbourhoods--;
            }
            return minCostRecursive(houses, cost, neighbourhoods, index+1, houses[index]);
        } else {
            // Case 2: house is not yet painted
            // try to paint first house with every colour and call minCost for next house
            for(int c =0; c < cost[0].length; c++) {
                int neighbourhoods = target;
                if(previous != c+1) { // we can't use c here in place of c+1.
                    // Because it is possible that previous house was already painted and it had colour 1.
                    // in that case, we will be choosing wrong colour as 1. If we use zero based index, we will choose the 2nd colour as 1 which is wrong
                    neighbourhoods--;
                }
                int costForNextHouse = minCostRecursive(houses, cost, neighbourhoods, index+1, c+1);
                if(costForNextHouse==-1) {
                    continue;
                }
                int currentCost = cost[index][c] + costForNextHouse;
                min = min ==-1? currentCost: Math.min(min, currentCost);
            }
        }
        return min;
    }

    public int minCost2(int[] houses, int[][] cost, int m, int n, int target) {
        // let's start by painting first house
        // m and n are confusing, don't use them, use length functions directly
        // 3 things change, index, previous and target. So 3 dimensional dp
        int[][][] dp = new int[houses.length][cost[0].length+1][target];
        return dp(houses, cost, target, 0, -1, dp);
    }

    private int dp(int[] houses, int[][] cost, int target, int index, int previous, int[][][] dp) {
        if(index >= houses.length) { // we painted all houses
            if(target==0) {
                return 0;
            }
            return -1;
        }
        // neighbourhoods have to be EXACTLY same as target
        if(target <0) {
            return -1;
        }
        if(previous!= -1 && dp[index][previous][target] !=0) {
            return dp[index][previous][target];
        }
        int min = -1;
        // Case 1: house is already painted
        if(houses[index]!=0) {
            int neighbourhoods = target;
            if(previous != houses[index]) {
                neighbourhoods--;
            }
            if(previous ==-1) {
                return dp(houses, cost, neighbourhoods, index+1, houses[index], dp);
            }
            dp[index][previous][target] = dp(houses, cost, neighbourhoods, index+1, houses[index], dp);
            return dp[index][previous][target];
        } else {
            // Case 2: house is not yet painted
            // try to paint first house with every colour and call minCost for next house
            for(int c =0; c < cost[0].length; c++) {
                int neighbourhoods = target;
                if(previous != c+1) { // we can't use c here in place of c+1.
                    // Because it is possible that previous house was already painted and it had colour 1.
                    // in that case, we will be choosing wrong colour as 1. If we use zero based index, we will choose the 2nd colour as 1 which is wrong
                    neighbourhoods--;
                }
                int costForNextHouse = dp(houses, cost, neighbourhoods, index+1, c+1, dp);
                if(costForNextHouse==-1) {
                    continue;
                }
                int currentCost = cost[index][c] + costForNextHouse;
                min = min ==-1? currentCost: Math.min(min, currentCost);
            }
        }if(previous==-1) {
            return min;
        }
        dp[index][previous][target] = min;
        return dp[index][previous][target];

        //Here, M is the number of houses, NNN is the number of colors and T is the number of target neighborhoods.
        //Time complexity: O(M⋅T⋅N^2)
        //Each state is defined by the values currIndex, neighborhoodCount, and prevHouseColor. Hence, there will be M⋅T⋅N possible states,
        // and in the worst-case scenario, we must visit most of the states to solve the original problem.
        // Each recursive call requires O(N) time as we might need to iterate over all the colors. Thus, the total time complexity is equal to
        // O(M⋅T⋅N^2)
        //Space complexity: O(M⋅T⋅N)
        //The memoization results are stored in the table memo with size M⋅T⋅N. Also, stack space in the recursion is equal to the maximum number of active functions.
        // The maximum number of active functions will be at most M i.e., one function call for every house. Hence, the space complexity is O(M⋅T⋅N)
    }


}
