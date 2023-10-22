package algorithms.interviews.salesforceinterview.onsiteprep;

//https://leetcode.com/problems/reorganize-string/description/
//Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
//
//Return any possible rearrangement of s or return "" if not possible.

//Example 1:
//Input: s = "aab"
//Output: "aba"

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//Example 2:
//Input: s = "aaab"
//Output: ""
//1 <= s.length <= 500
//s consists of lowercase English letters.
public class RearrangeCharacters {
    public String reorganizeString(String s) {
        //From the second example, it is evident that the limiting factor that determines when the string can be rearranged
        // is the occurrences of the characters in the input string. If there are too many instances of a given character,
        // it becomes impossible to rearrange the characters in a way that satisfies the condition of having no adjacent characters being the same.
        // Therefore, if the count of any character exceeds half the length of the string (i.e. if it appears more than ceil(length/2) times), (ceiling is next greater element in sorted order)
        // it is not possible to rearrange the characters, and the function should return an empty string.
        // length 5: aaabc = > max freq can be 3
        // length 4: aabc-> max freq can be 2
        // the idea is to sort the characters by their frequency in a pq and build the string using the most frequent character in pq

        Map<Character, Integer> charToFreq = new HashMap<>();
        for(int i =0 ; i< s.length(); i++) {
            charToFreq.put(s.charAt(i), charToFreq.getOrDefault(s.charAt(i), 0) +1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((e1, e2) -> e2.getValue() -e1.getValue()); // max heap- max frequency element to be polled/peeked first
        pq.addAll(charToFreq.entrySet());
        StringBuilder sb = new StringBuilder();
        Map.Entry<Character, Integer> previous = null;
        while (!pq.isEmpty()) {
            Map.Entry<Character,Integer> entry = pq.poll();
            sb.append(entry.getKey());// notice that we are not comparing this character with the previously appended character. We get correct answer without the need of explicit comparison
            entry.setValue(entry.getValue()-1);
            if(previous !=null && previous.getValue() >0) {
                pq.offer(previous);
            }
            previous = entry;
        }
        // take the example aaab. previous will contain a and pq will be empty. sb will contain aba
        return sb.length()==s.length() ? sb.toString(): "";
        // time: n for for loop, nlogk for addAll, log k for pq.offer will be called n times in case of string like aaaabbbb, every time offer will be called in while loop.
        // n + nlogk + nlogk
    }
}
