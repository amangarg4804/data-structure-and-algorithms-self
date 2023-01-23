package algorithms.leetcodehashtable;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.Math.abs;

//Given an integer array nums and an integer k,
// return true if there are two distinct indices i and j in the array such that
// nums[i] == nums[j] -- Numbers are equal
// and abs(i - j) <= k. -- distance is less than equal to k
public class ContainsDuplicateII {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> numberToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numberToIndex.containsKey(nums[i]) && abs(numberToIndex.get(nums[i]) - i) <= k) { // if map already contains key and index difference is <=k, return true
                return true;
            }
            numberToIndex.put(nums[i], i); // add or update number's index in map
        }
        return false;
    }

    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i< nums.length ; i++) {
            if(set.size() >k) {
                set.remove(nums[i-k-1]);
            }
            if(!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}
