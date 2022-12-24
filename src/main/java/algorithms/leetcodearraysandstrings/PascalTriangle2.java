package algorithms.leetcodearraysandstrings;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle2 {

    public List<Integer> getRow(int numRows) {
        List<Integer> row = new ArrayList<>();
        for(int i=0; i<=numRows; i++) {
            row.add(0, 1); // O(n), shifting all elements
            for(int j=1; j< row.size()-1; j++) {
                row.set(j, row.get(j) + row.get(j+1));
            }
        }
        return row;

    }

    public List<Integer> getRow1(int numRows) {
        List<Integer> row = new ArrayList<>();
        for(int i=0; i<=numRows; i++) {
            for(int j=row.size()-1; j>0; j--) {
                row.set(j, row.get(j) + row.get(j-1));
            }
            row.add(1);
        }
        return row; // check PascalTriangle class for explanations of both solutions

    }
}
