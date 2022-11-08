package algorithms.leetcoderecurion2;

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


}
