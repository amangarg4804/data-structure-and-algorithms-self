package algorithms.patterns.subset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArrayPermutations {

  public static void main(String[] args) {
    System.out.println(findPermutations(new int[]{1, 3, 5}));
  }

  public static List<List<Integer>> findPermutations(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Queue<List<Integer>> queue = new LinkedList<>();
    queue.add(new ArrayList<>());
    while (!queue.isEmpty()) {
      List<Integer> currentList = queue.poll();
      if (currentList.size() == nums.length) {
        result.add(currentList);
      } else {
        int currentIndex = currentList.size();
        for (int i = 0; i < currentList.size() + 1; i++) {
          List<Integer> newList = new ArrayList<>(currentList);
          newList.add(i, nums[currentIndex]);
          queue.add(newList);
        }
      }
    }
    return result;
  }
}
