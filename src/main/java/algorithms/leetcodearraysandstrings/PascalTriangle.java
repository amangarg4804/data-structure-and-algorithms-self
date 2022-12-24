package algorithms.leetcodearraysandstrings;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> previous =null;
        for(int i=0; i< numRows; i++) {
            List<Integer> current = new ArrayList<>();
            if(previous ==null) {
                current.add(1);
                previous = current;
                result.add(current);
                continue;
            }
            current.add(1);
            for(int j=1; j< previous.size(); j++) {
                current.add(previous.get(j-1) + previous.get(j));
            }
            current.add(1);
            previous = current;
            result.add(current);
        }
        return result;
    }

    public List<List<Integer>> generate1(int numRows) {
        // same logic but concise
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> previous = null;
        for(int i = 0; i< numRows; i++) {
            List<Integer> current = new ArrayList<>();
            for(int j=0; j<=i; j++) {
                if(j==0 || j==i) {
                    current.add(1);
                } else {
                    current.add(previous.get(j-1) + previous.get(j));
                }
            }
            result.add(current);
            previous = current;
        }
        return result;
    }

    public List<List<Integer>> generate2(int numRows) {
        // another approach is to add 1 to each row
        // if the index is not 0 and last index. the value of a particular index is always current value  + value on next index
        // E.g.
        //       1
        //      1  1
        //     1  2  1
        // Now add 1 to previous 1 1 2 1
        // add next index's value (except for indices 0 and last)
        // 1 3 3 1 -> this is the resulting row
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        for(int i = 0; i< numRows; i++) {
            row.add(0, 1); // add 1 to 0th index
            for(int j=1; j<row.size()-1; j++) {
                    row.set(j, row.get(j) + row.get(j+1));
            }
            result.add(new ArrayList<>(row));
        }
        return result;
    }

    public List<List<Integer>> generate3(int numRows) {
        // Above approach always added 1 to the start of list which is O(n) as it requires shifting the elements
        // What we can do is to iterate from end of list and add previous element to each index except 0 index
        // E.g.
        //       1
        //      1  1
        //     1  2  1
        // add previous element to end element it becomes 1 2 3,  add previous element to 2 -> 1, 3, 3
        //
        // Now add 1 to the end of list which is O(1)
        // 1 3 3 1 -> this is the resulting row
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        for(int i = 0; i< numRows; i++) {
            for(int j=row.size()-1; j>0; j--) {
                row.set(j, row.get(j) + row.get(j-1));
            }
            row.add(1);
            result.add(new ArrayList<>(row));
        }
        return result;
    }
}
