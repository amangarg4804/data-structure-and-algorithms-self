package algorithms.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Given an integer array nums and an integer k,
// return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
public class CountDuplicatesII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Time : O(n*k)
        for(int i=0; i< nums.length ; i++) {
            for(int j=i+1; j<= i+k && j< nums.length ;j++) {
                if(nums[i]==nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Map<Integer, Integer> numberToIndexMap = new HashMap<>();
        for(int i=0; i< nums.length ; i++) {
            if(numberToIndexMap.containsKey(nums[i]) && i- numberToIndexMap.get(nums[i]) <=k ) {
                return true;
            }
            numberToIndexMap.put(nums[i], i);
        }
        return false;
    }

    public boolean containsNearbyDuplicateSlidingWindow(int[] nums, int k) { // 0, 1, 2, 3, 4, 5
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
