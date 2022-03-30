package algorithms.patterns.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsWithDuplicates {

  public static void main(String[] args) {
    System.out.println(subsets(new int[]{1, 3, 3}));
    System.out.println(subsets(new int[]{1, 5, 3, 3}));
  }

  private static List<List<Integer>> subsets(int[] arr) {
    Arrays.sort(arr);
    List<List<Integer>> subsets = new ArrayList<>();
    List<Integer> emptySubset = new ArrayList<>();
    subsets.add(emptySubset);

    int previousSubsetsStart = 0;
    int previousSubsetsEnd = subsets.size() - 1;
    for (int i = 0; i < arr.length; i++) {
      if (i != 0 && arr[i - 1] == arr[i]) {
        for (int j = previousSubsetsStart; j <= previousSubsetsEnd; j++) {
          List<Integer> subset = new ArrayList<>(subsets.get(j));
          subset.add(arr[i]);
          subsets.add(subset);
        }
      } else {
        int existingSubsetSize = subsets.size();
        for (int j = 0; j < existingSubsetSize; j++) {
          List<Integer> subset = new ArrayList<>(subsets.get(j));
          subset.add(arr[i]);
          subsets.add(subset);
        }
      }
      previousSubsetsStart = previousSubsetsEnd + 1;
      previousSubsetsEnd = subsets.size() - 1;
    }
    return subsets;
  }
}
