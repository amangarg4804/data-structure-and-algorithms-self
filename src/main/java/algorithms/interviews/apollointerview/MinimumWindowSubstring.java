package algorithms.interviews.apollointerview;

import java.util.HashMap;
import java.util.Map;

//Given two strings s and t of lengths m and n respectively, return the minimum window
//substring
// of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
//
//The testcases will be generated such that the answer is unique.
//
//
//
//Example 1:
//
//Input: s = "ADOBECODEBANC", t = "ABC"
//Output: "BANC"
//Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
//Example 2:
//
//Input: s = "a", t = "a"
//Output: "a"
//Explanation: The entire string s is the minimum window.
//Example 3:
//
//Input: s = "a", t = "aa"
//Output: ""
//Explanation: Both 'a's from t must be included in the window.
//Since the largest window of s only has one 'a', return empty string.
//
//
//Constraints:
//
//m == s.length
//n == t.length
//1 <= m, n <= 105
//s and t consist of uppercase and lowercase English letters.
//
//
//Follow up: Could you find an algorithm that runs in O(m + n) time?
public class MinimumWindowSubstring {
    // AABCADEF  AAD
    public String minWindow(String s, String t) {
        Map<Character, Integer> reqFrequencyMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            reqFrequencyMap.put(t.charAt(i), reqFrequencyMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        int requiredMatchingCharCount = reqFrequencyMap.size();
        int windowStart = 0;
        int windowEnd = 0;
        int[] ans = {-1, 0, 0}; // storing answer array, indexes store window length, windowStart index and windowEnd indexes where we found answer
        Map<Character, Integer> currentFreqMap = new HashMap<>();
        int matchingCharCount = 0; // currently we haven't found matching char. A matching char means tha character has equal frequency in substring as well as the input t
        // in other words the character should have same frequency in reqFrequencyMap and currentFreqMap
        while (windowEnd < s.length()) {
            char c = s.charAt(windowEnd);
            currentFreqMap.put(c, currentFreqMap.getOrDefault(c, 0) + 1);
            if (currentFreqMap.get(c).equals(reqFrequencyMap.get(c))) {
                matchingCharCount++;
            }
            // we found a matching substring containing all chars in the desired frequency
            while (windowStart <=windowEnd && matchingCharCount == requiredMatchingCharCount) {
                // why use <= in first condition and not <. Because it is possible that window contains just one character s="a" t ="a"
                // second condition matchingCharCount == requiredMatchingCharCount makes sure we break the loop as soon as window doesn't contain required frequency for a particular char
                // update the answer
                if (ans[0] == -1 || windowEnd - windowStart + 1 < ans[0]) {
                    ans[0] = windowEnd - windowStart + 1;
                    ans[1] = windowStart;
                    ans[2] = windowEnd;
                }
                // try to reduce window size by removing chars at windowStart index
                char key = s.charAt(windowStart);
                currentFreqMap.put(key, currentFreqMap.get(key) - 1);
                if (reqFrequencyMap.containsKey(key) && currentFreqMap.get(key).intValue() < reqFrequencyMap.get(key).intValue()) {
                    matchingCharCount--;
                }
                windowStart++;
            }
            windowEnd++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1); // substring first index is inclusive, 2nd param is exclusive, se we do +1
    }
}
