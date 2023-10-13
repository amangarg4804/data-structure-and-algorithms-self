package algorithms.leetcodedp;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/
//You are given two 0-indexed integer arrays nums and multipliers of size n and m respectively, where n >= m.
//
//You begin with a score of 0. You want to perform exactly m operations. On the ith operation (0-indexed) you will:
//
//Choose one integer x from either the start or the end of the array nums.
//Add multipliers[i] * x to your score.
//Note that multipliers[0] corresponds to the first operation, multipliers[1] to the second operation, and so on.
//Remove x from nums.
//Return the maximum score after performing m operations.
//
//
//
//Example 1:
//
//Input: nums = [1,2,3], multipliers = [3,2,1]
//Output: 14
//Explanation: An optimal solution is as follows:
//- Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
//- Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
//- Choose from the end, [1], adding 1 * 1 = 1 to the score.
//The total score is 9 + 4 + 1 = 14.
//Example 2:
//
//Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
//Output: 102
//Explanation: An optimal solution is as follows:
//- Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
//- Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
//- Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
//- Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
//- Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
//The total score is 50 + 15 - 9 + 4 + 42 = 102.
//
//
//Constraints:
//
//n == nums.length
//m == multipliers.length
//1 <= m <= 300
//m <= n <= 105
//-1000 <= nums[i], multipliers[i] <= 1000
public class MaximumScoreMultiplications {

    public int maximumScore(int[] nums, int[] multipliers) {
//        return  maximumScore(nums, multipliers, 0, nums.length -1, 0);
        Integer[][] dp = new Integer[multipliers.length][multipliers.length];
        // 1. don't initialize the memo array with -1 because -1 could be an answer,
        // and we will never be able to distinguish between the case when the state is not computed, and the case when the state is computed and the answer is -1.
        // That's why we use Integer wrapper class and not primitive int
        // 2. Why do we use multipliers.length as length of left: because left can never go beyond multipliers.length
        return  maximumScoreMemoize(dp, nums, multipliers, 0,  0);
    }


    private int maximumScore(int[] nums, int[] multipliers, int start, int end, int multiplierIndex) {
        // 1.Base case: when this is last multiplier index
        // 2. recurrence relation
        // this is top down approach, no need to have a variable 'answer' as input to this function
        //
        // 2 / 59 testcases passed TLE
        int multiplier = multipliers[multiplierIndex];
        int startResult = multiplier* nums[start] ;
        int endResult = multiplier * nums[end];
        if(multiplierIndex == multipliers.length -1) {
            return Math.max(startResult, endResult); // base case doesn't require previous results, think of dfs
        }
        // it is given that multiplier's array length will always be less than or equal to nums length,
        // so no need to worry about array index out of bound for nums array

        //Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
        //Output: 102
        //Explanation: An optimal solution is as follows:
        //- Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score. Step 1
        //- Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score. Step 2
        //- Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score. Step 3
        //- Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score. Step 4
        //- Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.  Step 5
        //The total score is 50 + 15 - 9 + 4 + 42 = 102.

        // notice that at step 3, we chose -3 instead of 1 , even though multiplication of -3 results in a smaller value.
        // This is because by following this step, at step 5 , we get to multiply 7 by 6 to get a greater value
        // so we have to check all paths
       return Math.max( startResult + maximumScore(nums, multipliers, start+1, end, multiplierIndex+1),
               endResult + maximumScore(nums, multipliers, start, end-1, multiplierIndex+1));
        // time: exponential, for each input, we make 2 more calls: 2^n
    }
    //The logical intuition of why it is not optimal can be deduced from the following two cases:
    //
    //Greedy is short-sighted. For the global optimum, we pick the local optimum. But picking this Local Optimum may restrict greater positive product afterward.
    //
    //nums = [-10,1,1000,1,1,100], multipliers = [1,1,1]
    //If we pick 100 over -10, we would never ever be able to collect 1000. There are only three elements in multipliers, and we can collect 1000 by taking the left integers only. But selecting 100 at the first point restricts it.
    //
    //Moreover, what if both ends of nums are identical? We don't know which one to favor. One may yield another score, another may yield a very different score.
    //
    //nums = [2, 1000, -1, 2], multipliers = [1, 1]
    //if we select Left 2 first, then at the next step, there would be a contest between Left 1000 and Right 2. As per the approach we now would select left 1000, obtaining 1002 as the answer.
    //if we select Right 2 first, then at the next step, there would be a contest between Left 2 and Right -1. As per the approach we now would select left 2, obtaining 4 as the answer.
    //Thus, these examples suggest that we have to look for all two possible combinations at each step:

    private int maximumScoreMemoize(Integer[][] dp, int[] nums, int[] multipliers, int left, int multiplierIndex) { // NOTE: we can't use primitive int[][] dp- compilation error

        //Select the best from all possible states and instead of computing again and again, save what you have already computed. Memoizing the pre-visited states while trying all the possible scenarios will reduce the complexity.
        //To determine a state, we essentially need 3 things
        //left: specify we have used left integers from the left side of nums so far. Next, we may use nums[left]
        //right: specify we have used right integers from the right side of nums so far. Next, we may use nums[right]
        //op: number of operations done.
        //Hence, there are 3 state variables, left, right, and op. Thus, it's a 3D Dynamic Programming problem. And to memoize it, we may need a 3D array.
        //If there are n state variables, then we need an array of at most n dimensions.
        //However, with mathematics, we can reduce these 3 state variables to 2. Can you think of how to do that?
        //(Note that len(nums) will be constant in this approach, since we are not modifying nums and passing indices instead)
        //From this state, we have two options
        //
        //Select Left: Number of operations will advance by one. And, so does the left pointer. Thus, we will multiply mulitpliers[op] and nums[left]
        // (since we have selected from left), and add this product to (optimal) result of state dp[op+1][left+1].
        //
        //Select Right: Then also the number of operations will advance by one. Then, we will multiply mulitpliers[op] with nums[n-1-(op-left)]
        // (since we have selected from right), and add this product to (optimal) result of state dp[op+1][left]
        // (Now, left will not increment since number has not been chosen from left).

        // 1.Base case: when this is last multiplier index
        // 2. recurrence relation
        // this is top down approach, no need to have a variable 'answer' as input to this function
        //
        // 2 / 59 testcases passed TLE
        if(multiplierIndex == multipliers.length) {
            return 0;
        }
        if(dp[multiplierIndex][left] != null) {
            // return the memoized result
            return dp[multiplierIndex][left];
        }
        int right = nums.length - 1 - (multiplierIndex -left);
        int multiplier = multipliers[multiplierIndex];
        int leftResult = multiplier* nums[left] +  maximumScoreMemoize(dp, nums, multipliers, left+1, multiplierIndex+1);//we increase left
        int rightResult = multiplier * nums[right] + maximumScoreMemoize(dp, nums, multipliers, left, multiplierIndex+1);// we don't increase left but only increase multiplierIndex, in this recursion call, "right" will become +1
        // it is given that multiplier's array length will always be less than or equal to nums length,
        // so no need to worry about array index out of bound for nums array
        // 0, 1, 2, 3, 4
        // length = 5
        // multiplierIndex = 2
        // leftIndex =  2
        // right = 4 (how? ) -> 5 - 1 -(2-2) = 5-1-0 = 4

        dp[multiplierIndex][left] = Math.max(leftResult, rightResult);
        return dp[multiplierIndex][left];
        //Let M be the size of multipliers, the same as the number of operations.
        //
        //Time complexity: O(M^2)
        //
        //op can vary from 0 to M-1. Now, in two recursive calls that we are making, one time we are incrementing left, along with op. Other time, we are not incrementing left, but incrementing op. So, left is at most op. Thus, left also varies from 0 to M-1. So, there are O(M^2)
        //such pairs for computing.
        //
        //Space complexity: O(M^2), the memo will store at most M^2 such pairs!
    }

    public int maximumScore3(int[] nums, int[] multipliers) {
        // bottom up
        //if we look at the recursive solution above, the recursion will follow dfs and at the deepest level , multiplier value is m , then m-1, m-2
        // meaning the value m-2 is derived from m-1, m-3 is derived from m-2 and so on
        int m = multipliers.length;
        int[][] dp = new int[m+1][m+1];// should use primitive here to avoid NPE
        dp[m][m] =0; // no need, already 0

        for(int multiplierIndex = m-1; multiplierIndex>=0; multiplierIndex--) {
            for(int left= multiplierIndex; left >=0; left--) {
                int leftAnswer = multipliers[multiplierIndex] * nums[left] + dp[multiplierIndex+1][left+1];
                int right = nums.length -1- (multiplierIndex-left);
                int rightAnswer = multipliers[multiplierIndex]* nums[right] + dp[multiplierIndex+1][left];
                dp[multiplierIndex][left] = Math.max(leftAnswer, rightAnswer);
                //  dp[multiplierIndex][left] is storing the result for current values of multiplierIndex and left
                // Why don't we require dp[multiplierIndex][right] ? Because we calculate right based on multiplier index and left
                // because in case of rightAnswer calculation, we simply use the left value dp[multiplierIndex+1][left]
            }
        }
        return dp[0][0];
        //Time complexity: O(M^2)
        //op varies from M-1 to 0, and left varies from op to 0. This is equivalent to iterating half matrix of order M×M  . So, we are computing O(M^2/2) states
        //Space complexity: O(M^2)as evident from the dp array.
    }

    public int maximumScore4(int[] nums, int[] multipliers) {
        // bottom up
        //if we look at the recursive solution above, the recursion will follow dfs and at the deepest level , multiplier value is m , then m-1, m-2
        // meaning the value m-2 is derived from m-1, m-3 is derived from m-2 and so on
        int m = multipliers.length;
        int[][] dp = new int[m+1][m+1];// should use primitive here to avoid NPE
        dp[m][m] =0; // no need, already 0

        for(int multiplierIndex = m-1; multiplierIndex>=0; multiplierIndex--) {
            for(int left= multiplierIndex; left >=0; left--) {
                int leftAnswer = multipliers[multiplierIndex] * nums[left] + dp[multiplierIndex+1][left+1];
                int right = nums.length -1- (multiplierIndex-left);
                int rightAnswer = multipliers[multiplierIndex]* nums[right] + dp[multiplierIndex+1][left];
                dp[multiplierIndex][left] = Math.max(leftAnswer, rightAnswer);
                //  dp[multiplierIndex][left] is storing the result for current values of multiplierIndex and left
                // Why don't we require dp[multiplierIndex][right] ? Because we calculate right based on multiplier index and left
                // because in case of rightAnswer calculation, we simply use the left value dp[multiplierIndex+1][left]
            }
        }
        return dp[0][0];

    }

}
