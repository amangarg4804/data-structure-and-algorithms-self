package algorithms.leetcodeTop100;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> resultList = new ArrayList<>();
    combinationSum(0, candidates, target, resultList, new ArrayList<>());
    return resultList;
  }

  private void combinationSum(int currentIndex, int[] candidates, int target,
      List<List<Integer>> resultList, ArrayList<Integer> currentList) {
    if (target == 0 || currentIndex == candidates.length) {
      if (target == 0) {
        resultList.add( new ArrayList<>(currentList));
      }
      return;
    }
    //  accept currentIndex
    if (candidates[currentIndex] <= target) {
      currentList.add(candidates[currentIndex]);
      combinationSum(currentIndex, candidates, target - candidates[currentIndex], resultList,
          currentList);
      currentList.remove(currentList.size() - 1); // to backtrack
    }
    //  reject currentIndex
    combinationSum(currentIndex + 1, candidates, target - candidates[currentIndex + 1], resultList,
        currentList);
  }

}
