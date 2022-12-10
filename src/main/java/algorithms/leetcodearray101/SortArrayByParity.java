package algorithms.leetcodearray101;

// Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.
public class SortArrayByParity {

    public int[] sortArrayByParity(int[] nums) {
        // two pointer
        int start =0;
        int end =nums.length-1;
        while(start < end) {
            while(start <end && nums[start] %2!=0) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                end--;
            }
            start++;
        }
        return nums;
    }

        // snowball Approach
        public int[] sortArrayByParity1(int[] nums) {
            int odd =0;
            for (int i = 0; i < nums.length; i++) {
                if(nums[i]%2==0) { // As soon as even number is encountered, swap it with left most odd element tracked by odd, 1,3 , 5, 7, 2 -> swapping 2 with 1 will move all odd elements to the right of 2
                    int t = nums[i];
                    nums[i] = nums[odd];
                    nums[odd] = t;
                    odd++;
                }
            }
            return nums;
        }
}
