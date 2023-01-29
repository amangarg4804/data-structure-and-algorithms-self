package algorithms.leetcodehashtable;

import java.util.HashMap;
import java.util.Map;

// all arrays are of same length
// find number of tuples which sum to 0
public class FourSumIIToZero {

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // the problem doesn't mention that it requires unique tuples, so duplicate tuples are allowed.
        // brute force is to iterate over all fours arrays O(N^4)
        // How can we reduce complexity? - we can store 4th array elements in a hashmap and use 3 loops only
        // nums1[i] + nums2[j] + nums3[k] + nums4[l] = 0
        // nums1[i] + nums2[j] + nums3[k] = 0-nums4[l]
        // why hashmap and not a hashset? - because duplicates are allowed. Count needs to be incremented the number of times target is present
        // How to reduce complexity even further - to O(N^2)
        // We can store sum of all permutations of nums3 and nums4 in a map
        //  nums1[i] + nums2[j] +  = 0-(nums4[l] + nums3[k])
        Map<Integer, Integer> sumToFrequency = new HashMap<>();
        for(int i=0; i< nums3.length; i++) {
            for(int j=0; j< nums4.length; j++) {
                int sum = nums3[i] + nums4[j];
                sumToFrequency.put(sum, sumToFrequency.getOrDefault(sum, 0) +1);
            }
        }
        int count =0;
        for(int i=0; i< nums1.length;i++) {
            for(int j=0; j< nums2.length; j++) { // what if values at nums[j] and nums[j+1] are equal? Not an issue because we don't have to worry about duplicates in this problem
                int target = 0- (nums1[i]+nums2[j]);
//                if(sumToFrequency.containsKey(target)) {//check necessary to avoid null pointer
//                    count+= sumToFrequency.get(target);
//                }
                count+= sumToFrequency.getOrDefault(target, 0);
            }
        }
        return count;
    }
}
