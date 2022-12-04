package algorithms.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i< nums.length ; i++) {
            if(!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
    public boolean containsDuplicate1(int[] nums) {
        return  Arrays.stream(nums).distinct().count() != nums.length;
    }

    public boolean containsDuplicateLambda(int[] nums) {
        Set<Integer> set = new HashSet<>();
        return  Arrays.stream(nums).anyMatch(num -> !set.add(num));
    }
}
