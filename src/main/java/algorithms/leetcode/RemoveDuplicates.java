package algorithms.leetcode;

public class RemoveDuplicates {
  public int removeDuplicates(int[] nums) {
    int alreadySortedElementsIndex =0;

    for(int trackerIndex = 1; trackerIndex < nums.length ;trackerIndex++) {
      if(nums[trackerIndex] != nums[alreadySortedElementsIndex]) {
        nums[++alreadySortedElementsIndex] =nums[trackerIndex];
      }
    }
    return alreadySortedElementsIndex + 1;
  }
}
