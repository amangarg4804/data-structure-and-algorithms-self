package algorithms.leetcodedp;

//Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
//
//An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings respectively,
// such that:
//
//s = s1 + s2 + ... + sn
//t = t1 + t2 + ... + tm
//|n - m| <= 1
//The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
//Note: a + b is the concatenation of strings a and b.
//Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//Output: true
//Explanation: One way to obtain s3 is:
//Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
//Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
//Since s3 can be obtained by interleaving s1 and s2, we return true.
//Example 2:
//
//Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//Output: false
//Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
//Example 3:
//
//Input: s1 = "", s2 = "", s3 = ""
//Output: true
//
//
//Constraints:
//
//0 <= s1.length, s2.length <= 100
//0 <= s3.length <= 200
//s1, s2, and s3 consist of lowercase English letters.
//
//
//Follow up: Could you solve it using only O(s2.length) additional memory space?
public class InterleavingString {

    // s1="a", s2="b", s3 = "a"
    // if (s1Index < s1.length  || s2Index < s2.length ) && s1Index + s2Index >= s3.length) {return false;}
    //s1=a, s2=b, s3 = ab
    // if (s1Index == s1.length  && s2Index == s2.length) && s1Index + s2Index=>= s3.length) {return true;} // found match
    // s1=aa, s2="", s3 = aa
    // if(s2Index ==s2.length) { //check only s1 if(s1.charAt(s1Index) == }
    //if(s1Index ==s1.length) { //check only s2 }

    public boolean isInterleave(String s1, String s2, String s3) {
        return isInterleave(s1, s2, s3, 0, 0);
    }

    private boolean isInterleave(String s1, String s2, String s3, int s1Index, int s2Index) {
        //TLE 102 / 106 testcases passed
        if (s1Index == s1.length()  && s2Index == s2.length() ) {
            //Case 1: s1 and s2 can form s3 : s1=a, s2=b, s3 = ab
            if(s1Index + s2Index == s3.length()) {
                return true;
            }
            // Case 2: s1 and s2 have reached their respective lengths but s3 is larger. s1= "", s2="", s3=ab
            // or s1=a, s2=b, s3=aba
            if(s1Index + s2Index <s3.length()) {
                return false;
            }
        }
        // Case 3: all characters of s1 and s2 are yet to be consumed but s3 doesn't have anymore characters left
        // s1="a", s2="b", s3 = ""
        if ((s1Index < s1.length()  || s2Index < s2.length()) && s1Index + s2Index >= s3.length()) {
            return false;
        }

        //Case 4: s2 doesn't have chars left. s1=aa, s2="", s3 = aa
        if(s2Index==s2.length()) { //s2 is done, only compare s1
            if(s1.charAt(s1Index) == s3.charAt(s1Index +s2Index)) {
                return isInterleave(s1, s2, s3, s1Index+1, s2Index);
            }
            return false;
        }
        //Case 5: s1 doesn't have chars left s1="", s2=aa, s3 = aa
        if(s1Index==s1.length()) { //s1 is done, only compare s2
            if(s2.charAt(s2Index) == s3.charAt(s1Index +s2Index)) {
                return isInterleave(s1, s2, s3, s1Index, s2Index+1);
            }
            return false;
        }
        // Case 6: both s1 and s2 have chars left and both match
        if(s1.charAt(s1Index) == s3.charAt(s1Index + s2Index) && s2.charAt(s2Index) == s3.charAt(s1Index + s2Index)) {
            return isInterleave(s1, s2, s3, s1Index+1, s2Index)  ||
                    isInterleave(s1, s2, s3, s1Index, s2Index+1) ;
        }
        // only s1 match
        if(s1.charAt(s1Index) == s3.charAt(s1Index + s2Index)) {
            return isInterleave(s1, s2, s3, s1Index+1, s2Index);
        }
        // only s2 match
        if( s2.charAt(s2Index) == s3.charAt(s1Index + s2Index)) {
            return isInterleave(s1, s2, s3, s1Index, s2Index+1) ;
        }
        // Case 9: both s1 and s2 have chars left but none match
        return false;
    }

    public boolean isInterleave2(String s1, String s2, String s3) {
        // optimization to remove several corner cases:
        if(s1.length()+ s2.length() != s3.length()) {
            return false;
        }
        // s1Index and s2Index will change, so 2-dimensional dp
        Boolean[][] dp = new Boolean[s1.length()+1][s2.length()+1];
        return dp(s1, s2, s3, 0, 0, dp);
    }

    private boolean dp(String s1, String s2, String s3, int s1Index, int s2Index, Boolean[][] dp) {
        //top down dp
        if(dp[s1Index][s2Index] !=null) {
            return dp[s1Index][s2Index];
        }
        //case 1: s1 is empty or s1Index has reached its length. s1="", s2=ab, s3 =ab
        if(s1Index ==s1.length()) {
            dp[s1Index][s2Index] = s2.substring(s2Index).equals(s3.substring(s1Index+s2Index));
            return dp[s1Index][s2Index];
        }
        //case 2: s2 is empty or s2Index has reached its length. s1=ab, s2="", s3 =ab
        if(s2Index ==s2.length()) {
            dp[s1Index][s2Index] = s1.substring(s1Index).equals(s3.substring(s1Index+s2Index));
            return dp[s1Index][s2Index];
        }
        // neither of the strings have reached their length yet. so we compare current char
        if((s1.charAt(s1Index) ==s3.charAt(s1Index + s2Index) && dp(s1, s2, s3, s1Index+1, s2Index, dp) ) ||
                (s2.charAt(s2Index) ==s3.charAt(s1Index + s2Index) && dp(s1, s2, s3, s1Index, s2Index +1 , dp) )) {
            dp[s1Index][s2Index] = true;
            return dp[s1Index][s2Index];
        }
        dp[s1Index][s2Index] = false;
        return dp[s1Index][s2Index];
        //Time complexity: O(m⋅n), where
        //m is the length of s1 and n is the length of s2.
        //That's a consequence of the fact that each (i, j) combination is computed only once.
        //Space complexity: O(m⋅n) to keep double array memo.
    }

    private boolean dp2(String s1, String s2, String s3, int s1Index, int s2Index, Boolean[][] dp) {
        //top down dp
        //using substring is duplicate code since substring itself is checking char by char. We are already doing so
        if(dp[s1Index][s2Index] !=null) {
            return dp[s1Index][s2Index];
        }
        if (s1Index == s1.length()  && s2Index == s2.length() ) {
            return true;
        }
        // neither of the strings have reached their length yet. so we compare current char
        if((s1Index < s1.length() && s1.charAt(s1Index) ==s3.charAt(s1Index + s2Index) && dp(s1, s2, s3, s1Index+1, s2Index, dp) ) ||
                (s2Index < s2.length() && s2.charAt(s2Index) ==s3.charAt(s1Index + s2Index) && dp(s1, s2, s3, s1Index, s2Index +1 , dp))) {
            dp[s1Index][s2Index] = true;
            return dp[s1Index][s2Index];
        }
        dp[s1Index][s2Index] = false;
        return dp[s1Index][s2Index];
        //Time complexity: O(m⋅n), where
        //m is the length of s1 and n is the length of s2.
        //That's a consequence of the fact that each (i, j) combination is computed only once.
        //Space complexity: O(m⋅n) to keep double array memo.
    }



}
