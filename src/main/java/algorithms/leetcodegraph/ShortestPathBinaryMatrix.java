package algorithms.leetcodegraph;

import java.util.LinkedList;
import java.util.Queue;

//Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
//
//A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
//
//All the visited cells of the path are 0.
//All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
//The length of a clear path is the number of visited cells of this path.
public class ShortestPathBinaryMatrix {
    int[][] directions = new int[][] {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0]==1) { // if the top left corner iteself is not 0, there is no clear path, return -1
            return -1;
        }
        if(grid.length==1 && grid[0].length==1 && grid[0][0]==0) {
            return 1;
        }
        // use BFS. For all 8 directions, push the cells with value 0 to the q
        Queue<Cell> q = new LinkedList<>();
        int length = 1 ; // length of shortest path
        q.offer(new Cell(0,0));
        boolean[][] visited = new boolean[grid.length][grid[0].length];
//[[0,0,0],
// [1,1,0],
// [1,1,1]]
        while (!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i< size; i++) {
                Cell c = q.poll();
                for(int dir = 0; dir < directions.length; dir++) {
                    int nextRow = c.row + directions[dir][0];
                    int nextCol = c.col + directions[dir][1];
                    if(nextRow <0 || nextCol < 0 || nextRow >= grid.length || nextCol >= grid[0].length || visited[nextRow][nextCol] || grid[nextRow][nextCol] ==1) {
                        continue;
                    }
                    if(nextRow== grid.length-1 && nextCol == grid[0].length -1) { // as soon as we find the bottom right corner, return length
                        return length+1;
                    }
                    visited[nextRow][nextCol] = true;
                    q.offer(new Cell(nextRow, nextCol));
                }
            }
            length++;
        }
        return -1;
    }

    private class Cell {
        int row;
        int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}



