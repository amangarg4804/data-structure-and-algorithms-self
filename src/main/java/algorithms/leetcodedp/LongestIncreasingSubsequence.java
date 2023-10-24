package algorithms.leetcodedp;

//Given an integer array nums, return the length of the longest strictly increasing
//subsequence

//Example 1:
//Input: nums = [10,9,2,5,3,7,101,18]
//Output: 4
//Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

//Example 2:
//Input: nums = [0,1,0,3,2,3]
//Output: 4

import java.util.*;

//Example 3:
//Input: nums = [7,7,7,7,7,7,7]
//Output: 1
//
//Constraints:
//1 <= nums.length <= 2500
//-104 <= nums[i] <= 104
//
//Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if(nums.length ==0) {
            return 0;
        }
        // what is the longest subsequence starting a index 0?
        // we are sure that the next element of longest subsequence starting at index i is the next greater element in the array with index more than i
        // we can add 1 each time we find a greater number and use recursion
        // 1. recursive relation - (1+ recurse(nextIndex))
        // we have to use "iteration in recursion" because any of the index can be starting point of longest increasing subsequence
        // 2. Base case is when we reach last index, element at last index don't have further elements to form a subsequence of more than 1 integer
        // looks like we can make a small optimization that if we reach index i and the current longest subsequence is longer than the remaining elements,
        // we don't have to process i or indexes >i, they are all going to form a smaller subsequence
//        return recurse(nums, 0, Integer.MIN_VALUE);
        // dp top down, how many dimensions- two dimenstions
        // index itself is a dimension
        // previous is also a dimension because it is possible that we reach an index with different previous each time
        // possible values of previous -104 <= nums[i] <= 104
        // size could be 104+104 + 1(for 0) = 209
        // -104 points to index 0
        // 104 points to index 208
        // given a number, it's index is number + 104

//        Arrays.fill(dp, -1);
//        return dpTopDown(nums, 0, Integer.MIN_VALUE,dp);
        // we require previous index, in case of 0th index, previous is -1. We can't have an index -1 in an array. So we use 0 for -1,1 for 0 and n+1 for n
        int[][]dp = new int[nums.length][nums.length+1];;

        return dpTopDown2(nums, 0, -1,dp);
    }


    private int recurse(int[] nums, int i, int previous) {
        //TLE: 22 / 55 testcases passed
        if(i == nums.length) {
            return 0;
        }
        // 2 , 100, 5, 7
        // We can't simply choose the next greater element
        // In the above example, if we choose 100 for 2, then we can form a subsequence of only 2 integers
        // we have to try for all integers greater than 2
        //[4,10,4,3,8,9]

        int longest = 0;
        // outer loop is to try all indexes as we don't know which index to start with that can result in longest subsequence
        for(int j =i; j< nums.length;j++) {
            // after we have chosen all starting points, the inner loop is to call recursion only of integers greater than currently chosen starting position (j)
            if(nums[j] <= previous) {
                continue;
            }
            int longestStartingAti= 1;
            for(int k =j+1; k< nums.length;k++) {
                int longestWithk = 0;
                if(nums[k] > nums[j]) {
                    // we found a greater element, add 1 and recurse
                    longestWithk = 2 + recurse(nums, k+1, nums[k]);
                }
                longestStartingAti = Math.max(longestWithk, longestStartingAti);
            }
            longest = Math.max(longest, longestStartingAti);
        }
        return longest;
    }

    private int dpTopDown(int[] nums, int i, int previous, int[] dp) {
        //TLE: 22 / 55 testcases passed
        if(i == nums.length) {
            return 0;
        }
        // 2 , 100, 5, 7
        // We can't simply choose the next greater element
        // In the above example, if we choose 100 for 2, then we can form a subsequence of only 2 integers
        // we have to try for all integers greater than 2
        //[4,10,4,3,8,9]
        if(dp[i]!= -1) {
            return dp[i];
        }
        int longest = 0;
        // outer loop is to try all indexes as we don't know which index to start with that can result in longest subsequence
        for(int j =i; j< nums.length;j++) {
            // after we have chosen all starting points, the inner loop is to call recursion only of integers greater than currently chosen starting position (j)
            if(nums[j] <= previous) {
                continue;
            }
            int longestStartingAti= 1;
            for(int k =j+1; k< nums.length;k++) {
                int longestWithk = 0;
                if(nums[k] > nums[j]) {
                    // we found a greater element, add 1 and recurse
                    longestWithk = 2 + dpTopDown(nums, k+1, nums[k], dp);
                }
                longestStartingAti = Math.max(longestWithk, longestStartingAti);
            }
            longest = Math.max(longest, longestStartingAti);
        }
        dp[i] = longest;
        return longest;
    }

    private int dpTopDown2(int[] nums, int i, int previous, int[][]dp) {
        //Input: nums = [10,9,2,5,3,7,101,18]
        // no for loops
        // the approach is to either take the integer or don't take
        // the result will be maximum of these
        // in case of 10, we either take 10 or don't take. 10 has to be greater than previous. For index 0, previous will be -1

        //Why do we use a 2-dimensional array? Because  it is possible that recursive calls for index i have different values of previous
        if(i== nums.length) {
            return 0;
        }
        if(dp[i][previous+1] !=0) {
            return dp[i][previous+1];
        }
        int notTake = dpTopDown2(nums, i+1, previous, dp); // if we don't take previous remains same
        int take = 0;
        if(previous==-1 || nums[previous] < nums[i]) {
            take = 1 + dpTopDown2(nums, i+1, i, dp);
        }
        int max = Math.max(take, notTake);
        dp[i][previous +1] =max;
        return max;
    }

    //Approach 1: Dynamic Programming
    //Realizing a Dynamic Programming Problem
    //This problem has two important attributes that let us know it should be solved by dynamic programming. First, the question is asking for the maximum or minimum of something. Second, we have to make decisions that may depend on previously made decisions, which is very typical of a problem involving subsequences.
    //As we go through the input, each "decision" we must make is simple: is it worth it to consider this number? If we use a number, it may contribute towards an increasing subsequence, but it may also eliminate larger elements that came before it. For example, let's say we have nums = [5, 6, 7, 8, 1, 2, 3]. It isn't worth using the 1, 2, or 3, since using any of them would eliminate 5, 6, 7, and 8, which form the longest increasing subsequence. We can use dynamic programming to determine whether an element is worth using or not.
    //A Framework to Solve Dynamic Programming Problems
    //Typically, dynamic programming problems can be solved with three main components. If you're new to dynamic programming,
    // this might be hard to understand but is extremely valuable to learn since most dynamic programming problems can be solved this way.
    //
    //First, we need some function or array that represents the answer to the problem from a given state. For many solutions on LeetCode,
    // you will see this function/array named "dp". For this problem, let's say that we have an array dp. As just stated,
    // this array needs to represent the answer to the problem for a given state, so let's say that dp[i] represents the length of the longest increasing subsequence
    // that ends with the ith element. The "state" is one-dimensional since it can be represented with only one variable - the index i.
    //Second, we need a way to transition between states, such as dp[5] and dp[7]. This is called a recurrence relation and can sometimes be tricky to figure out.
    // Let's say we know dp[0], dp[1], and dp[2]. How can we find dp[3] given this information? Well, since dp[2] represents the length of the longest increasing subsequence
    // that ends with nums[2], if nums[3] > nums[2], then we can simply take the subsequence ending at i = 2 and append nums[3] to it, increasing the length by 1.
    // The same can be said for nums[0] and nums[1] if nums[3] is larger. Of course, we should try to maximize dp[3], so we need to check all 3. Formally, the recurrence relation is: dp[i] = max(dp[j] + 1) for all j where nums[j] < nums[i] and j < i.
    //The third component is the simplest: we need a base case. For this problem, we can initialize every element of dp to 1,
    // since every element on its own is technically an increasing subsequence.

    public int lengthOfLIS2(int[] nums) {
        //dp bottom up
        // 2 , 100, 5, 7
        // 0     1  2  3
        // we can check the longest increasing subsequence ending at an index i
        // longest increasing subsequence ending index 0 is 1
        // longest increasing subsequence ending at index 1 is dependent on index 0, if integer at index 0 is less than integer at index 1, then we can add 1
        // length of subsequence ending at index i is dependent on all indexes 0 to i-1 with values less than nums[i]
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); // all values are subsequences of 1 by themselves
        for(int i =1; i< dp.length; i++) { // subsequence length ending at 0 is always 1
            for( int j=0; j< i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1); // Note that we are setting dp[i] and also using dp[i] itself as one of the possible values. it is possible that j is less than i but we still don't want to use it because a previous j set the value of dp[i] greater than the value we get by using current j
                }
            }
        }
        // in this case, we can't simply return dp[nums.length-1]
        // we have to return the maximum
        int max = 1;
        for(int i: dp) {
            max = Math.max(max, i);
        }
        return max;
    }

    public int lengthOfLIS3(int[] nums) {
        //dp bottom up along with printing the lis
        // 2 , 100, 5, 7
        // 0     1  2  3
        // we can check the longest increasing subsequence ending at an index i
        // longest increasing subsequence ending index 0 is 1
        // longest increasing subsequence ending at index 1 is dependent on index 0, if integer at index 0 is less than integer at index 1, then we can add 1
        // length of subsequence ending at index i is dependent on all indexes 0 to i-1 with values less than nums[i]
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); // all values are subsequences of 1 by themselves
        // create another array to store the previous index for each index at subsequence. We will then use this array to print the subsequence
        int[] chosenPrevious = new int[nums.length];
        // initialize each value with index itself
        int lastIndex = 0;
        int max = 0;
        for(int i =1; i< dp.length; i++) { // subsequence length ending at 0 is always 1
            chosenPrevious[i] = i;
            for( int j=0; j< i; j++) {
                if(nums[j] < nums[i] && dp[i] < dp[j] +1) {
                    dp[i] =dp[j]+1; // Note that we are setting dp[i] and also using dp[i] itself as one of the possible values. it is possible that j is less than i but we still don't want to use it because a previous j set the value of dp[i] greater than the value we get by using current j
                    chosenPrevious[i] = j;
                }
            }
            if(dp[i] > max) {
                max = dp[i];
                lastIndex =i; //last index is the last index of longest increasing subsequence. This will be used to backtrack every index belonging to the subsequence
            }

        }
        List<Integer> subsequence = new ArrayList<>();
        // 0 1 2 3
        // 0 1 2 1
        subsequence.add(nums[lastIndex]);
        while (chosenPrevious[lastIndex] != lastIndex) {
            lastIndex = chosenPrevious[lastIndex];
            subsequence.add(nums[lastIndex]);
        }
        Collections.reverse(subsequence);
        return max;
    }
    //Approach 2: Intelligently Build a Subsequence
    //Intuition
    //
    //As stated in the previous approach, the difficult part of this problem is deciding if an element is worth using or not. Consider the example nums = [8, 1, 6, 2, 3, 10]. Let's try to build an increasing subsequence starting with an empty one: sub = [].
    //At the first element 8, we might as well take it since it's better than nothing, so sub = [8].
    //At the second element 1, we can't increase the length of the subsequence since 8 >= 1, so we have to choose only one element to keep. Well, this is an easy decision, let's take the 1 since there may be elements later on that are greater than 1 but less than 8, now we have sub = [1].
    //At the third element 6, we can build on our subsequence since 6 > 1, now sub = [1, 6].
    //At the fourth element 2, we can't build on our subsequence since 6 >= 2, but can we improve on it for the future? Well, similar to the decision we made at the second element, if we replace the 6 with 2, we will open the door to using elements that are greater than 2 but less than 6 in the future, so sub = [1, 2].
    //At the fifth element 3, we can build on our subsequence since 3 > 2. Notice that this was only possible because of the swap we made in the previous step, so sub = [1, 2, 3].
    //At the last element 10, we can build on our subsequence since 10 > 3, giving a final subsequence sub = [1, 2, 3, 10]. The length of sub is our answer.
    //It appears the best way to build an increasing subsequence is: for each element num, if num is greater than the largest element in our subsequence, then add it to the subsequence. Otherwise, perform a linear scan through the subsequence starting from the smallest element and replace the first element that is greater than or equal to num with num. This opens the door for elements that are greater than num but less than the element replaced to be included in the sequence.
    //One thing to add: this algorithm does not always generate a valid subsequence of the input, but the length of the subsequence will always equal the length of the longest increasing subsequence. For example, with the input [3, 4, 5, 1], at the end we will have sub = [1, 4, 5], which isn't a subsequence, but the length is still correct. The length remains correct because the length only changes when a new element is larger than any element in the subsequence. In that case, the element is appended to the subsequence instead of replacing an existing element.
    //Approach 3: Improve With Binary Search
    //Intuition
    //
    //In the previous approach, when we have an element num that is not greater than all the elements in sub,
    // we perform a linear scan to find the first element in sub that is greater than or equal to num.
    // Since sub is in sorted order, we can use binary search instead to greatly improve the efficiency of our algorithm.
    public int lengthOfLIS4(int[] nums) {
        // binary search
        //Returns:
        //the index of the search key, if it is contained in the list; otherwise, (-(insertion point) - 1).
        // The insertion point is defined as the point at which the key would be inserted into the list: the index of the first element greater than the key,
        // or list.size() if all elements in the list are less than the specified key.
        // Note that this guarantees that the return value will be >= 0 if and only if the key is found.
        // 2 4 6 8
        // 0 1 2 3
        // Search for 5
        // will return (-(insertionpoint) -1) of first element greater than 5 which 6. index of 6 is 2
        // (-(2) -1)= -3
        // How to get actual insertion point from this value?
        // -(-3) -1 = 3 -1 = 2
        // -(returned value from binary Search) -1
        List<Integer> subsequence = new ArrayList<>();
        for(int i =0 ;i < nums.length; i++) {
            if(i==0 || subsequence.get(subsequence.size()-1) < nums[i]) {
                subsequence.add(nums[i]);
            } else {
                int index = Collections.binarySearch(subsequence, nums[i]);
                if( index < 0) {
                    index = -index -1;
                }
                subsequence.set(index, nums[i]);
            }
        }

        return subsequence.size();

    }
}
