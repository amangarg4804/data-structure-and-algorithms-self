package algorithms.leetcoderecursion2;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
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
        s = s.substring(0, colIndex) + '.' + s.substring(colIndex+1); // Note: to replace/set a character in String at a particular index
        solution.set(rowIndex, s);
    }

    private void placeQueen(List<String> solution, int rowIndex, int colIndex) {
        String s = solution.get(rowIndex);
        s = s.substring(0, colIndex) + 'Q' + s.substring(colIndex+1); // NOTE: substring has first index inclusive and 2nd index exclusive
        // "hamburger".substring(4, 8) returns "urge". substring with only one parameter, i.e, beginIndex, returns the substring starting from beginIndex (inclusive) to end
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
}
