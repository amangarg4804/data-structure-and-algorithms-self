package algorithms.interviews.salesforceinterview.oa;

import java.util.HashMap;
import java.util.Map;

//Given an integer array nums and an integer k, return the number of good subarrays of nums.
//
//A good array is an array where the number of different integers in that array is exactly k.
//
//For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
//A subarray is a contiguous part of an array.
//
//Example 1:
//
//Input: nums = [1,2,1,2,3], k = 2
//Output: 7
//Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
//Example 2:
//
//Input: nums = [1,2,1,3,4], k = 3
//Output: 3
//Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
//
//Constraints:
//
//1 <= nums.length <= 2 * 10^4
//1 <= nums[i], k <= nums.length
public class SubarraysWithKDistinct {
    public int subarraysWithKDistinct(int[] nums, int k) {
        // brute force approach will be to check every subarray- 45/55 test cases passed - TLE
        int subarrayCount = 0;
        for(int i = 0; i< nums.length; i++) {
            // As per constraints 1<= nums[i] <=nums.length, So we can create an array of size nums.length + 1 and check frequency of each integer
//            int[] freq = new int[nums.length +1]; // length + 1 because we will use indices from 1 to nums.length
            // subarrays starting from i
            Map<Integer, Integer> map = new HashMap<>();
            map.put(nums[i], 1);
            // k could be 1 too, in that case, each integer in the array is a valid subarray
            if(k==1) {
                subarrayCount++;
            }
            for( int j=i+1; j< nums.length; j++) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) +1); // increment frequency of number at jth position
                // now we have to check whether the subarray currently formed has frequency greater than 1 for exactly k integers.
                // This step is easier with Map than an array, because we can use the function size of map. With an array, we will have to iterate and sum all number s with frequency greater than 1
                if(map.size()==k) {
                    subarrayCount++;
                }
                // if map size is already more than k,this means that total number of different integers are already more than k,  no need to check further for more values of j
                if(map.size()>k) {
                    break;
                }

            }
        }
        return subarrayCount;
        // time complexity O(n*n) two for loops
        // space complexity O(n)- map is constant size
    }


    public int subarraysWithKDistinct2(int[] nums, int k) {
        //https://www.youtube.com/watch?v=akwRFY2eyXs
        // Optimized approach- sliding window
        // We can find number of subarrays with atmost k distinct numbers and atmost k-1 distint numbers
        // and subtract them to get number of subarrays with exactly k distinct numbers
        // 1, 2, 1, 3 , k = 3
        // subarray {1,2,1} has atmost 3 . What is the total number of subarrays ending at windowEnd(the last 1)? It is the window size, that is 3 -> {1,2,1}, {2,1}, {1}
        return atmost(nums, k) -atmost(nums, k-1);

    }

    private int atmost(int[] nums, int k) {
        int windowStart = 0;
        int windowEnd =0;
        int count = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();
        while (windowEnd < nums.length) {
            freqMap.put(nums[windowEnd], freqMap.getOrDefault(nums[windowEnd], 0) +1);
            while (freqMap.size() > k) {
                freqMap.put(nums[windowStart], freqMap.get(nums[windowStart])-1);
                if(freqMap.get(nums[windowStart]) ==0) {
                    freqMap.remove(nums[windowStart]);
                }
                windowStart++;
            }
            count += windowEnd -windowStart +1 ;
            windowEnd++;
        }
        return count;
    }


}
