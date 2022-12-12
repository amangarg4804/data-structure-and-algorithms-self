package algorithms.leetcodearray101;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisappearedNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // wrong solution
        Arrays.sort(nums);
        // 1, 2, 2, 3, 3, 4, 7, 8
        // 0, 1, 2, 3, 4, 5, 6, 7
        // 3 exists but it still includes it
        List<Integer> result = new ArrayList<>();
        for( int i=0; i< nums.length ; i++) {
            if(nums[i] != i+1) {
                result.add(i+1);
            }
        }
        return result;
    }

    public List<Integer> findDisappearedNumbers1(int[] nums) {
        // cyclic sort
        List<Integer> result = new ArrayList<>();
        int i=0;
        while(i< nums.length) {
            int correctIndex = nums[i]-1; // what is the correct index for nums[i]? it is nums[i]-1. If number at nums[i] -1 is not equal to num[i], we should swap.
            // Note that in case number at nums[i] -1 is already equal to nums[i]
            // either the number is at its correct place (meaning indexes nums[i] -1 and i are equal) or there are duplicate numbers (when indexes nums[i-1] && i are not equal)
            if(nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }

        for(int j=0; j< nums.length;j++) {
            if(nums[j] != j+1) {
                result.add(j+1);
            }
        }
        return result;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }


}
