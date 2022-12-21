package algorithms.leetcodearraysandstrings;

import java.util.Arrays;

//Given an integer array nums of 2n integers,
// group these integers into n pairs (a1, b1), (a2, b2), ..., (an, bn) such that the sum of min(ai, bi) for all i is maximized. Return the maximized sum.
public class ArrayPartition1 {
    public int arrayPairSum0(int[] nums) {
        Arrays.sort(nums);
        int sum =0;
        for(int i=0; i< nums.length; i=i+2) {
            sum+=nums[i];
        }
        return sum;
    }

    public int arrayPairSum1(int[] nums) {
        // two pointers
        Arrays.sort(nums);
        int sum =0;
        int start = 0;
        int end = nums.length-2; //2nd last number because we have to take minimum number among the pair
        while (start <end) {// given that array contains 2n integers, so left and right should never meet
            sum+=nums[start] + nums[end];
            start+=2;
            end-=2;
        }
        return (nums.length/2) %2 ==0? sum: sum+nums[start]; // in case length/2 is odd, the last number of start index is not added in loop. Example: // [6,2,6,5,1,2]-> [1, 2, 2, 5, 6, 6]-> 1,6 is added, end becomes 2, start also becomes 2. loop doesn't run
    }

    public int arrayPairSum2(int[] nums) {
        // counting sort
        int[] counts = new int[20001]; // range is -10^4 to -10^4 , adding 1 for 0

        for(int i=0; i< nums.length;i++) {
            counts[nums[i] +10000]++;
        }
        boolean min =true;
        int sum =0;
        for(int i=0; i< counts.length; i++) {
            while (counts[i]>0) { // we can have duplicate elements
                if(min) {
                    sum +=i-10000;
                }
                counts[i]--;
                min = !min;
            }
        }
        return sum;
    }

}
