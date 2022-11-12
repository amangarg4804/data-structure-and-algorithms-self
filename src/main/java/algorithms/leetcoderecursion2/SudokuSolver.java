package algorithms.leetcoderecursion2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// It is guaranteed that the input board has only one solution.
public class SudokuSolver {
    public boolean solveSudoku(char[][] board) {
        for(int rowIndex=0; rowIndex< 9; rowIndex++) {
            for(int colIndex= 0; colIndex<9; colIndex++) {
                if(board[rowIndex][colIndex]!='.') {
                    continue;
                }
                for(char i='1'; i<='9'; i++) {  // NOTE: We can iterate over character just like an integer
                    if(isValid(rowIndex, colIndex, board, i)) {
                        place(rowIndex, colIndex, board, i);
                        if(solveSudoku(board)) { // It will return true only after we have reached the end of sudoku solution. Like DFS. As the stack unfolds, it will keep on returning true
                            return true; // We have to return boolean from here instead of void so that remove is never called after we have solved sudoku
                        } else {
                            remove(rowIndex, colIndex, board);
                        }
                    }
                }
                return false; // if none of the numbers from 1-9 pass the isValid check, we return false.
                // There is no solution with this arrangement of sudoku, and we need to try other permutations. After this return statement, remove will be called at line 21
            }
        }
        return true;
    }

    private void remove(int rowIndex, int colIndex, char[][] board) {
        board[rowIndex][colIndex] ='.';
    }

    private void place(int rowIndex, int colIndex, char[][] board, char i) {
        board[rowIndex][colIndex] = i;
    }

    private boolean isValid(int rowIndex, int colIndex, char[][] board, char num) {
        for(int i=0; i< 9; i++) {
            if(board[rowIndex][i]==num) {
                return false;
            }
            if(board[i][colIndex]==num) {
                return false;
            }
            int cubeRowIndex = 3 * (rowIndex / 3) + i / 3;
            // NOTE: let's say the element is 2,2. Meaning it is in first cube.
            // rowIndex/3 always returns 0. it doesn't change throughout the loop
            // i/3 returns:
            // 0 for i =0,1,2,
            // 1 for i=3,4,5
            // 2 for i=6, 7, 8
            // So, we are able to cover all three rows (rowIndexes 0, 1, 2 )for first cube
            int cubeColIndex = 3 * (colIndex / 3) + i % 3;
            // NOTE: Here the difference is using i%3 instead of i/3
            if (board[cubeRowIndex][cubeColIndex] == num)
                return false;
        }
        return true;
    }

}
