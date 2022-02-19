package algorithms.patterns.cyclicsort;

public class DuplicateNumber {

  public static void main(String[] args) {
    System.out.println(findNumber(new int[]{1, 4, 4, 3, 2}));
    System.out.println(findNumber(new int[]{2, 1, 3, 3, 5, 4}));
    System.out.println(findNumber(new int[]{2, 4, 1, 4, 4}));
  }

  public static int findNumber(int[] nums) {
    int i=0;
    while (i< nums.length) {
      int j = nums[i] -1;
      if(nums[i] != nums[j]) {
        swap(nums, i, j);
      } else{
        if(i != j) {
          return nums[i];
        }
        i++;
      }
    }
    return 0;
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[j];
    nums[j] = nums[i];
    nums[i] = temp;
  }
}
