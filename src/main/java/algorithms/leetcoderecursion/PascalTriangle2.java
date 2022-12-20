package algorithms.leetcoderecursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PascalTriangle2 {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        for(int j=0; j<= rowIndex; j++) {
            int value = getElement(rowIndex, j);
            result.add(value);
        }
        return result;
    }

    private int getElement(int i, int j) {
        // recursive, TLE for values greater than 28
        if(j==0 || i==j) {
            return 1;
        }
        return getElement(i-1, j-1) + getElement(i-1, j);
    }
}

class PascalTriangle2Memoization {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        if(rowIndex == 0) {
            return result;
        }
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int j=1; j< rowIndex; j++) {
            int value = getElement(rowIndex, j,map);
            result.add(value);
        }
        result.add(1);
        return result;
    }

    private int getElement(int i, int j, Map<Integer, Map<Integer, Integer>> map ) {
        // TLE for values greater than 28
        if(map.containsKey(i)) {
            map.get(i);
            if(map.get(i).containsKey(j)) {
                return map.get(i).get(j);
            }
        }
        if(j==0 || i==j) {
            return 1;
        }
        int element1 = getElement(i-1, j-1, map);
        int element2 = getElement(i-1, j, map);
        Map<Integer, Integer> rowMap = map.getOrDefault(i , new HashMap<>());
        rowMap.put(j, element1+element2);
        return element1 + element2;
    }
}

class PascalTriangle2Iterative {

    public List<Integer> getRow(int rowIndex) {

        // Working
        List<Integer> previous  = new ArrayList<>();
        previous.add(1);

        for(int i = 1; i<=rowIndex; i++ ) {
            List<Integer> currentRow = new ArrayList<>();
            for(int j= 0; j<=i; j++) {
                int num;
                if(j==0 || j==i) {
                    num =1;
                } else {
                    num = previous.get(j-1) + previous.get(j);
                }

                currentRow.add(num);
            }
            previous = currentRow;

        }
        return previous;
    }
}

class PascalTriangle2Dp {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row =
                new ArrayList<>(rowIndex + 1);
        row.add(1);

        for (int i = 0; i < rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
            row.add(1);
        }

        return row;
    }
}