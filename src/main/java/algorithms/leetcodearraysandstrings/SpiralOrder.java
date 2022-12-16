package algorithms.leetcodearraysandstrings;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {

    enum Direction {UP, DOWN, RIGHT, LEFT};

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int row = 0;
        int col = 0;
        int totalRows = matrix.length;
        int totalCols = matrix[0].length;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        Direction direction = Direction.RIGHT;
        for(int i=0; i< matrix.length*matrix[0].length; i++) {
            result.add(matrix[row][col]);
            visited[row][col] = true;
            switch (direction) {
                case RIGHT: {
                    int nextCol = col+1;
                    if(nextCol< totalCols && !visited[row][nextCol]) {
                        col++;
                    } else {
                        row++;
                        direction =Direction.DOWN;
                    }
                    break;
                }
                case DOWN: {
                    int nextRow = row+1;
                    if(nextRow< totalRows && !visited[nextRow][col]) {
                        row++;
                    } else {
                        col--;
                        direction =Direction.LEFT;
                    }
                    break;
                }
                case LEFT: {
                    int previousCol = col-1;
                    if(previousCol>=0 && !visited[row][previousCol]) {
                        col--;
                    } else {
                        row--;
                        direction =Direction.UP;
                    }
                    break;
                }
                case UP: {
                    int nextRow = row-1;
                    if(nextRow>=0 && !visited[nextRow][col]) {
                        row--;
                    } else {
                        col++;
                        direction =Direction.RIGHT;
                    }
                    break;
                }
            }
        }
        return result;
    }

    public List<Integer> spiralOrder1(int[][] matrix) {
        // simulation
        // define boundaries
        int top =0;
        int bottom = matrix.length -1;
        int left = 0;
        int right = matrix[0].length -1;
        List<Integer> result = new ArrayList<>();
        while (top <=bottom && left <=right) {
            for(int i=left; i<= right;i++) {
                result.add(matrix[top][i]); // top is fixed, i is moving from left to right
            }
            top++; // row is completed, so increment top boundary

            for(int i=top ; i <= bottom; i++) {
                result.add(matrix[i][right]); // right is fixed, i going from top to bottom
            }
            right--; // column completed, decrement right boundary
            if(top<=bottom) {  // have to add extra check here because it is possible that top and bottom boundaries have overlapped but right and left haven't yet. If we don't add this check, duplicate element gets added in result
                for(int i = right; i>=left ; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if(left<=right) {
                for(int i=bottom; i>=top; i--) {
                    result.add(matrix[i][left]);
                }
            }
            left++;
        }
        return result;
    }

    public static void main(String[] args) {
        new SpiralOrder().spiralOrder1(new int[][]{{1,2, 3, 4},{5, 6, 7, 8},{9, 10, 11, 12}});
    }
}
