package algorithms.leetcodedp;

import java.util.List;
import java.util.Map;

//Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
//
//Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
//Each vowel 'a' may only be followed by an 'e'.
//Each vowel 'e' may only be followed by an 'a' or an 'i'.
//Each vowel 'i' may not be followed by another 'i'.
//Each vowel 'o' may only be followed by an 'i' or a 'u'.
//Each vowel 'u' may only be followed by an 'a'.
//Since the answer may be too large, return it modulo 10^9 + 7.
//
//Example 1:
//Input: n = 1
//Output: 5
//Explanation: All possible strings are: "a", "e", "i" , "o" and "u".

//Example 2:
//Input: n = 2
//Output: 10
//Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
//Example 3:
//Input: n = 5
//Output: 68
//Constraints:
//1 <= n <= 2 * 10^4
public class CountVowelPermutations {
    Map<Character, List<Character>> map = Map.of('a', List.of('e'),
            'e', List.of('a', 'i'),
            'i', List.of('a', 'e', 'o', 'u'),
            'o', List.of('i','u'),
            'u', List.of('a'),
            'z', List.of('a','e','i','o','u')); // hack, If previous is z, current char can be anything
    public int countVowelPermutation(int n) {
        return countVowelPermutation(n, 'z');
    }

    private int countVowelPermutation(int n, char prev) {
        // TLE 5 / 64 testcases passed
        if(n==0) {
            return 1;
        }
        int count =0;
        for(char c: map.get(prev)) {
            // possible strings have to be exactly length n. so if n=2, we shouldn't include strings with length 1, i.e, "a", "e" etc. we can only include
            // "ae", "ea" etc.
         count += countVowelPermutation(n-1, c);
        }
        return count;
    }

    public int countVowelPermutation2(int n) {
        // n and previous vary, 2 dimensional dp
        int[][] dp = new int[n+1][26];
        return dp(n, 'z', dp);
    }

    private int dp(int n, char prev, int[][] dp) {
        // TLE 5 / 64 testcases passed
        if(n==0) {
            return 1;
        }
        if(dp[n][prev-'a'] !=0) {
            return dp[n][prev-'a'];
        }
        //Since the answer may be too large, return it modulo 10^9 + 7.
        // NOTE: modulo 10^9 +7 means 10000000000 + 7 = 1000000007
        // returning the answer as modulo 10^9 + 7 means returning the result as result % 1000000007
        int MOD = 1000000007;
        long count =0;
        for(char c: map.get(prev)) {
            // possible strings have to be exactly length n. so if n=2, we shouldn't include strings with length 1, i.e, "a", "e" etc. we can only include
            // "ae", "ea" etc.
            count += dp(n-1, c, dp);
        }
        dp[n][prev-'a'] = (int)(count %MOD);
        return dp[n][prev-'a'];
        //Time complexity: O(N). This is because there are NNN recursive calls to each vowel. Therefore, the total number of function calls to vowelPermutationCount
        // is 5⋅N, which leads to time complexity of O(N) Initializations will take O(1) time.
        // Putting them together, this solution takes O(N) time.
        //
        //Space complexity: O(N). This is because O(5⋅N) space is required for memoization. Furthermore, the size of the system stack used by recursion calls will be
        // O(N). Putting them together, this solution uses O(N) space.
    }
}
