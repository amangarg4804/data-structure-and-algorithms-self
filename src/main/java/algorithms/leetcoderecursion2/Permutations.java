package algorithms.leetcoderecursion2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


//Given an array nums of "distinct" integers, return all the possible permutations. You can return the answer in any order.
//
//        Example 1:
//        Input: nums = [1,2,3]
//        Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();
        backtrack(nums, new HashSet<Integer>(), result, currentList);
        return result;
    }

    private void backtrack(int[] nums, HashSet<Integer> set, List<List<Integer>> result, List<Integer> currentList) {
        if(currentList.size() == nums.length) {
            result.add(new ArrayList<>(currentList));
        }
        for(int i =0; i<nums.length;i++) {
            if(!set.contains(nums[i])) { // we could skip using set and use currentList.contains (would increase time complexity) or we could also use a boolean array instead of set
                currentList.add(nums[i]);
                set.add(nums[i]);
                backtrack(nums, set, result, currentList);
                currentList.remove(currentList.size()-1);
                set.remove(nums[i]);
            }
        }
    }

    public List<List<Integer>> permuteIterative(int[] nums) {
        //assuming nums.length >0
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(nums[0]);
        result.add(list1);


        for(int i=1; i< nums.length;i++) {
            List<List<Integer>> newResultList = new ArrayList<>();
            for(List<Integer> existing : result) {
                for(int j=0; j<=existing.size(); j++) {
                    List<Integer> newCandidate = new ArrayList<>(existing);
                    newCandidate.add(j, nums[i]); //NOTE: arrayList add method can be called for index =list.size() but not more than that.
                    // If list already contains 3 elements, list.add(3, 20) will add the element
                    // but list(4, 20) will throw IndexOutOfBound exception
                    newResultList.add(newCandidate);
                }
            }
            result = newResultList;
        }
        return result;
    }

}
