package algorithms.leetcodebst;
//Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
//        Output: true
//        Explanation: We can choose (i, j) = (0, 3).
//        We satisfy the three conditions:
//        i != j --> 0 != 3
//        abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
//        abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0

import java.util.TreeSet;

public class ContainsDuplicateIII {


    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        // time complexity : O(n*k)
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + indexDiff && j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) <= valueDiff) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int indexDiff, int valueDiff) {
        // time complexity: O(n log(k))-> ceiling and floor take log k time
        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
//                                  if(!set.isEmpty()) { // not required because floor and ceiling will take care of it
            Integer smaller = set.floor(nums[i]); // NOTE: floor returns smaller element than nums[i] or null in case smaller element doesn't exist
            Integer greater = set.ceiling(nums[i]); // NOTE: ceiling returns greater element than nums[i] or null
            if (smaller != null && Math.abs(smaller - nums[i]) <= valueDiff) { // NOTE: while subtracting two numbers and computing  absolute value of result, the order doesn't matter
                return true;
            }
            if (greater != null && Math.abs(greater - nums[i]) <= valueDiff) { // NOTE: in case of integer overflow we could multiply by 1L -> Math.abs(1L* greater -nums[i])
                return true;
            }
            set.add(nums[i]);
            if(set.size()> indexDiff) {
                set.remove(nums[i-indexDiff]);
            }

        }
        return false;
    }
}
