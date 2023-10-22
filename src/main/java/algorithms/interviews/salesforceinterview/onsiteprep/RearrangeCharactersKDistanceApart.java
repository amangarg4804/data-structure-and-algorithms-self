package algorithms.interviews.salesforceinterview.onsiteprep;

import java.util.*;

//Given a string s and an integer k, rearrange s such that the same characters are at least distance k from each other. If it is not possible to rearrange the string, return an empty string "".
//Example 1:
//
//Input: s = "aabbcc", k = 3
//Output: "abcabc"
//Explanation: The same letters are at least a distance of 3 from each other.
//Example 2:
//
//Input: s = "aaabc", k = 3
//Output: ""
//Explanation: It is not possible to rearrange the string.
//Example 3:
//
//Input: s = "aaadbbcc", k = 2
//Output: "abacabcd"
// a-3,
//Explanation: The same letters are at least a distance of 2 from each other.
//
//Constraints:
//
//1 <= s.length <= 3 * 105
//s consists of only lowercase English letters.
//0 <= k <= s.length
public class RearrangeCharactersKDistanceApart {
    public String rearrangeString(String s, int k) {
        Map<Character, Integer> charToFreq = new HashMap<>();
        for(int i =0 ; i< s.length(); i++) {
            charToFreq.put(s.charAt(i), charToFreq.getOrDefault(s.charAt(i), 0) +1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((e1, e2) -> e2.getValue() -e1.getValue()); // max heap- max frequency element to be polled/peeked first
        pq.addAll(charToFreq.entrySet());
        //Input: s = "aaadbbcc", k = 2
        //Output: "abacabcd"
        // a-3, b-2, c- 2, d -1
        // a
        StringBuilder sb = new StringBuilder();
        List<Map.Entry<Character, Integer>> previous =new ArrayList<>();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> current = pq.poll();
            sb.append(current.getKey());
            current.setValue(current.getValue()-1);
            //
            if(current.getValue() >0) {
                previous.add(current);
            }

        }
        return sb.length() == s.length()? sb.toString(): "";
    }
}
