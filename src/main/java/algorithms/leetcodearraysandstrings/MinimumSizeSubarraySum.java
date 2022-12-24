package algorithms.leetcodearraysandstrings;
//Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
//
//
//
//        Example 1:
//
//        Input: target = 7, nums = [2,3,1,2,4,3]
//        Output: 2
//        Explanation: The subarray [4,3] has the minimal length under the problem constraint.
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        // time - O(n)- each element is visited at most 2 times for worst case - 1, 1, 1, 1,, 1, 1, 6 - for target 6
        //all number are positive, if that was not the case, we won't be able to apply sliding window
        int currentSum =0;
        int windowStart = 0;
        int minLength = Integer.MAX_VALUE;
        for(int windowEnd =0; windowEnd < nums.length ; windowEnd ++) {
            currentSum += nums[windowEnd];
            while (currentSum >= target && windowStart <=windowEnd) {
                minLength = Math.min(windowEnd-windowStart +1, minLength);
                currentSum-=nums[windowStart++];
            }

        }

        return minLength ==Integer.MAX_VALUE ? 0: minLength;
    }

    public int minSubArrayLen1(int target, int[] nums) {
        // binary search
        int minLength = Integer.MAX_VALUE;
        int[] sums = new int[nums.length +1];
        for(int i=1; i< sums.length; i++) {
            sums[i] = sums[i-1] + nums[i-1];
        }
        // 0, 2, 5, 6, 8, 12, 15
        // 0, 1, 2, 3, 4, 5, 6
        for(int i =1; i< sums.length;i++) {
            int foundIndex = binarySearch(i, sums.length-1, target+sums[i-1], sums);
            if(foundIndex == sums.length) { // if index is equal to array's length, we couldn't find the element, no need to check for other indexes, because sums array is strictly increasing given all numbers are positive
                break;
            }
            if (sums[foundIndex]-sums[i-1] >=target) {
                minLength = Math.min(minLength, foundIndex-i +1);
            }
        }

        return minLength ==Integer.MAX_VALUE ? 0: minLength;
    }

    private int binarySearch(int start, int end, int target, int[] sums) {
        while (start<=end) {
            int mid = start + (end -start)/2;
            if(sums[mid]==target) {
                return mid;
            }
            if(sums[mid] >target) {
                end = mid-1;
            } else {
                start =mid+1;
            }
        }
        return start;
        // Why return start ? -
        // Case 1: If in any case, condition sum[mid] >target satisfied, and we decremented end
        // and there is no other number greater than end. Every time the loop runs, it will increment start until start reaches end + 1 (because of while loop condition),
        // this end +1 is same as the value of end which was decremented
        // Case 2: if all the numbers are smaller than target, start will keep incrementing until it reaches end+1, meaning 1+ last index of array, meaning array's length, and at this point for loop in the calling method will break
    }

    public static void main(String[] args) {
        new MinimumSizeSubarraySum().minSubArrayLen1(7, new int[]{2,3,1,2,4,3});
    }
}
