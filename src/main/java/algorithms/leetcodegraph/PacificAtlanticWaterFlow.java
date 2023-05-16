package algorithms.leetcodegraph;

import java.util.ArrayList;
import java.util.List;
//There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges,
// and the Atlantic Ocean touches the island's right and bottom edges.
//
//The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights
// where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
//
//The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east,
// and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
//
//Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci)
// to both the Pacific and Atlantic oceans.



//[[1,2,2,3,5],
// [3,2,3,4,4],
// [2,4,5,3,1],
// [6,7,1,4,5],
// [5,1,1,2,4]]
public class PacificAtlanticWaterFlow {
    int[][] directions = {{0, 1},{1, 0},{0, -1},{-1,0}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // Pacific ocean = y<0  ||  x <0
        // Atlantic ocean = x >=heights.length || y> =heights[0].length
        // we have to move in all 4 directions and check whether the rain will reach both pacific and atlantic ocean
        // we can check one by one for both oceans for each position
        // if the number is 0, rain will not flow in any direction
        List<List<Integer>> result = new ArrayList<>();
        for(int row=0; row< heights.length;row++) {
            for(int col = 0; col < heights[0].length ; col++) {
                boolean[][] visited1 = new boolean[heights.length][heights[0].length];
                boolean[][] visited2 = new boolean[heights.length][heights[0].length];
                visited1[row][col] = true;
                visited2[row][col] = true;
                if(canFlowToPacific(heights, row, col, visited1)
                        && canFlowToAtlantic(heights, row, col, visited2)) {
                    result.add(List.of(row, col));
                }
            }
        }
        return result;
    }
    // think of the case where there is only one element in grid
    // will we iterate over all directions for this case? yes
    private boolean canFlowToAtlantic(int[][] heights, int row, int col, boolean[][] visited) {

        for(int dir =0; dir < directions.length; dir++) {
            int nextRow = row + directions[dir][0];
            int nextCol = col + directions[dir][1];
            if(nextRow >=heights.length || nextCol>=heights[0].length) {
                return true; // rain water flowing to atlantic
            }
            if(nextRow < 0 || nextCol < 0 || visited[nextRow][nextCol] || heights[row][col] < heights[nextRow][nextCol]) {
                continue; // rain water flowing to pacific or the cell was already visited or current height is less than neighbouring height
            }
            // rain water has not reached either pacific or atlantic. It can flow to nextRow, nextCol if heights[row][col] >= heights[nextRow][nextCol]
            // think of the case with 4 elements
            //  4 2
            //  5 6
            // will water from 4 flow to atlantic ? yes
            visited[nextRow][nextCol] =true;
            if(canFlowToAtlantic(heights, nextRow, nextCol, visited)) {
                return true;
            }
        }
        return false;
    }

    private boolean canFlowToPacific(int[][] heights, int row, int col, boolean[][] visited) {
        for(int dir =0; dir < directions.length; dir++) {
            int nextRow = row + directions[dir][0];
            int nextCol = col + directions[dir][1];
            if(nextRow < 0 || nextCol < 0 ) {
                return true; // rain water flowing to pacific
            }
            if(nextRow >=heights.length || nextCol>=heights[0].length || visited[nextRow][nextCol] || heights[row][col] < heights[nextRow][nextCol]) {
                continue; // rain water flowing to atlantic or the cell was already visited or current height is less than neighbouring height
            }
            // rain water has not reached either pacific or atlantic. It can flow to nextRow, nextCol if heights[row][col] >= heights[nextRow][nextCol]
            // think of the case with 4 elements
            //  2 4
            //  5 6
            // will water from 4 flow to pacific ? yes
            visited[nextRow][nextCol] =true;
            if(canFlowToPacific(heights, nextRow, nextCol, visited)) {
                return true;
            }
        }
        return false;
    }
}
