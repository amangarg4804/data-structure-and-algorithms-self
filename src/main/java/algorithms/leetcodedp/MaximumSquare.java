package algorithms.leetcodedp;

//Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
//Input: matrix =[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
//Output: 4

//Input: matrix = [["0","1"],["1","0"]]
//Output: 1

//Input: matrix = [["0"]]
//Output: 0
//
//
//Constraints:
//m == matrix.length
//n == matrix[i].length
//1 <= m, n <= 300
//matrix[i][j] is '0' or '1'.
public class MaximumSquare {
    // how do you know that a coordinate can be included in square
    // let's say we have co-ordinate x,y. If the value of matrix[x][y] is 1, then we have a square of size 1
    // what if we want to find whether this is the left-top corner of a square of size 2?
    // if that has to be true then (x+1, y), (x, y+1) and x+1, y+1 have to be 1
    // for square of size 3?
    // (x+2, y), (y+2, x), (x+2, y+1), (x+2, y+2), (x+1, y+2)
    // this approach seems to get out of hand but it can be coded
    public int maximalSquare(char[][] matrix) {
        int max =0;
        for(int row =0; row < matrix.length ;row++) {
            for(int col = 0; col < matrix[row].length; col++) {
                if(matrix[row][col] =='0') {
                    continue;
                }
                int currentMax = 1;
                // what's the intuition? when we are trying to check whether we are able to make a square of size 2 from the coordinate (0,0).
                // we fix row to index 1 (0 based index) and then check that all values of col from index 0 to 1 are 1
                // then we fix the col to index 1 (0 based index) and then check that all values of row from index 0 to 1 are 1
                // if both are true, then we increment current max and check for size 3, size 3 would mean checking index 2 (could be thought of as current max + coordinate valye ; 0+2)
                while (row+currentMax < matrix.length && col + currentMax < matrix[0].length) {
                    int maxRow= row + currentMax;
                    boolean isPossible = true;
                    //for maxRow check all columns from current Column to current Column + currentMax
                    for(int colRange = col; colRange <=col+currentMax ;colRange++ ) {
                        if(matrix[maxRow][colRange] !='1') {
                            isPossible= false;
                            break;
                        }
                        //System.out.println("Possible:" + "row: " + row + "col: " + col + "currentMax: " + currentMax);
                    }
                    int maxCol= col + currentMax;
                    for(int rowRange = row; rowRange <=row+currentMax ;rowRange++ ) {
                        if(matrix[rowRange][maxCol] !='1') {
                            isPossible= false;
                            break;
                        }
                    }
                    if(!isPossible) {
                        //System.out.println("row: " + row + "col: " + col + "currentMax: " + currentMax);
                        break;
                    }
                    currentMax++;
                }
                max = Math.max(currentMax, max);
            }
        }
        return max*max;
        //Time complexity : O((mn)^2)
        // In worst case, we need to traverse the complete matrix for every 1.
        //Space complexity : O(1). No extra space is used.
    }

    //https://www.youtube.com/watch?v=nZAyRZC8tko - Alisha
    public int maximalSquare2(char[][] matrix) {
        // Using dynamic programming.
        // At each index, check the left, left-top and top index
        // set the value of current index as min (left, left-top, top ) +1
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for( int row= 0; row < matrix.length; row++) {
            for(int col =0; col < matrix[0].length; col++) {
                if(row==0 || col ==0) {
                    // for indexes at the left and top edges, the square size is always equal to the value at index
                    dp[row][col]  = matrix[row][col] -'0';
                } else if(matrix[row][col]=='1') { // if matrix[row][col] itself is 0, it can't form a square
                    dp[row][col] = Math.min(dp[row][col-1], Math.min(dp[row-1][col-1], dp[row-1][col])) +1  ;
                }
                max = Math.max(max, dp[row][col]);
            }
        }
        return max*max;
    }
}
