package algorithms.leetcode;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

  public static void main(String[] args) {
    char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
    System.out.println(isValidSudoku(board));
  }

  public static boolean isValidSudoku(char[][] board) {
    for (int i = 0; i < 9; i++) {
      Set<Character> rowSet = new HashSet<>();
      Set<Character> columnSet = new HashSet<>();
      Set<Character> cube = new HashSet<>();
      for (int j = 0; j < 9; j++) {
        if (board[i][j] != '.' && !rowSet.add(board[i][j])) {
          return false;
        }
        if (board[j][i] != '.' && !columnSet.add(board[j][i])) {
          return false;
        }
        int cubeRowIndex = 3 * (i / 3) + (j/3);
        int cubeColIndex = 3 * (i % 3) + (j%3);
        if (board[cubeRowIndex][cubeColIndex] != '.' &&
            !cube.add(board[cubeRowIndex][cubeColIndex])) {
          return false;
        }
      }
    }
    return true;
  }
}
