package algorithms.leetcodestringmatching;

//A string is called a happy prefix if is a non-empty prefix which is also a suffix (excluding itself).
//Given a string s, return the longest happy prefix of s. Return an empty string "" if no such prefix exists.
//Example 1:
//Input: s = "level"
//Output: "l"
//Explanation: s contains 4 prefix excluding itself ("l", "le", "lev", "leve"), and suffix ("l", "el", "vel", "evel"). The largest prefix which is also suffix is given by "l".
//Example 2:
//Input: s = "ababab"
//Output: "abab"
//Explanation: "abab" is the largest prefix which is also suffix. They can overlap in the original string.
//Constraints:
//1 <= s.length <= 105
//s contains only lowercase English letters.
public class LongestHappyPrefix {
    public String longestPrefix(String s) {
        //https://docs.google.com/document/d/1uf2fDgX_CnYSH8avhUGTvQdD7EwUcAHwlxFZOnPMkFw/edit
        int[] lps = new int[s.length()];
        lps[0] =0;
        //int maxLength = 0;
        //int maxIndex = 0; // index is not required because the prefix and suffix will be same. And we can use the start of the string, i.e, index 0 and maxLength to calculate result;
        for(int i=1; i< s.length(); i++) {
            int x = lps[i-1];
            // 0 1 2 3 4 5
            // a b c a b c
            // 0 0 0 1 2 ?
            // a b c a b c a b c
            while(s.charAt(x)!=s.charAt(i)) {
                if(x==0) {
                    x=-1;
                    break;
                }
                x = lps[x-1];
            }
            lps[i] =x+1;
            // if(lps[i] > maxLength) {
            //     maxLength = lps[i];
            // }
            // using maxLength would be wrong. We should check the last index of lps array
        }
        return  s.substring(0, lps[s.length()-1]); // substring (0,0) is empty string
    }
}
