package algorithms.leetcoderecursion2;

import java.util.ArrayList;
import java.util.List;

// Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

//        Input: n = 4, k = 2
//        Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
//        Explanation: There are 4 choose 2 = 6 total combinations.
//        Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        combine(1, n, k, result, current);
        return result;
    }

    private void combine(int combinationStartingNo, int n, int k, List<List<Integer>> result, List<Integer> current) {
        if(current.size()==k ) {
            result.add(new ArrayList<>(current));
            return;
        }
        for(int i= combinationStartingNo; i<=n; i++) {
                current.add(i);
                combine(i+1, n, k, result, current);
                current.remove(current.size() -1);
        }
    }
}
