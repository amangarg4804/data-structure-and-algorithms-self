package algorithms.leetcodedp;


import java.util.Arrays;

//A message containing letters from A-Z can be encoded into numbers using the following mapping:
//
//'A' -> "1"
//'B' -> "2"
//...
//'Z' -> "26"
//To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
//
//"AAJF" with the grouping (1 1 10 6)
//"KJF" with the grouping (11 10 6)
//Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
//Given a string s containing only digits, return the number of ways to decode it.
//The test cases are generated so that the answer fits in a 32-bit integer.
//Example 1:
//Input: s = "12"
//Output: 2
//Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
//Example 2:
//Input: s = "226"
//Output: 3
//Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
//Example 3:
//Input: s = "06"
//Output: 0
//Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
//Constraints:
//1 <= s.length <= 100
//s contains only digits and may contain leading zero(s).
public class DecodeWays {
    public int numDecodings(String s) {
        // TLE
        return numDecodings(s, 0);
    }

    private int numDecodings(String s, int i) {
        // TLE
        if(i == s.length()) {
            return 1;
        }
        if(s.charAt(i) =='0') { // In case of 00, two char don't result in a separate decoding , in case of 01, 02, 03, we don't need to calculate two char numbers as per the problem statement 06 is not equivalent to 6 and can't be mapped to F
            return 0;
        }
        if(i==s.length()-1) {
            return 1;
        }
        // either we can decode just the current char or we can choose to decode two chars starting from current char
        int result = numDecodings(s, i+1);
        int first = (s.charAt(i) -'0') * 10;
        int second = s.charAt(i+1) -'0';
        if(first + second <=26) {
            result += numDecodings(s,i+2);
        }
        return result;
    }

    public int numDecodings2(String s) {
        // top down dp
        //  what changes? - only the index.
        // one dimension dp
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return numDecodings(s, 0, dp);
    }
    // counting dp- base cases are not set to 0, The recurrence relation usually only includes addition terms between states, so if the base cases were set to 0, then all states would be stuck at 0.
    private int numDecodings(String s, int i, int[] dp) {
        if(i == s.length()) {
            return 1;
        }
        if(s.charAt(i) =='0') {
            return 0;
        }
        if(i == s.length()-1) {
            return 1;
        }
        if(dp[i] != -1) {
            return dp[i];
        }
        int result = numDecodings(s, i+1, dp);
        int twoDigit = ((s.charAt(i) - '0') * 10) + (s.charAt(i+1) -'0');
        if(twoDigit <= 26) { // if (Integer.parseInt(s.substring(i, i + 2)) <= 26) {
            result += numDecodings(s, i+2, dp);
        }
        dp[i] = result;     
        return dp[i];
    }
}
