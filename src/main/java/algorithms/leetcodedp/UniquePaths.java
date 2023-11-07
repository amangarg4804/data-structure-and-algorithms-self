package algorithms.leetcodedp;

//There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
// The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
//
//Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
//
//The test cases are generated so that the answer will be less than or equal to 2 * 109.

//Example 1:
//Input: m = 3, n = 7
//Output: 28
//Example 2:
//
//Input: m = 3, n = 2
//Output: 3
//Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
//1. Right -> Down -> Down
//2. Down -> Down -> Right
//3. Down -> Right -> Down
//
//
//Constraints:
//1 <= m, n <= 100
public class UniquePaths {
    //The bottom-up approach for pathing problems is often more intuitive than for other types of dynamic programming problems
    // - so this is a good chance for us to practice starting with a bottom-up approach.
    //In this article, we'll use the framework to solve Unique Paths. This problem asks us to find the number of distinct ways to do something, which is a hint that we should consider using dynamic programming, and we discussed how to do so in the previous chapter. Let's get started:
    //1. An array that answers the problem for a given state
    //State variables are usually easy to find in pathing problems. Similar to how we need one index (
    //i) for 1D array inputs, with pathing problems on a 2D matrix, we need two indices (
    //row and
    //col) to denote position. Some problems have added constraints that will require additional state variables,
    // but there doesn't seem to be anything of the sort in this problem. Therefore, we will just use two state variables
    //row which represents the current row, and
    //col which represents the current column.
    //
    //The problem is asking for the number of paths to the final square, so let's have
    //dp[row][col] represent how many paths there are from the start (top-left corner) to the square at
    //(row, col). We will return
    //dp[m - 1][n - 1] where
    //m and n are the number of rows and columns respectively.
    //2. A recurrence relation to transition between states
    //
    //The problem says that we are allowed to move down or right. That means, if we are at some square, we arrived from either the square above
    // or the square to the left. These two squares are
    //(row - 1, col) and
    //(row, col - 1). Since we can arrive at the current square from either of these squares, the number of ways to get to the current square is the sum of the
    // number of ways to get to these two squares. Either of these may be out of the grid bounds, so we should make sure to check for that.
    // This gives us our simple recurrence relation:
    //
    //dp[row][col] = dp[row - 1][col] + dp[row][col - 1], where
    //dp[row - 1][col] and
    //dp[row][col - 1] is equal to 0 if out of bounds.
    //
    //3. Base cases
    //
    //In the previous chapter, when talking about counting DP problems, we said that the base cases need to be set to nonzero values
    // so that the terms in the recurrence relation don't just stay stuck at zero. In this problem, we start in the top-left corner.
    // How many ways are there for us to get to the first square? Only 1 - we start on it. Therefore, our base case is
    //dp[0][0] = 1
    //
    //Note: If you have trouble coming up with the recurrence relation, sometimes it helps to come up with the base case(s) first.
    // Then walk through how you would find the result for states that are slightly more complicated than the base case(s), such as
    //dp[0][1]
    //dp[1][1], and
    //dp[2][1].
    // Often, this process of manually solving the problem for simple states can help you understand what the recurrence relation should be.
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int row = 0; row < m; row++) {
            for(int col = 0; col < n; col ++) {
                if(row>0) {
                    dp[row][col] += dp[row-1][col];
                }
                if(col>0) {
                    dp[row][col] += dp[row][col-1];
                }
            }
        }
        return dp[m-1][n-1];
        //Time complexity: O(N×M)
        //Space complexity: O(N×M)
    }
}
