package algorithms.agodainterview;

import java.util.*;

// https://www.algoexpert.io/questions/River%20Sizes#:~:text=You're%20given%20a%20two,(but%20not%20diagonally%20adjacent).
public class RiverSizes {

  public static List<Integer> riverSizes(int[][] matrix) {
    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    List<Integer> result = new ArrayList<Integer>();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        int currentRiverSize = riverSizes(matrix, i, j, visited);
        // System.out.println(currentRiverSize);
        if (currentRiverSize > 0) {
          result.add(currentRiverSize);
        }

      }
    }
    return result;
  }


  public static int riverSizes(int[][] matrix, int x, int y, boolean[][] visited) {
    if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length || visited[x][y]) {
      return 0;
    }
    if (matrix[x][y] == 0) {
      return 0;
    }
    visited[x][y] = true;
    return 1
        + riverSizes(matrix, x - 1, y, visited)
        + riverSizes(matrix, x + 1, y, visited)
        + riverSizes(matrix, x, y + 1, visited)
        + riverSizes(matrix, x, y - 1, visited);
  }

}
