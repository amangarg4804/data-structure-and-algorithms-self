package algorithms.patterns.cyclicsort;

import java.util.Arrays;

// Write a function to sort the objects in-place on their creation sequence number in O(n) and without any extra space.
public class CyclicSort {

  public static void main(String[] args) {
    int[] arr1 = new int[] {3, 1, 5, 4, 2};
    sortSingleLoopOtherWay(arr1);
    System.out.println(Arrays.toString(arr1));

    int[] arr2 = new int[] {2, 6, 4, 3, 1, 5};
    sortSingleLoopOtherWay(arr2);
    System.out.println(Arrays.toString(arr2));

    int[] arr3 = new int[] {1, 5, 6, 4, 3, 2};
    sortSingleLoopOtherWay(arr3);
    System.out.println(Arrays.toString(arr3));

  }

  public static void sort(int[] nums) {
    for(int i =0; i< nums.length ;i++) {
      while (nums[i] != i+1) {
        swap(nums, i);
      }
    }
  }

  private static void swap(int[] nums, int i) {
    int temp = nums[nums[i] - 1];
    nums[nums[i] - 1] = nums[i];
    nums[i] = temp;
  }

  public static void sortSingleLoop(int[] nums) {

    int i=0;
    while (i< nums.length) {
      if(nums[i] != i+1)  {
        swap(nums, i);
      } else {
        i++;
      }
    }
  }

  public static void sortSingleLoopOtherWay(int[] nums) {
   // int[] arr2 = new int[] {2, 6, 4, 3, 1, 5};
    int i=0;
    while (i< nums.length) {
      int correctIndex = nums[i] - 1;
      if(nums[i] != nums[correctIndex])  {
        swap(nums, i, correctIndex);
      } else {
        i++;
      }
    }
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[j];
    nums[j] = nums[i];
    nums[i] = temp;
  }



}
