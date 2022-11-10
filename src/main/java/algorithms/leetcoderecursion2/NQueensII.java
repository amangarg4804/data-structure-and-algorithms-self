package algorithms.leetcoderecursion2;

import java.util.ArrayList;
import java.util.List;

public class NQueensII {



    public int totalNQueens(int n) {

        int count = 0;
        int[][] board = new int[n][n];
        return totalNQueens(n, board, count, 0);
    }

    private int totalNQueens(int boardSize, int[][] board, int count, int rowIndex) {
        if(rowIndex >=boardSize) {
            return count;
        }
        for(int colIndex = 0; colIndex < boardSize; colIndex++) {
            if(canBePlaced(rowIndex, colIndex, board)) {
                placeQueen(board, rowIndex, colIndex);
                if(rowIndex+1 == boardSize) {
                    count++;
                } else {
                    count = totalNQueens(boardSize, board, count, rowIndex+1);
                }
                removeQueen(board, rowIndex, colIndex);
            }
        }
        return count;
    }

    private boolean canBePlaced(int rowIndex, int colIndex, int[][] board) {
        // Because we move down row by row, we need to check only 3 upward directions \|/
        int initRowIndex = rowIndex;
        int initColIndex = colIndex;

        //checking left-up
        while(rowIndex>=0 && colIndex>=0) {
            if(board[rowIndex][colIndex] ==1) {
                return false;
            }
            rowIndex--;
            colIndex--;
        }

        rowIndex = initRowIndex;
        colIndex = initColIndex;
        // straight up
        while(rowIndex >=0) {
            if(board[rowIndex][colIndex] ==1) {
                return false;
            }
            rowIndex--;
        }
        rowIndex = initRowIndex;
        colIndex = initColIndex;
        // right-up
        while(rowIndex >=0 && colIndex< board.length) {
            if(board[rowIndex][colIndex] ==1) {
                return false;
            }
            rowIndex--;
            colIndex++;
        }
        return true;
    }


    private void placeQueen(int[][] board, int rowIndex, int colIndex) {
        board[rowIndex][colIndex] =1;
    }


    private void removeQueen(int[][] board, int rowIndex, int colIndex) {
        board[rowIndex][colIndex]=0;
    }


    //n queens, nxn board
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();

        List<String> solution = new ArrayList<>();
        for(int i=0; i<n;i++) {
            String s = ".".repeat(n);
            solution.add(s);
        }
        solveNQueens(n, solutions, solution, 0);
        return solutions;
    }

    private void solveNQueens(int n, List<List<String>> solutions, List<String> solution, int rowIndex) {

        if(rowIndex>=n) {
            return;
        }
        for(int colIndex =0; colIndex <n; colIndex++) {
            if(canBePlaced(rowIndex, colIndex, solution )) {
                placeQueen(solution, rowIndex, colIndex);
                if(rowIndex+1 == n) {
                    solutions.add( new ArrayList<>(solution));

                } else {
                    solveNQueens(n, solutions, solution, rowIndex + 1);
                }
                removeQueen(solution, rowIndex, colIndex);
            }
        }
    }

    private void removeQueen(List<String> solution, int rowIndex, int colIndex) {
        String s = solution.get(rowIndex);
        s = s.substring(0, colIndex) + '.' + s.substring(colIndex+1); // Trick to replace/set a character in String at a particular index
        solution.set(rowIndex, s);
    }

    private void placeQueen(List<String> solution, int rowIndex, int colIndex) {
        String s = solution.get(rowIndex);
        s = s.substring(0, colIndex) + 'Q' + s.substring(colIndex+1);
        solution.set(rowIndex, s);
    }

    private boolean canBePlaced(int rowIndex, int colIndex, List<String> solution) {
        //"...."
        //"...."
        //"...."
        //"...."
        int originalRowIndex = rowIndex;
        int originalColIndex = colIndex;

        while (rowIndex>=0 && colIndex>=0) {
            if(solution.get(rowIndex).charAt(colIndex)=='Q') {
                return false;
            }
            rowIndex--;
            colIndex--;
        }

        rowIndex = originalRowIndex;
        colIndex = originalColIndex;
        // straight up
        while(rowIndex >=0) {
            if(solution.get(rowIndex).charAt(colIndex) == 'Q') {
                return false;
            }
            rowIndex--;
        }
        rowIndex = originalRowIndex;
        colIndex = originalColIndex;
        // right-up
        while(rowIndex >=0 && colIndex< solution.size()) {
            if(solution.get(rowIndex).charAt(colIndex) == 'Q') {
                return false;
            }
            rowIndex--;
            colIndex++;
        }
        return true;
    }

    public static void main(String[] args) {
        new NQueensII().solveNQueens(4);
    }


}
