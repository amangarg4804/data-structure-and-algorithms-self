package algorithms.leetcodehashtable;

import java.util.*;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        // brute force n^2
        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {
                int required = target - nums[i];
                if (nums[j] == required) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && target - nums[i] == nums[i]) {
                // duplicate number can exist in given array but we are given that there is exactly one solution. So, we can check if the existing value in map and current value form target
                return new int[]{map.get(nums[i]), i};
            }
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }

            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public int[] twoSum2(int[] nums, int target) {
        // sort the array, find the numbers in sorted array using two pointers, then find their indexes

        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        int start =0;
        int end = nums.length -1;
        List<Integer> numbers = new ArrayList<>();
        while (start<end) {
            int diff = target-sorted[start];
            if(diff == sorted[end]) {
                numbers.add(sorted[start]);
                numbers.add(sorted[end]);
                break;
            }

            if(diff < sorted[end]) {
                end--;
            } else {
                start++;
            }

        }
        int[] result = new int[] {-1, -1};
        int index = 0;
        for(int i=0; i< nums.length;i++) {
            if(numbers.contains(nums[i])) {
                result[index++] =i;
            }
        }
        return result;
    }

}

