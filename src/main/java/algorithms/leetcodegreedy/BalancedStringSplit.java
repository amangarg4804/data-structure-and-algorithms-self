package algorithms.leetcodegreedy;
//https://leetcode.com/problems/split-a-string-in-balanced-strings/
//Balanced strings are those that have an equal quantity of 'L' and 'R' characters.
//
//Given a balanced string s, split it into some number of substrings such that:
//
//Each substring is balanced.
//Return the maximum number of balanced strings you can obtain.

//Example 1:
//Input: s = "RLRRLLRLRL"
//Output: 4
//Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
public class BalancedStringSplit {


    public int balancedStringSplit(String s) {
        // we have to find substring, so we won't sort it
            int substringCount = 0;
            int rCount =0;
            int lCount =0;
            for(int i=0; i< s.length(); i++) {
                if(s.charAt(i) =='R') {
                    rCount++;   
                } else {
                    lCount++;
                }
                if(rCount==lCount) {
                    substringCount++;
                    rCount =0;
                    lCount=0;
                }
            }
            return substringCount;
    }
}
