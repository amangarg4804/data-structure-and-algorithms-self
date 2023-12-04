package algorithms.leetcodedp;

//Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
//
//A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right.
// Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
public class MinimumFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        for(int col=0 ; col < matrix[0].length; col++) {
            int pathSum = minFallingPathSum(matrix, 0, col);
            if(pathSum < min) {
                min = pathSum;
            }
        }
        return min;
    }

    private int minFallingPathSum(int[][] matrix, int row, int col ) {
        // TLE 38 / 50 testcases passed
        if(row==matrix.length-1) {
            return matrix[row][col];
        }
        if(col-1 >=0 && col +1 < matrix[0].length ) {
            return matrix[row][col] + Math.min(minFallingPathSum(matrix, row+1, col),
                    Math.min(minFallingPathSum(matrix, row+1, col-1),
                            minFallingPathSum(matrix, row+1, col+1)));
        }
        if(col-1 >=0) {
            return matrix[row][col] + Math.min(minFallingPathSum(matrix, row+1, col),
                    minFallingPathSum(matrix, row+1, col-1));
        }
        if(col +1 < matrix[0].length) {
            System.out.println("row: " + row + "col: " + col);
            return matrix[row][col] + Math.min(minFallingPathSum(matrix, row+1, col),
                    minFallingPathSum(matrix, row+1, col+1));
        }
        return 0;
      }

    public int minFallingPathSum2(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        Integer[][]dp = new Integer[matrix.length][matrix[0].length];
        for(int col=0 ; col < matrix[0].length; col++) {
            int pathSum = dp(matrix, 0, col, dp);
            if(pathSum < min) {
                min = pathSum;
            }
        }
        return min;
    }

    private int dp(int[][] matrix, int row, int col, Integer[][] dp ) {
        // Top down dp
        if(dp[row][col] !=null) {
            return dp[row][col];
        }
        if(row==matrix.length-1) {
            return matrix[row][col];
        }
        if(col-1 >=0 && col +1 < matrix[0].length ) {
            dp[row][col] = matrix[row][col] + Math.min(dp(matrix, row+1, col, dp),
                    Math.min(dp(matrix, row+1, col-1, dp),
                            dp(matrix, row+1, col+1, dp)));
            return dp[row][col];
        }
        if(col-1 >=0) {
            dp[row][col] = matrix[row][col] + Math.min(dp(matrix, row+1, col, dp),
                    dp(matrix, row+1, col-1, dp));
            return dp[row][col];
        }
        if(col +1 < matrix[0].length) {
            dp[row][col] = matrix[row][col] + Math.min(dp(matrix, row+1, col, dp),
                    dp(matrix, row+1, col+1, dp));
            return dp[row][col];
        }
        return 0;
    }

    public int minFallingPathSum3(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        Integer[][]dp = new Integer[matrix.length][matrix[0].length];
        for(int col=0 ; col < matrix[0].length; col++) {
            int pathSum = dp2(matrix, 0, col, dp);
            if(pathSum < min) {
                min = pathSum;
            }
        }
        return min;
    }
    private int dp2(int[][] matrix, int row, int col, Integer[][] dp ) {
        // Top down dp
        if(dp[row][col] !=null) {
            return dp[row][col];
        }
        if(row==matrix.length-1) {
            return matrix[row][col];
        }
        int left = col -1>=0? dp2(matrix, row+1, col-1, dp): Integer.MAX_VALUE;
        int right = col +1 < matrix[0].length? dp2(matrix, row+1, col+1, dp): Integer.MAX_VALUE;
        int down = dp2(matrix, row+1, col, dp);
        dp[row][col] = matrix[row][col] + Math.min(down, Math.min(left, right));
        return dp[row][col];
        //Let N be the length of matrix.
        //Time Complexity: O(N^2)
        //For every cell in the matrix, we will compute the result only once and update the memo. For the subsequent calls,
        // we are using the stored results that take O(1) time. There are N^2
        //  cells in the matrix, and thus N^2
        //  dp states. So, the time complexity is O(N^2)
        //Space Complexity: O(N^2)
        //The recursive call stack uses O(N)space. As the maximum depth of the tree is N,
        // we can’t have more than NNN recursive calls on the call stack at any time. The 2D matrix memo uses O(N^2)
        //  space. Thus, the space complexity is O(N) + O(N^2) = O(N^2)

    }

    public int minFallingPathSum4(int[][] matrix) {
        Integer[][]dp = new Integer[matrix.length +1][matrix[0].length+1];
        // can't have outside for loop for col
        // for calculating dp[row][col] we require all values of next row pre-calculated.
        // i.e, dp[row+1][col], dp[row+1][col+1], dp[row+1][col-1]
        int min = Integer.MAX_VALUE;

        for(int row = matrix.length-1; row >=0; row--) {
            for(int col=0 ; col < matrix[0].length; col++) {
                if (row == matrix.length - 1) {
                    dp[row][col] = Math.min(matrix[row][col], min);
                    continue;
                }
                int left = col - 1 >= 0 ? dp[row + 1][col - 1] : Integer.MAX_VALUE;
                int right = col + 1 < matrix[0].length ? dp[row + 1][col + 1] : Integer.MAX_VALUE;
                int down = dp[row + 1][col];
                dp[row][col] = matrix[row][col] + Math.min(down, Math.min(left, right));
//                    if(row==0) {
//                        min = Math.min(dp[0][col], min); // this logic never runs for matrix with just one element [-45]. We can use a outer for loop instead
//                    }
            }
        }
        for(int col=0; col < matrix[0].length; col++) {
            min = Math.min(dp[0][col], min);
        }
        return min;
    }
}
