package algorithms.leetcodedp;

import java.util.*;

//You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:
//
//Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete "every" element equal to nums[i] - 1 and every element equal to nums[i] + 1.
//Return the maximum number of points you can earn by applying the above operation some number of times.
//
//
//
//Example 1:
//
//Input: nums = [3,4,2]
//Output: 6
//Explanation: You can perform the following operations:
//- Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
//- Delete 2 to earn 2 points. nums = [].
//You earn a total of 6 points.
//Example 2:
//
//Input: nums = [2,2,3,3,3,4]
//Output: 9
//Explanation: You can perform the following operations:
//- Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
//- Delete a 3 again to earn 3 points. nums = [3].
//- Delete a 3 once more to earn 3 points. nums = [].
//You earn a total of 9 points.
//
//
//Constraints:
//
//1 <= nums.length <= 2 * 104
//1 <= nums[i] <= 104
public class DeleteAndEarn {
    //This problem is an excellent example of why it is important to read the problem description carefully. We can make a substantial optimization by noticing that the order of the elements in nums is irrelevant.
    //
    //At any operation, we pick a number, let's say x, and delete all occurrences of x - 1 and x + 1. That means if x occurs multiple times in nums and we take one, we may as well take all of them because after deleting all x - 1 and x + 1, we can only stand to gain more points by taking additional x.
    //
    //Before we start, we can simplify nums by collecting all duplicate numbers together. As an example, nums can be represented as a hash map with numbers as keys which map to the number of times the key occurs in nums. [2, 2, 3, 3, 3, 4] would be converted to {2: 2, 3: 3, 4: 1}. Furthermore, since we are only really concerned about how many points each number can give us, we can multiply the keys and values to represent how many points taking the key will give us. In this case, we would have {2: 4, 3: 9, 4: 4}.
    //
    //In the first approach of this article, we will talk about an algorithm that solves this problem in detail. In the second approach, we will implement the same algorithm in a slightly more efficient way.
    //
    //Then, we will look at some approaches that build on the idea from the first two approaches, but each one will have differences in their time and space complexities.
    //
    //Approach 1: Top-Down Dynamic Programming
    //Intuition
    //
    //There are two characteristics of this problem that hint towards the use of dynamic programming (DP). The first is that the problem is asking us to find the maximum of something. The second is that we need to make decisions on which numbers to take, and each decision may influence future decisions. For example, if we wanted to take all the fives, then we can no longer take the fours or sixes.
    //
    //As described in the Dynamic Programming Explore Card, these two characteristics suggest that we should consider a DP solution. If you're new to DP, then reading this card is highly recommended.
    //
    //The difficult part about this problem is figuring out how to always make the optimal decisions on which elements to take. Let's look at an example to illustrate the problem: nums = [5, 4, 5, 4, 3, 5, 3]. As we mentioned above, we can convert this into a format that's easier to use. We have two 3, two 4, and three 5. That means:
    //
    //If we take all the 3, we get 3 * 2 = 6 points.
    //If we take all the 4, we get 4 * 2 = 8 points.
    //If we take all the 5, we get 5 * 3 = 15 points.
    //If we tried moving from smaller keys to larger ones in a greedy manner, our first decision would be to take all 3 or all 4. We can only choose one because picking one deletes the other one. Well, we get 8 points if we pick 4 and 6 points if we pick 3, so perhaps we should greedily pick 4. Unfortunately, this prevents us from picking 5, which gives us way more points.
    //
    //Perhaps a different greedy strategy would work. How about sorting the keys by how many points they give, and then greedily trying to pick the most points? That way, we would take the 15 points here immediately. Well, what if we had an example nums = [5, 5, 5, 6, 6, 6, 6, 7, 7]? We would have:
    //
    //5 --> 15 points.
    //6 --> 24 points.
    //7 --> 14 points.
    //Again, this greedy strategy won't work. Those 24 points are tempting, but by taking them, we delete 29 points. In these small examples, it's easy to see what the correct decisions are. But for larger test cases, the question can get out of hand quickly. We need to ensure that we always make the correct decision.
    //
    //This is where DP comes in. We'll skip over the underlying details of DP, so the article stays focused on the problem - but if DP is relatively new to you, then you will likely find the DP explore card to be a very helpful resource.
    //
    //We can formulate DP algorithms in 3 easy steps. First, we need some sort of memory that stores the answer to our question. Because we're doing top-down, we'll use a hash table for memory and a recursive function. Let's declare a function maxPoints. We want maxPoints(num) to return the maximum points that we can gain if we only consider all the elements in nums with values between 0 and num.
    //
    //The second thing we need is a recurrence relation, a way to move between states. Let's say that we are currently at some arbitrary number x, where x is in nums one or more times. How can we find maxPoints(x)? When it comes to x, we have to make a choice: take, or don't take.
    //
    //If we take x, then we gain points equal to x times the number of times x occurs in nums - we can pre-compute these values. For now, let's call this value gain. However, because of the deletion, by taking x, we are no longer allowed to take x - 1. The largest number that we can still consider is x - 2. Therefore, if we choose to take x, then the most points that we can have here is gain + maxPoints(x - 2), where gain is how many points we gain from taking x and maxPoints(x - 2) is the maximum number of points we can obtain from the numbers between x - 2 and 0.
    //
    //If we choose not to take x, then we don't gain any points here, but we still may have accumulated some points from numbers smaller than x. Because we didn't take x, we did not close the door to x - 1. In this case, the most points we can have here is maxPoints(x - 1).
    //
    //This forms our recurrence relation: for an arbitrary x, maxPoints(x) = max(maxPoints(x - 1), maxPoints(x - 2) + gain), where gain is the number of points we can gain from taking x.
    //
    //The problem is, even though we figured out how to find maxPoints(x), how do we find maxPoints(x - 1) and maxPoints(x - 2)? That would involve finding maxPoints(x - 3) and maxPoints(x - 4) and so on.
    //
    //The third component of a dynamic programming solution is base cases. Typically, we can find base cases with a little bit of logical thinking. First, maxPoints(0) will always be equal to 0. Second, when considering maxPoints(1), we only care about the elements 0 and 1. We do not care about 2 because of how we defined maxPoints(x). Looking at the recurrence relation, we know that if we arrived at 1, it means that we must not have taken 2, and because 1 times any quantity will be greater than or equal to the number of points we can get from taking 0, we should always take 1 (if there are any).
    //
    //With these base cases, we can find maxPoints(2). With maxPoints(2) calculated, we can find maxPoints(3), all the way up to maxPoints(max(nums)). Remember, we defined maxPoints(x) as the maximum points we can gain when we consider the numbers from 0 to x, so maxPoints(max(nums)) covers the entire input, and stores the answer to the original problem.
    //
    //Because each call to maxPoints will create 2 extra calls (the recurrence relation looks for maxPoints(num - 1) and maxPoints(num - 2)), we would end up with an exponential amount of function calls. To avoid this, we will memoize our function. When we find the answer for a certain number for the first time, we will store this answer. Then in the future, we can refer to this value instead of repeating computation.
    Map<Integer, Integer> earningsMap = new HashMap<>();
    public int deleteAndEarn(int[] nums) {
        int max = Integer.MIN_VALUE; // we will call dp function on maximum number of array.
        // Because our function returns maximum points that we can gain if we only consider all the elements in nums with values between 0 and num.

        for(int i : nums){
            earningsMap.put(i, earningsMap.getOrDefault(i, 0) + i);
            max = Math.max(max, i);

        }
        return dp(max);
    }
    Map<Integer,Integer> memoizeMap = new HashMap<>();// here we can't initialize map with values of 0 and 1 because it is possible that 1 doesn't exist in array or exists multiple times

    private int dp(int n) {
        if(n == 0) {
            return 0;
        }
        if(n==1) {
            return earningsMap.getOrDefault(n, 0);
        }
        if(!memoizeMap.containsKey(n)) {
            // Two choices- either take n or don't take n.
            // If we take n, then we can take n-2. so earnings will be earningsMap.get(n) + dp(n-2)
            int result = Math.max(dp(n - 1), dp(n - 2) + earningsMap.getOrDefault(n, 0));
            memoizeMap.put(n, result);
            return result; // we don't consider n+1 here because if dp(n) is called, we calculate only for 0 to n
        }
        return memoizeMap.get(n);
        // time: first for loop iterates the input array. O(n),calculation once for each value from 0 to max: O(max): O(n) + O(max)
        // space :
        // 1. recursion stack O(max)- method call for each number from max to 0 once
        // 2, earningsMap -> O(n)
    }

    public int deleteAndEarn2(int[] nums) {
        // bottom up
        // if i is maximum number, we can either take it or not take it, then the earning is max(earning(i-1), earning(i) + earning(i-2))
        int max = Integer.MIN_VALUE;
        for(int i: nums) {
            earningsMap.put(i, earningsMap.getOrDefault(i, 0) +i);
            max = Math.max(i, max);
        } //O(n)

        // We have to check for all numbers from 1 to max number. So if max number is Integer.MAX_VALUE, time complexity will be O(maxValue)
        int[] dp = new int[max +1];
        dp[0] = 0;
        dp[1] = earningsMap.getOrDefault(1, 0);
        for(int i= 2; i<= max; i++) {
            // if i doesn't exist in array, earnings are zero for i
            dp[i] = Math.max(dp[i-1],
                    dp[i-2] + earningsMap.getOrDefault(i, 0));
        }
        return dp[dp.length -1];

        // time complexity- dependent on maximum number in array and the number of elements in array . O(n) + O(max).
        // first for loop iterates the input array. Second for loop iterates from 2 to max
        // space complexity-
        // 1. earningsMap -> O(n)
        // 2. dp array: maximum number in array. O(max)
    }

    //In our recurrence relation, for a given num, we refer to num - 1 and num - 2. To find maxPoints[10], we only need maxPoints[9] and maxPoints[8]. While it is true that we also need to calculate maxPoints for 0 to 7, by the time we get to 10, we don't need those values anymore. Because of this, instead of using an array to store the answers to all states, we can improve to constant space by only storing the previous two values.
    //
    //Let's use two integer variables, twoBack and oneBack, that at any given num will represent maxPoints[num - 2] and maxPoints[num - 1] respectively. In the second approach, we started iterating from 2. This means that twoBack should be initialized the same as maxPoints[0] = 0, and oneBack should be initialized the same as maxPoints[1] = nums.count(1).
    //
    //Our recurrence relation remains the same, as long as we appropriately substitute the variables. Now, it is maxPoints[num] = max(oneBack, twoBack + points[num]). Once we calculate this value, we need to update twoBack and oneBack. When we go to the next number, the previous value (oneBack) will become twoBack, and the current value will become oneBack. At the very end, oneBack will be our answer.

    public int deleteAndEarn3(int[] nums) {
        // bottom up - space complexity optmized
        // without dp array, we only require last two values
        // if i is maximum number, we can either take it or not take it, then the earning is max(earning(i-1), earning(i) + earning(i-2))
        int max = Integer.MIN_VALUE;
        for(int i: nums) {
            earningsMap.put(i, earningsMap.getOrDefault(i, 0) +i);
            max = Math.max(i, max);
        } //O(n)

        // We have to check for all numbers from 1 to max number. So if max number is Integer.MAX_VALUE, time complexity will be O(maxValue)
        int maxMinus2 = 0;
        int maxMinus1 = earningsMap.getOrDefault(1, 0);
        int result = maxMinus1;
        for(int i= 2; i<= max; i++) {
            // if i doesn't exist in array, earnings are zero for i
             result = Math.max(maxMinus1,
                    maxMinus2+ earningsMap.getOrDefault(i, 0));
             maxMinus2  = maxMinus1;
             maxMinus1 = result;
        }
        return result;

        // time complexity- dependent on maximum number in array and the number of elements in array . O(n) + O(max).
        // first for loop iterates the input array. Second for loop iterates from 2 to max
        // space complexity-
        // 1. earningsMap -> O(n)
    }


    public int deleteAndEarn4(int[] nums) {
        // by sorting elements
        int max = Integer.MIN_VALUE;
        for(int i: nums) {
            earningsMap.put(i, earningsMap.getOrDefault(i, 0) +i);
            max = Math.max(i, max);
        } //O(n)
        List<Integer> entries =new ArrayList<>(earningsMap.keySet());
        Collections.sort(entries);
        // We have to check for all numbers from 1 to max number. So if max number is Integer.MAX_VALUE, time complexity will be O(maxValue)
        int maxMinus2 = 0;
        int maxMinus1 = earningsMap.get(entries.get(0));
        // instead of iterating from  0 to max,we iterate actual unique elements in sorted order
        int result = maxMinus1;
        for(int i= 1; i< entries.size(); i++) {
            // if value of previous element is list is 1 less than current value, then we can't take both previous and current element
            // use recurrence relation
            if(entries.get(i-1) == entries.get(i) -1) {
                result = Math.max(maxMinus2 + earningsMap.get(entries.get(i)), maxMinus1);
            } else {
                // if the difference is more than one, we can take both elements
                result = maxMinus1 + earningsMap.get(entries.get(i)); // can introduce variable for entries.get(i)
            }
            maxMinus2 = maxMinus1;
            maxMinus1 = result;

        }
        return result;
        //Time complexity: O(N⋅log(N))
        //Space complexity: O(N)
        // The extra space we use is the hash map earningsMap and entries list.
        // These have the same length, and in the worst case scenario when nums only contains unique elements, their lengths will be equal to N.

    }

}
