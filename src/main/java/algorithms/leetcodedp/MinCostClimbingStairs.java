package algorithms.leetcodedp;

import java.util.HashMap;
import java.util.Map;

//You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
//
//You can either start from the step with index 0, or the step with index 1.
//
//Return the minimum cost to reach the top of the floor.
//
//
//Example 1:
//
//Input: cost = [10,15,20]
//Output: 15
//Explanation: You will start at index 1.
//- Pay 15 and climb two steps to reach the top.
//The total cost is 15.
//Example 2:
//
//Input: cost = [1,100,1,1,1,100,1,1,100,1]
//[1, 100 , 1 , 1 , 1 , 100 , 1 , 1 , 100 , 1]
// 0   1    2   3   4    5    6   7    8     9
//Output: 6
//Explanation: You will start at index 0.
//- Pay 1 and climb two steps to reach index 2.
//- Pay 1 and climb two steps to reach index 4.
//- Pay 1 and climb two steps to reach index 6.
//- Pay 1 and climb one step to reach index 7.
//- Pay 1 and climb two steps to reach index 9.
//- Pay 1 and climb one step to reach the top.
//The total cost is 6.
public class MinCostClimbingStairs {
    // The problem asks to find minimum. The subproblems are overlapping- so dp?
    public int minCostClimbingStairs(int[] cost) {
        // We have to reach top is cost.length
        // 'cost' to have 2 <= size <= 1000
        // we are paying the cost equal to the step that we stand on, if we choose to step on index i, we are paying cost [i]
        // what is the recurrence relation here?
        // person can take either 1 step or 2 step
        // person can start from either index 0 or 1
        // here probably we start from index 2 directly and assume index 2 is top, What is the minimum cost for reaching index 2?
        int[] dp = new int[cost.length + 1]; // because we have to find for cost.length
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i <= cost.length; i++) {
            int currentStepCost = i < cost.length ? cost[i] : 0;
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + currentStepCost;
        }

        return dp[cost.length];
        //Space complexity: O(N) The array minimumCost is always 1 element longer than the array cost
        // Time complexity: O(N) We iterate N - 1 times, and at each iteration we apply an equation that requires O(1)O(1)O(1) time.
        //  [10,15,20]
        // cost for index 2 = dp[0] = 10, dp[1] = 15, dp[2] = 30, dp[3] =
    }


    // The problem asks to find minimum. The subproblems are overlapping- so dp?
    public int minCostClimbingStairs2(int[] cost) {
        // bottom up solution as above - but here we use a different recurrent relation and remove the ternary operator
        // better solution, each index contains its minimum cost
        // indexes 0 and 1 have cost 0 as we can start from any of these steps without paying
        // cost for index 2 = dp[0] = 0, dp[1] = 0, dp[2] = 10,
        // dp[3] = min(dp[2] + cost[2], dp[1] + cost[1]) = min(10 + 20, 0+ 15) ,
        int[] dp = new int[cost.length + 1]; // because we have to find for cost.length
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= cost.length; i++) {
            int oneStep = dp[i-1] + cost[i-1];
            int twoStep = dp[i-2] + cost[i-2];
            dp[i] = Math.min(oneStep, twoStep);
        }

        return dp[cost.length];
        //Space complexity: O(N) The array minimumCost is always 1 element longer than the array cost
        // Time complexity: O(N) We iterate N - 1 times, and at each iteration we apply an equation that requires O(1)O(1)O(1) time.
    }

    //Bottom-up dynamic programming is named as such because we start from the bottom (in this case, the bottom of the staircase)
    // and iteratively work our way to the top. Top-down dynamic programming starts at the top and works its way down to the base cases.
    // Typically, this is implemented through recursion, and then made efficient using memoization.
    // Memoization refers to storing the results of expensive function calls in order to avoid duplicate computations
    public int minCostClimbingStairs3(int[] cost) {
        // top down
        //259 / 283 testcases passed - TLE
//        return recurse(cost,cost.length);
        return recurseMemoize(cost, cost.length);
    }
    Map<Integer, Integer> map = new HashMap<>();
    public int recurse(int[] cost, int n) {
        // top down
        if(n<=1) {
            return 0; // for first two stairs, there is no cost
        }
        return Math.min(recurse(cost, n-1) + cost[n-1], recurse(cost, n-2) + cost[n-2]);
        // Time complexity: O(2^n) - check house robber
    }

    public int recurseMemoize(int[] cost, int n) {
        // top down
        if(n<=1) {
            return 0; // for first two stairs, there is no cost
        }
        if(map.containsKey(n)) {
            return map.get(n);
        }
        int result =  Math.min(recurse(cost, n-1) + cost[n-1], recurse(cost, n-2) + cost[n-2]);
        map.put(n, result);
        return result;
        // Time complexity : O(n)- we calculate once for each value of n
        // Space complexity: O(n)-The extra space used by this algorithm is the recursion call stack.
        // For example, minimumCost(10000) will call minimumCost(9999), which calls minimumCost(9998) etc.,
        // all the way down until the base cases at minimumCost(0) and minimumCost(1).
        // In addition, our hash map memo will be of size N at the end, since we populate it with every index from 0 to N.
    }

    //Approach 3: Bottom-Up, Constant Space
    //Intuition
    //
    //You may have noticed that our recurrence relation from the previous two approaches only cares about 2 steps below the current step.
    // For example, if we are calculating the minimum cost to reach step 12, we only care about data from step 10 and step 11.
    // While we would have needed to calculate the minimum cost for steps 2-9 as well, at the time of the actual calculation for step 12,
    // we no longer care about any of those steps.
    //
    //Therefore, instead of using O(n) space to keep an array, we can improve to O(1)space using only two variables.
    public int minCostClimbingStairs4(int[] cost) {
        int cost0 = 0; // cost two stairs down
        int cost1 = 0; // the cost one stair down
        int result = 0;
        for(int i=2; i<= cost.length; i++) {
            result = Math.min(cost0 + cost[i-2], cost1 + cost[i-1]);
            cost0 = cost1;
            cost1 = result;
        }
        return result;
        // time complexity: O(n); One for loop
        // space : O(1)
    }



}
