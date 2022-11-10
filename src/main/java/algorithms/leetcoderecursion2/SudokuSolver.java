package algorithms.leetcoderecursion2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SudokuSolver {

    ArrayList<HashSet<Integer>> rows = new ArrayList<>();
    ArrayList<HashSet<Integer>> columns = new ArrayList<>();
    ArrayList<HashSet<Integer>> grids = new ArrayList<>();



    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }

    public void backtrack(char[][] board, int row, int col) {
         // 6/3 =2 +1 * 2+1
        // ((row/3 +1) * (col/3 +1))+1
        if(row >= board.length || col >=board[0].length || row <0 || col <0 ) {
            return;
        }
        for(int i=row; i< board.length; i++) {
            for(int j=1; j< 10; j++) {
//                if(board[i][col] != '.' && canBeFilled(board, i, col, j)) {
//                    fillPosition(board, i, col, j);
//                    backtrack(board, i, col+1);
//                    removePosition(board, i, col, j);
//                }
            }

        }
    }
}
