package algorithms.patterns.cyclicsort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateNumbers {

  public static void main(String[] args) {
    System.out.println(findNumbers(new int[] {3, 4, 4, 5, 5}));
    System.out.println(findNumbers(new int[] {5, 4, 7, 2, 3, 5, 3}));
  }

  public static List<Integer> findNumbers(int[] nums) {
    List<Integer> list = new ArrayList<>();
    int i = 0;
    while (i< nums.length) {
      int j = nums[i] -1;
      if(nums[i] != nums[j]) {
        swap(nums, i , j);
      } else {
        i++;
      }
    }
    for(int j = 0; j < nums.length ;j++) {
      if(nums[j] != j+1) {
        list.add(nums[j]);
      }
    }
    return list;
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[j];
    nums[j] = nums[i];
    nums[i] = temp;
  }
}
