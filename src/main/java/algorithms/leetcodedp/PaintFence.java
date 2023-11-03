package algorithms.leetcodedp;

//You are painting a fence of n posts with k different colors. You must paint the posts following these rules:
//
//Every post must be painted exactly one color.
//There cannot be three or more consecutive posts with the same color.
//Given the two integers n and k, return the number of ways you can paint the fence.
//
//
//
//Example 1:Input: n = 3, k = 2
//Output: 6
//Explanation: All the possibilities are shown.
//Note that painting all the posts red or all the posts green is invalid because there cannot be three posts in a row with the same color.
//Example 2:
//
//Input: n = 1, k = 1
//Output: 1
//Example 3:
//
//Input: n = 7, k = 2
//Output: 42
//
//
//Constraints:
//
//1 <= n <= 50
//1 <= k <= 105
//The testcases are generated such that the answer is in the range [0, 231 - 1] for the given n and k.
public class PaintFence {
    public int numWays(int n, int k) {
        // number of ways to paint 1st fence: k
        // no of ways to paint 2nd fence: k
        // no of ways to paint 3rd fence : k-1 (can't choose same color as last 2)
        // no of ways to paint a fence at i = (F(same colour as i-1) + F(different colour than i-1)) * F(no of ways to paint i-1)
        // Fi(different colour) = F(i-1) * (k-1)
        // F(same colour) = let's see with an example
        // Y represents yellow, R represents Red
        // Y Y Y - can't use yellow
        // R Y Y - can use red
        // so basically to paint i with same colour as i-1, we require than i-1 is not the same colour as i-2
        // Fi(same colour) = Fi-1(different colour)
        //  base cases :
        // ways to paint 1 wall = k
        // ways to paint 2 walls = k*k
        //If you are painting the ith post, you have two options:
        //
        //make it different color as i-1th post
        //make it same color as i-1 th post (if you are allowed!)
        //simply add these for your answer:
        //num_ways(i) = num_ways_diff(i) + num_ways_same(i)
        //
        //Now just think of how to calculate each of those functions.
        //The first one is easy. If you are painting the ith post, how many ways can you paint it to make it different from the i-1 th post? k-1
        //num_ways_diff(i) = num_ways(i-1) * (k-1)
        //The second one is hard, but not so hard when you think about it.
        //If you are painting the ith post, how many ways can you paint it to make it the same as the i-1th post?
        // At first, we should think the answer is 1 -- it must be whatever color the last one was.
        //num_ways_same(i) = num_ways(i-1) * 1
        //But no! This will fail in the cases where painting the last post the same results in three adjacent posts of the same color! We need to consider ONLY the cases where the last two colors were different. But we can do that!
        //num_ways_diff(i-1) <- all the cases where the i-1th and i-2th are different.
        //THESE are the cases where can just plop the same color to the end, and no longer worry about causing three in a row to be the same.
        //num_ways_same(i) = num_ways_diff(i-1) * 1
        //
        //We sum these for our answer, like I said before:
        //
        //num_ways(i) = num_ways_diff(i) + num_ways_same(i)
        //= num_ways(i-1) * (k-1) + num_ways_diff(i-1)
        //
        //We know how to compute num_ways_diff, so we can substitute:
        //num_ways(i) = num_ways(i-1) * (k-1) + num_ways(i-2) * (k-1)
        //
        //We can even simplify a little more:
        //
        //num_ways(i) = (num_ways(i-1) + num_ways(i-2)) * (k-1)
        //
        //As a note, trying to intuitively understand that last line is impossible. If you think you understand it intuitively, you are fooling yourself. Only the original equation makes intuitive sense.
        if(n==0) {
            return 0;
        }
        if(n==1) {
            return k;
        }
        if(n==2) {
            return k*k;
        }
        return (numWays(n-1, k) + numWays(n-2, k)) * (k-1);
    }

    public int numWays2(int n, int k) {
        // top down dp
        if(n==0) {
            return 0;
        }
        if(n==1) {
            return k;
        }
        if(n==2) {
            return k*k;
        }
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] =k;
        dp[2] = k*k;
        return dp(n, k, dp);
    }

    private int dp(int n, int k, int[] dp) {
      if(n==0 || dp[n] !=0) {
          return dp[n];
      }
        dp[n] = (dp(n-1, k, dp) + dp(n-2, k, dp)) * (k-1);
      return dp[n];
      //Time complexity: O(n)O(n)O(n)
        //
        //totalWays gets called with each index from n to 3. Because of our memoization, each call will only take O(1)O(1)O(1) time.
        //
        //Space complexity: O(n)O(n)O(n)
        //
        //The extra space used by this algorithm is the recursion call stack. For example, totalWays(50) will call totalWays(49),
        // which calls totalWays(48) etc., all the way down until the base cases at totalWays(1) and totalWays(2).
        // In addition, our hash map memo will be of size n at the end, since we populate it with every index from n to 3
    }

    public int numWays3(int n, int k) {
        // bottom up
        if(n==0) {
            return 0;
        }
        if(n==1) {
            return k;
        }
        if(n==2) {
            return k*k;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] =k;
        dp[2] = k*k;
        for(int i = 3;i <n+1; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) *(k-1);
        }
        return dp[n];
    }
}
