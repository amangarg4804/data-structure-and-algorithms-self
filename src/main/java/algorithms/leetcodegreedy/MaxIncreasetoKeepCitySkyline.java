package algorithms.leetcodegreedy;

//https://leetcode.com/problems/max-increase-to-keep-city-skyline/description/
//There is a city composed of n x n blocks, where each block contains a single building shaped like a vertical square prism.
// You are given a 0-indexed n x n integer matrix grid where grid[r][c] represents the height of the building located in the block at row r
// and column c.
//
//A city's skyline is the outer contour formed by all the building when viewing the side of the city from a distance.
// The skyline from each cardinal direction north, east, south, and west may be different.
//
//We are allowed to increase the height of any number of buildings by any amount (the amount can be different per building).
// The height of a 0-height building can also be increased. However, increasing the height of a building should not affect the city's skyline
// from any cardinal direction.
//
//Return the maximum total sum that the height of the buildings can be increased by without changing the city's skyline from any cardinal direction.


//Input: grid =
// [[3,0,8,4],
//  [2,4,5,7],
//  [9,2,6,3],
//  [0,3,1,0]]
//Output: 35
//Explanation: The building heights are shown in the center of the above image.
//The skylines when viewed from each cardinal direction are drawn in red.
//The grid after increasing the height of buildings without affecting skylines is:
//gridNew = [ [8, 4, 8, 7],
//            [7, 4, 7, 7],
//            [9, 4, 8, 7],
//            [3, 3, 3, 3] ]
public class MaxIncreasetoKeepCitySkyline {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        // For a particular building, calculate maximum height in its row and column: maxRow and maxCol. Since we can't affect skyline from any direction,
        // the building height can be increased to a minimum of maxRow and maxCol
        // for first building 3, maxRow is 8, maxCol is 9. we can increase the height to minimum of 8 and 9,i.e, 8.
        int[] rowMax = new int[grid.length];
        int[] colMax = new int[grid[0].length];

        for(int row= 0; row < grid.length; row++) {
            for(int col =0; col < grid.length; col++) {
                rowMax[row] = Math.max(rowMax[row], grid[row][col]);
                colMax[col] = Math.max(colMax[col], grid[row][col]);
            }
        }
        int increase = 0;

        for(int row= 0; row < grid.length; row++) {
            for(int col =0; col < grid.length; col++) {
                int newValue = Math.min(rowMax[row], colMax[col]);
                increase+=newValue-grid[row][col];
            }
        }
        return increase;
    }
}
