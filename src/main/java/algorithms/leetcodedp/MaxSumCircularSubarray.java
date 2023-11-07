package algorithms.leetcodedp;

//Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
//
//A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
//
//A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
//
//
//
//Example 1:
//
//Input: nums = [1,-2,3,-2]
//Output: 3
//Explanation: Subarray [3] has maximum sum 3.
//Example 2:
//
//Input: nums = [5,-3,5]
//Output: 10
//Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
//Example 3:
//
//Input: nums = [-3,-2,-3]
//Output: -2
//Explanation: Subarray [-2] has maximum sum -2.
//
//
//Constraints:
//
//n == nums.length
//1 <= n <= 3 * 104
//-3 * 104 <= nums[i] <= 3 * 104
public class MaxSumCircularSubarray {
    public int maxSubarraySumCircular(int[] nums) {
        // TLE 103 / 111 passed
        // For a circular array we can iterate the array beyond the actual length of the array
        // if an array is of length 3 (0,1,2 indexes). index 3 = 3%3 =0, index 4 = 4%3 =1
        // each index can be included atmost once.
        // How to restrict sum calculation if we are treating the array as circular?
        // we can use another for loop to restrict the max index.

        int max;
        int currentMax;
        int result= Integer.MIN_VALUE;
        for(int i=0; i< nums.length; i++) {
            // apply Kadane's algorithm for each index
            // for index 0, we will iterate till index = i+nums.length = 0+ 3 = < 3
            // for index 1, we will iterate till index = i+nums.length = 0+ 4 = < 4, index 3 will be 3%3 = 0
            max = nums[i];
            currentMax= nums[i];
            for( int j = i+1; j< i+nums.length; j++) {
                int index = j%nums.length;
                currentMax  = Math.max(nums[index], currentMax+nums[index]);
                max = Math.max(currentMax, max);
            }
            System.out.println("result for i=" + i + ":" + max);
            result = Math.max(result, max);
        }
        return result;
    }
    public int maxSubarraySumCircular2(int[] nums) {
        // another approach could be to find minimum sum subarray and then subtract it from the total sum
        // Equation: maxSumOfSubarray = total sum - minimum sum subarray sum
        // we have two corner cases here
        // 1. when all numbers are negative. In this case, minimum subaarray sum will be equal to total sum e.g -1, -2, -3
        // total sum = -6, minimum sum = -6, maximum sum = -1
        // in this case, maximum sum subarray is answer (regular kadane's algorithm)
        // 2. when all numbers are positive. In this case, minimum subaarray sum will be equal to total sum e.g 1, 2, 3
        // total sum = 6, minimum sum = 6, maximum sum = 6
        // In this case , we can't return the answer from equation mentioned above. total sum - minimum sum subarray sum is ZERO
        // in this case too, maximum sum subarray is answer (regular kadane's algorithm)
        // 3. There are also cases where total -minimum is less than maximum, so we should return the maxiumn
        // e.g [1,-2,3,-2]. Total = 0, min = -2, max = 3
        // total -min = 0 -(-2) =2. which is wrong
        int currentMax = nums[0];
        int max = nums[0];
        int currentMin = nums[0];
        int min = nums[0];
        int total = nums[0];
        for(int i=1; i< nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax+ nums[i]);
            max = Math.max(currentMax, max);

            currentMin = Math.min(nums[i], currentMin + nums[i]);
            min = Math.min(currentMin, min);
            total += nums[i];
        }
        if(total ==min) {
            return max;
        }
        return Math.max(total -min,max );
    }
}
