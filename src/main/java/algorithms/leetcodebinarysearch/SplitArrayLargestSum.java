package algorithms.leetcodebinarysearch;

//Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
//
//Return the minimized largest sum of the split.
//
//A subarray is a contiguous part of the array.

//1 <= nums.length <= 1000
//0 <= nums[i] <= 106
//1 <= k <= min(50, nums.length)

//Input: nums = [7,2,5,10,8], k = 2
//Output: 18
//Explanation: There are four ways to split nums into two subarrays.
//The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
public class SplitArrayLargestSum {
    // Binary search on answer
    public int splitArray(int[] nums, int k) {
        // find the smallest and largest minimized largest sum
        // largest minimized largest sum  is when the array has to be splitted into subarray of size nums.length (k= nums.length), meaning the array itself
        // smallest minimized largest sum  of subarray will be largest element in array. when k =1. So every array element is a subarray.

        int largestMinimizedLargestSum = 0;
        int smallestMinimizedLargestSum = 0; // given array contains positive integers only
        for(int i=0; i< nums.length; i++) {
            largestMinimizedLargestSum+= nums[i];
            smallestMinimizedLargestSum = Math.max(smallestMinimizedLargestSum, nums[i]);
        }

        // we don't have to return the splitted arrays. We have to return the largest minimized sum
        // answer will lie between smallestMinimizedLargestSum and largestMinimizedLargestSum (both inclusive). We apply binary search
        int answer =0;
        while (smallestMinimizedLargestSum <= largestMinimizedLargestSum) { // it is possible that both are same. in case of array with one element only
            int mid = smallestMinimizedLargestSum + (largestMinimizedLargestSum -smallestMinimizedLargestSum)/2;

            // if mid is the answer. any subarray sum should be <= mid
            // we check whether mid is largest minimized sum
            // we check the count of subarrays required for mid to be the largest minimized sum
            // nums = [7,2,5,10,8], k = 2 [7,2,5] and [10,8],The best way is to split it into where the largest sum among the two subarrays is only 18.
            if(countSubarraysWithSumLessThanEqualToTarget(mid, nums) >k) {
                // meaning we have taken a very low value of mid. This will never be the answer
                smallestMinimizedLargestSum = mid+1;
            } else {
                // less subarrays mean it's a possible answer
                // E.g  // {3, 6, 2, 8, 4} and k =3. largest =23, smallest =8 . mid = 8+(23-8)/2 = 15
                // array is splitted to {3,6,2} and {8,4}. Both have sum less than 15. So 15 is a candidate for answer
                // if we can split the array into 2 and find that all subarray sum is less than 15.
                // then  we can definitely split the array into 3 subarrays, all subarrays sum will be less than 15
                    largestMinimizedLargestSum = mid-1; // we now try to find if 14 can be answer too, because we have to minimize the answer
                    answer = mid;
            }

        }
        //Once mid gets you to a value that works, the binary search will keep going down till you can find the minimum value where this condition is met (i.e. m partitions).
        // Any lower than this value and you will just have m-1 partitions, which won't work.
        // So the binary search algorithm keeps pushing the mid value lower to just that right number where you barely make m partitions.
        // This value has to be the sum of some contiguous elements of the array.

        return answer;
    }

    int countSubarraysWithSumLessThanEqualToTarget (int target, int[] nums){ // target lies between maximum element and sum of all elements
        int count =1;
        int sum =0;
        for(int i=0; i< nums.length; i++) { // {18, 1, 1} target will never be 10-> invalid case. target will be between 18(maximum element) and 20 (sum of all elements)
            if(sum+ nums[i] > target) {
                count++;
                sum = nums[i];
            } else {
                sum += nums[i];
            }
        }
        return count;
    }
}
