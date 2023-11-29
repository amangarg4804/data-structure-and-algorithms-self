package algorithms.leetcodedp;

//Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
//
//Note: You can only move either down or right at any point in time.
//Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
//Output: 7
//Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

import java.util.Arrays;

//Example 2:
//Input: grid = [[1,2,3],[4,5,6]]
//Output: 12
//
//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 200
//0 <= grid[i][j] <= 200
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        return minPathSum(grid, grid.length-1, grid[0].length -1);
    }

    public int minPathSum(int[][] grid, int row, int col) {
        //25 / 61 testcases passed TLE
        if(row == 0 && col == 0) {
            return grid[row][col];
        }
        if(row ==0) {
            return  grid[row][col] +  minPathSum(grid, row, col-1);
        }
        if(col ==0) {
            return  grid[row][col] + minPathSum(grid, row-1, col);
        }
        return grid[row][col] + Math.min(minPathSum(grid, row, col-1), minPathSum(grid, row-1, col));
    }

    public int minPathSum2(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for(int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        return dp(grid, grid.length-1, grid[0].length -1, dp);
    }

    public int dp(int[][] grid, int row, int col, int[][] dp) {
        // top down dp
        if(dp[row][col] != -1) {
            return dp[row][col];
        }
        if(row == 0 && col == 0) {
            return grid[row][col];
        }
        if(row ==0) {
            dp[row][col] =  grid[row][col] +  dp(grid, row, col-1, dp);
            return dp[row][col];
        }
        if(col ==0) {
             dp[row][col]=  grid[row][col] + dp(grid, row-1, col, dp);
            return dp[row][col];
        }
        dp[row][col]= grid[row][col] + Math.min(dp(grid, row, col-1, dp), dp(grid, row-1, col, dp));
        return dp[row][col];
        // time complexity: for each element, we calculate result only once. It takes constant time to calculate result for each element. So time complexity is O(m*n)
        // space complexity: dp array = m*n. recursion stack: m*n
    }

    public int minPathSum3(int[][] grid) {
        // bottom up dp
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for(int col = 1; col < grid[0].length; col ++) {
            dp[0][col] = grid[0][col] + dp[0][col-1];
        }
        for(int row = 1; row < grid.length; row ++) {
            dp[row][0] = grid[row][0] + dp[row-1][0];
        }
        for(int row =1; row < grid.length; row++) {
            for(int col = 1; col < grid[0].length; col++) {
                dp[row][col] = grid[row][col] + Math.min(dp[row][col-1], dp[row-1][col]);
            }
        }
        return dp[grid.length-1][grid[0].length -1];
    }
}
