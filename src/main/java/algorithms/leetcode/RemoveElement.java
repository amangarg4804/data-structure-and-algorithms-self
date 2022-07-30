package algorithms.leetcode;

public class RemoveElement {

  public int removeElement(int[] nums, int val) {
    if(nums.length==0) {
      return 0;
    }
    int left = 0 ;
    int right = nums.length-1;

    while(left < right) {
      if(nums[left] == val) {
        swap(nums, left, right);
        right--;
      } else {
        left++;
      }
    }
    if( nums[left] == val) {
      left--;
    }
    return left +1;

  }

  private void swap(int[] nums, int left, int right) {
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;
  }

  public int removeElementMethod2(int[] nums, int val) {
    if(nums.length==0) {
      return 0;
    }
    int correct = 0 ;
    for(int i=0; i< nums.length; i++) {
      if(nums[i] !=val) {
        nums[correct++] = nums[i];
      }
    }
    return correct;
  }
}
