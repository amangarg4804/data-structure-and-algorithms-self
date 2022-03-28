package algorithms.patterns.subset;

import java.util.ArrayList;
import java.util.List;

//Given a set with distinct elements, find all of its distinct subsets.
public class Subsets {

  public static void main(String[] args) {
    System.out.println(findSubsets(new int[]{1, 3}));
    System.out.println(findSubsets(new int[]{1, 5, 3}));
  }

  public static List<List<Integer>> findSubsets(int[] nums) {
    // time: O(2^N), space = O(2^N)
    List<List<Integer>> subsets = new ArrayList<>();
    subsets.add(new ArrayList<>());

    for (int i = 0; i < nums.length; i++) {
      int subsetSize = subsets.size();
      for (int j = 0; j < subsetSize; j++) {
        List<Integer> subset = new ArrayList<>(subsets.get(j));
        subset.add(nums[i]);
        subsets.add(subset);
      }
    }
    return subsets;
  }
}
