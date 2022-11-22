package algorithms.leetcode;

// backtracking
//Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
//Input: nums = [1,1,2]
//        Output:
//        [[1,1,2],
//        [1,2,1],
//        [2,1,1]]

import java.util.*;

public class PermutationsII {

    public static void main(String[] args) {
        System.out.println(new PermutationsII().permuteUnique(new int[]{1, 1, 2}));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, current, nums, new boolean[nums.length]);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums, boolean[] visited) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (visited[i] ) {
                continue;
            }
            if( i>0 && nums[i] ==nums[i-1] && !visited[i-1]){
//                With inputs as [1a, 1b, 2a],
//                If we don't handle the duplicates, the results would be: [1a, 1b, 2a], [1b, 1a, 2a]...,
//                so we must make sure 1a goes before 1b to avoid duplicates
//                By using nums[i-1]==nums[i] && !visited[i-1], we can make sure that 1b cannot be choosed before 1a
                continue;
            }
            visited[i] = true;
            current.add(nums[i]);
            backtrack(result, current, nums, visited);
            visited[i] = false;
            current.remove(current.size() - 1);
        }
    }

}
