package algorithms.patterns.cyclicsort;

import java.util.ArrayList;
import java.util.List;

//We are given an unsorted array containing numbers taken from the range 1 to ‘n’. The array can have duplicates, which means some numbers will be missing. Find all those missing numbers.
public class FindAllMissingNumbers {

  public static void main(String[] args) {
    System.out.println(findMissingNumbers(new int[] {2, 3, 1, 8, 2, 3, 5, 1}));
    System.out.println(findMissingNumbers(new int[] {2, 4, 1, 2}));
    System.out.println(findMissingNumbers(new int[] {2, 3, 2, 1}));
  }

  public static List<Integer> findMissingNumbers(int[] nums) {
    List<Integer> missingNumbers = new ArrayList<>();
    int i=0;
    while (i < nums.length) {
      int j = nums[i] -1;
      if(nums[i] != nums[j] ) {
        swap(nums, i, j);
      } else {
        i++;
      }
    }
    for(int j = 0; j < nums.length; j++) {
      if(nums[j] != j+1) {
        missingNumbers.add(j+1);
      }
    }

    return missingNumbers;
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[j];
    nums[j] = nums[i];
    nums[i] = temp;
  }
}
