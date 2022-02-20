package algorithms.patterns.cyclicsort;

public class SmallestMissingPositiveNumber {

  public static void main(String[] args) {
    System.out.println(findNumber(new int[] {-3, 1, 5, 4, 2}));
    System.out.println(findNumber(new int[] {3, -2, 0, 1, 2}));
    System.out.println(findNumber(new int[] {3, 2, 5, 1}));
  }

  public static int findNumber(int[] nums) {
    int i=0;
    while(i< nums.length) {
      int j = nums[i] -1;
      if(j >= 0  && j < nums.length && nums[i] != nums[j] ) {
        swap(nums, i, j);
      } else {
        i++;
      }
    }

    for(i = 0; i< nums.length ; i++) {
      if(nums[i]-1 !=i) {
        return i+1;
      }
    }
    return -1;
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

}
