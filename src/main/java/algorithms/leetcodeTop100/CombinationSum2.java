package algorithms.leetcodeTop100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {

  public static void main(String[] args) {
    System.out.println(combinationSum(new int[]{10, 1, 2, 7, 6, 1, 5},8));

  }
  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> resultList = new ArrayList<>();
    Arrays.sort(candidates);
    combinationSum(0, candidates, target, resultList, new ArrayList<>());
    return resultList;
  }

  private static void combinationSum(int currentIndex, int[] candidates, int target,
      List<List<Integer>> resultList, ArrayList<Integer> currentList) {
    if (target == 0 || currentIndex == candidates.length) {
      if (target == 0) {
        resultList.add(new ArrayList<>(currentList));
      }
      return;
    }

    for (int nextCurrentIndex = currentIndex; nextCurrentIndex < candidates.length;
        nextCurrentIndex++) {
      if (nextCurrentIndex > currentIndex && candidates[nextCurrentIndex] == candidates[
          nextCurrentIndex - 1]) {
        continue;
      }
      if (candidates[nextCurrentIndex] > target) {
        break;
      }
      currentList.add(candidates[nextCurrentIndex]);
      combinationSum(nextCurrentIndex + 1, candidates, target - candidates[nextCurrentIndex],
          resultList,
          currentList);
      currentList.remove(currentList.size() - 1);
    }
  }
}
