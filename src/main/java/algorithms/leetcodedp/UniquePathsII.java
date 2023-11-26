package algorithms.leetcodedp;

//You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]).
// The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
//An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
//Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
//The testcases are generated so that the answer will be less than or equal to 2 * 109.

//Example 1:
//Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
//Output: 2
//Explanation: There is one obstacle in the middle of the 3x3 grid above.
//There are two ways to reach the bottom-right corner:
//1. Right -> Right -> Down -> Down
//2. Down -> Down -> Right -> Right

//Example 2:
//Input: obstacleGrid = [[0,1],[0,0]]
//Output: 1

//Constraints:
//m == obstacleGrid.length
//n == obstacleGrid[i].length
//1 <= m, n <= 100
//obstacleGrid[i][j] is 0 or 1.

import java.util.Arrays;

public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // no of ways to reach the right bottom index
        // recursive TLE
        if (obstacleGrid[0][0] == 1 || obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1) {
            // if either the start point or the end point is obstacle, then there are ZERO ways
            return 0;
        }
        return uniquePathsWithObstacles(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1);
    }

    private int uniquePathsWithObstacles(int[][] obstacleGrid, int row, int col) {
        if (obstacleGrid[row][col] == 1) {
            return 0;
        }
        if (row == 0 || col == 0) {
            return 1;
        }
        return uniquePathsWithObstacles(obstacleGrid, row - 1, col) + uniquePathsWithObstacles(obstacleGrid, row, col - 1);
    }

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        // no of ways to reach the right bottom index
        // top down dp
        if (obstacleGrid[0][0] == 1 || obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1) {
            // if either the start point or the end point is obstacle, then there are ZERO ways
            return 0;
        }
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int[] i : dp) {
            Arrays.fill(i, -1);
        }
        dp[0][0] = 1;
//        for(int row=0; row< obstacleGrid.length; row++) {
//            dp[row][0] =1;
//        }
//        for(int col=0; col< obstacleGrid[0].length; col++) {
//            dp[0][col] =1;
//        } It will be wrong to initialize obstacles with 1
        return uniquePathsWithObstacles(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1, dp);
    }

    private int uniquePathsWithObstacles(int[][] obstacleGrid, int row, int col, int[][] dp) {
        if (row < 0 || col < 0) {
            return 0;
        }
        if (obstacleGrid[row][col] == 1) {
            return 0;
        }
        if (dp[row][col] != -1) {
            return dp[row][col];
        }
        dp[row][col] = uniquePathsWithObstacles(obstacleGrid, row - 1, col, dp) + uniquePathsWithObstacles(obstacleGrid, row, col - 1, dp);
        return dp[row][col];
    }
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        // bottom up dp
        if (obstacleGrid[0][0] == 1 || obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1) {
            // if either the start point or the end point is obstacle, then there are ZERO ways
            return 0;
        }
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        // dp[r][c] represents no of ways of reaching row r and col c.
        //dp[0][0] =1; // no of ways of reaching row 0 and col 0 are not always 1
        // dp[0][0] is 1, we want to use to for loops, but the loops can start from row 1 and col 1.We can fill row 0 and col 0 in separate loops
        // fill 0th column
        for(int row = 0; row < obstacleGrid.length; row++) {
            if(obstacleGrid[row][0]==1) {
                //on the first column, if there is an obstacle, the rest are blocked because we can't move left or up. we can only move down and right
                //no need to continue.
                dp[row][0] =0;
                break;
            } else {
                dp[row][0] =1;
            }
        }

        for(int col = 1; col < obstacleGrid[0].length; col++) {
            if(obstacleGrid[0][col]==1) {
                //on the first row, if there is an obstacle, the rest are blocked.
                //no need to continue.
                dp[0][col] =0;
                break;
            } else {
                dp[0][col] =1;
            }
        }
        for(int row = 1; row < obstacleGrid.length; row++) {
            for(int col = 1; col < obstacleGrid[0].length; col++) {
                if(obstacleGrid[row][col] ==1) {
                    dp[row][col] =0;
                } else{
                    dp[row][col] = dp[row-1][col] + dp[row][col -1];
                }
            }
        }
        return dp[obstacleGrid.length- 1][obstacleGrid[0].length -1];
    }

}
