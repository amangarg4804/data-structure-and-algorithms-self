package algorithms.leetcodedp;

//Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
//
//A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
//
//For example, "ace" is a subsequence of "abcde".
//A common subsequence of two strings is a subsequence that is common to both strings.
//
//
//
//Example 1:
//
//Input: text1 = "abcde", text2 = "ace"
//Output: 3
//Explanation: The longest common subsequence is "ace" and its length is 3.
//Example 2:
//
//Input: text1 = "abc", text2 = "abc"
//Output: 3
//Explanation: The longest common subsequence is "abc" and its length is 3.
//Example 3:
//
//Input: text1 = "abc", text2 = "def"
//Output: 0
//Explanation: There is no such common subsequence, so the result is 0.
//
//
//Constraints:
//
//1 <= text1.length, text2.length <= 1000
//text1 and text2 consist of only lowercase English characters.
public class LargestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        // either length of text1 can be more than text2 or vice versa. In both cases, text1 and text2 can have common subsequence
        /// babc ,dcabc- initially we don't know which array's index has to be incremented to find the common starting char
        // either first char is same or its not
        // if first char is same, we increment both indexes
        // if first char is not same, then we have to :
        //  -> search whether the first char of text 1 is present later in text2
        //->  search whether the first char of text2 is present later in text1
        //Then return the length
//        return longestCommonSubsequence(text1, text2, 0, 0);
        Integer[][] dp= new Integer[text1.length()][text2.length()]; // if we use prmitive int ,all values will be initialized by 0,
        // it won't be possible to distguish whether the value was set by memoize function or whetehr it is default value
        // another way could be to use primitive int and initialize all values by -1. That would be m*n time complexity for initialization.
        return longestCommonSubsequenceMemoize(text1, text2, 0, 0, dp);
    }

    private int longestCommonSubsequence(String text1, String text2, int text1Index, int text2Index) {
        // 17 / 47 testcases passed TLE
        if(text1Index >=text1.length() || text2Index >= text2.length()) {
            return 0;
        }
        // ab, ac
        // if both chars are same, then we can move ahead with next indices of both texts
        if(text1.charAt(text1Index) ==text2.charAt(text2Index)) {
            return 1+ longestCommonSubsequence(text1, text2, text1Index + 1, text2Index + 1);
        }
        // otherwise, try to find text1 char in text2 and text2 char in text1
        return Math.max(longestCommonSubsequence(text1, text2, text1Index+1, text2Index),
                longestCommonSubsequence(text1, text2, text1Index, text2Index +1));
    }


    // Let's think about memoization. As we have text1Index and text2Index variables, it is 2 dimensional dp

    private int longestCommonSubsequenceMemoize(String text1, String text2, int text1Index, int text2Index, Integer[][] dp) {
        if(text1Index >=text1.length() || text2Index >= text2.length()) {
            return 0;
        }
        // ab, ac
        // if both chars are same, then we can move ahead with next indices of both texts
        if(dp[text1Index][text2Index]!=null) {
                return dp[text1Index][text2Index];
        }
        if(text1.charAt(text1Index) ==text2.charAt(text2Index)) {
            int result = 1+ longestCommonSubsequenceMemoize(text1, text2, text1Index + 1, text2Index + 1, dp);
            dp[text1Index][text2Index] = result;
            return dp[text1Index][text2Index];
        }
        // otherwise, try to find text1 char in text2 and text2 char in text1
        int result = Math.max(longestCommonSubsequenceMemoize(text1, text2, text1Index+1, text2Index, dp),
                longestCommonSubsequenceMemoize(text1, text2, text1Index, text2Index +1, dp));
        dp[text1Index][text2Index] = result;
        return dp[text1Index][text2Index];
        //Time complexity : O(M⋅N)
        //This time, solving each subproblem has a cost of O(1). Again, there are M⋅N subproblems, and so we get a total time complexity of O(M⋅N
        //Space complexity : O(M⋅N)
        //We need to store the answer for each of the M⋅N subproblems.
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        //bottom up
        // if we see the recursion tree above, the deepest value is when either of the indices is greater than
        // calculations is done for greatest indexes first text1.length -1 ,text1.length -2 and so on
        //Observe that the subproblems have a natural "size" ordering; the largest subproblem is the one we start with, and the smallest subproblems are the ones with just one letter left in each word.
        // The answer for each subproblem depends on the answers to some of the smaller subproblems.
        int[][] dp = new int[text1.length()+1][text2.length()+1]; // all values are 0 by default
        // abc
        // bac
        //dp[2][2] = 1
        // dp[2][1] =Math.max(dp[3][1], dp[2][2])= dp[2][2] =1
        // dp[1][2] = 1
        // dp[1][1] = 1
        // dp[0][2]= 1+ dp[1][2] = 1+ 1 =2
        //dp[0][
        for(int i =text1.length()-1; i>=0; i--) {
            for(int j=text2.length()-1; j>=0; j--) {
                if(text1.charAt(i) ==text2.charAt(j)) {
                    dp[i][j] = 1+ dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }
        return dp[0][0];
    }

    public int longestCommonSubsequence3(String text1, String text2) {
        //bottom up space optimizaton
        // if we see the recursion tree above, the deepest value is when either of the indices is greater than
        // calculations is done for greatest indexes first text1.length -1 ,text1.length -2 and so on
        //Observe that the subproblems have a natural "size" ordering; the largest subproblem is the one we start with, and the smallest subproblems are the ones with just one letter left in each word.
        // The answer for each subproblem depends on the answers to some of the smaller subproblems.
        int[][] dp = new int[text1.length()+1][text2.length()+1]; // all values are 0 by default
        // abc
        // bac
        //dp[2][2] = 1
        // dp[2][1] =Math.max(dp[3][1], dp[2][2])= dp[2][2] =1
        // dp[1][2] = 1
        // dp[1][1] = 1
        // dp[0][2]= 1+ dp[1][2] = 1+ 1 =2
        //dp[0][
        for(int i =text1.length()-1; i>=0; i--) {
            for(int j=text2.length()-1; j>=0; j--) {
                if(text1.charAt(i) ==text2.charAt(j)) {
                    dp[i][j] = 1+ dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }
        return dp[0][0];
    }
}
