package algorithms.leetcoderecursion2;

public class NQueensII {

     int[] upwardColumn;
     int[] leftUpDiagnoal;
     int[] rightupDiagonal;
     NQueensII(int n) {
         upwardColumn = new int[n];
         leftUpDiagnoal = new int[2*n-1];  //NOTE: No of Diagonals in each direction in a nxn matrix are 2n-1;
         //      3 4 5 6
         //      2 3 4 5
         //      1 2 3 4
         //      0 1 2 3
         rightupDiagonal = new int[2*n-1];

  //       0 1 2 3
//         1 2 3 4
//         2 3 4 5
//         3 4 5 6
     }

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
            if(canBePlaced1(rowIndex, colIndex, board)) {
                placeQueen1(board, rowIndex, colIndex);
                if(rowIndex+1 == boardSize) {
                    count++;
                } else {
                    count = totalNQueens(boardSize, board, count, rowIndex+1);
                }
                removeQueen1(board, rowIndex, colIndex);
            }
        }
        return count;
    }


    private boolean canBePlaced1(int rowIndex, int colIndex, int[][] board) {
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


    private void placeQueen1(int[][] board, int rowIndex, int colIndex) {
        board[rowIndex][colIndex] =1;
    }


    private void removeQueen1(int[][] board, int rowIndex, int colIndex) {
        board[rowIndex][colIndex]=0;
    }

    private boolean canBePlaced2(int rowIndex, int colIndex, int[][] board) {
         return upwardColumn[colIndex] !='Q' && leftUpDiagnoal[rowIndex+colIndex]!='Q'
                 && rightupDiagonal[board.length-1 + colIndex -rowIndex]!='Q';
         //Note: Given the rowIndex and col index, right up diagonal value is rowIndex + colIndex.
        // left up diagonal value is board.length-1 + colIndex -rowIndex

    }

    private void placeQueen2(int[][] board, int rowIndex, int colIndex) {
        board[rowIndex][colIndex] =1;
        upwardColumn[colIndex] ='Q';  //NOTE: We are able to assign character value to integer array
        leftUpDiagnoal[rowIndex+colIndex]='Q';
        rightupDiagonal[board.length-1 + colIndex -rowIndex]='Q';

    }


    private void removeQueen2(int[][] board, int rowIndex, int colIndex) {
        board[rowIndex][colIndex]=0;
        upwardColumn[colIndex] = 0;
        leftUpDiagnoal[rowIndex+colIndex]=0;
        rightupDiagonal[board.length-1 + colIndex -rowIndex]=0;
    }

}
