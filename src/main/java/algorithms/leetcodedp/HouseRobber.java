package algorithms.leetcodedp;

import java.util.HashMap;
import java.util.Map;

//You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
//Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
//
//Example 1:
//
//Input: nums = [1,2,3,1]
//Output: 4
//Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//Total amount you can rob = 1 + 3 = 4.
public class HouseRobber {
    Map<Integer, Integer> map = new HashMap<>();
    public int rob(int[] nums) {
//        return rob(nums, nums.length -1);
        return robMemoize(nums, nums.length -1);
    }


    private int rob(int nums[], int n) {
        //TLE: 55 / 70 testcases passed
        if(n==0) {
            return nums[0];
        }
        // For n =1, either the robber can rob house 0 or house 1.
        if(n==1) {
            return Math.max(nums[0], nums[1]);
        }
        // n=2, either the robber can rob house 0 and 2, or he can rob house
        return Math.max(rob(nums, n-2) + nums[n], rob(nums, n-1));
        // time complexity: At each level, the answer requires 2 more calls. So time complexity is exponential. 2^n
    }

    private int robMemoize(int nums[], int n) {
        // top down dp
        // beats 100%
        if(n==0) {
            return nums[0];
        }
        // For n =1, either the robber can rob house 0 or house 1.
        if(n==1) {
            return Math.max(nums[0], nums[1]);
        }
        // n=2, either the robber can rob house 0 and 2, or he can rob house
        if(map.containsKey(n)) {
            return map.get(n);
        }
        int result = Math.max(robMemoize(nums, n-2) + nums[n], robMemoize(nums, n-1));
        map.put(n, result);
        return result;
        //Time Complexity: O(N) since we process at most N recursive calls, thanks to caching, and during each of these calls,
        // we make an O(1)computation which is simply making two other recursive calls, finding their maximum, and populating the cache based on that.
        //
        //Space Complexity: O(N) which is occupied by the cache and also by the recursion stack.
    }

    public int robIterative(int[] nums) {
        // bottom up dp- beats 100%
        if(nums.length==1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i=2; i< nums.length; i++) {
            dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        }
        return dp[nums.length -1];
    }

}
