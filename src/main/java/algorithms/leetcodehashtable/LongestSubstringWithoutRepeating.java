package algorithms.leetcodehashtable;

import java.util.*;

public class LongestSubstringWithoutRepeating {

    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0) {
            return 0;
        }
        // sliding window
        // we require two data structures, one to hold characters in sequence and another to tell us uniqueness of characters
        // For uniqueness, we can use hashset
        // for storing chars in sequence, we can use queue
        Queue<Character> q = new LinkedList<>();
        Set<Character> set = new HashSet<>();
        int max =0;
        for(int windowEnd=0; windowEnd< s.length(); windowEnd++) {
            char c = s.charAt(windowEnd);
            if(set.contains(c)) { // current charcter is already present in set. meaning we need to remove all characters before that character
                // e.g. abccd . When we encounter 2nd c, we should remove abc from q as well as set.
                int size = q.size();
                for(int i=0; i< size; i++) {
                    char qChar = q.poll();
                    set.remove(qChar);
                    if(qChar ==c) {
                        break;
                    }
                }
            }
            q.offer(c);
            set.add(c); //at any point size of q and set will be same
            max = Math.max(q.size(), max);

        }
        return max;
        // time O(2N) in worst case when last character is repeated char of 2nd last abcdefghijkll, we will we iterating over string twice
    }

    public int lengthOfLongestSubstring2(String s) {
        if(s.length()==0) {
            return 0;
        }
        // sliding window
        // We can use hashmap instead of using q and set.
        // The challenge would be that map doesn't store characters as per their sequence
        // that's ok. We can use the value of windowStart and figure our character at windowStart
        // keep removing until we find the same character as current character. E.g abccd
        // for storing chars in sequence, we can use queue
        Map<Character, Integer> map = new HashMap<>();
        int windowStart =0;
        int max =0;
        for(int windowEnd=0; windowEnd< s.length(); windowEnd++) {
            char c = s.charAt(windowEnd);
            if(map.containsKey(c)) { // current character is already present in map. meaning we need to remove all characters before that character
                // e.g. abccd . When we encounter 2nd c, we should remove abc from map
                while (windowStart< windowEnd) {
                    char startChar = s.charAt(windowStart);
                    map.remove(startChar);
                    windowStart++;
                    if(startChar==c) {
                        break;
                    }
                }
            }
            map.put(c, windowEnd); //at any point size of map and window size will be same
            max = Math.max(map.size(), max);

        }
        return max;
        // time O(2N) in worst case when last character is repeated char of 2nd last abcdefghijkll.
    }

    public int lengthOfLongestSubstring3(String s) {
        if(s.length()==0) {
            return 0;
        }
        // sliding window
        // We can use set instead of map

        Set<Character> set = new HashSet<>();
        int windowStart =0;
        int max =0;
        for(int windowEnd=0; windowEnd< s.length(); windowEnd++) {
            char c = s.charAt(windowEnd);
            if(set.contains(c)) { // if current character is already present in set. meaning we need to remove all characters before that character
                // e.g. abccd . When we encounter 2nd c, we should remove abc from map
                while (windowStart< windowEnd) {
                    char startChar = s.charAt(windowStart);
                    set.remove(startChar);
                    windowStart++;
                    if(startChar==c) {
                        break;
                    }
                }
            }
            set.add(c); //at any point size of set and window size will be same
            max = Math.max(set.size(), max);

        }
        return max;
        // time O(2N) in worst case when last character is repeated char of 2nd last abcdefghijkll.
    }

    public int lengthOfLongestSubstring4(String s) {
        if (s.length() == 0) {
            return 0;
        }
        // All the above solutions are 2N. can we do better? in one pass?
        // We don't need to remove elements from map, we can simply move windowStart to next index
        // a b c c d c d
        // 0 1 2 3 4 5 6
        // At any point map will contain the last occurence of a character
        // as soon as we encounter 2nd c, we change c's index to 3 and windowStart to 3.
        // as soon as we encounter 3nd c, we change c's index to 5 and windowStart to previous c's index + 1 = 3+1 =4
        Map<Character, Integer> map =new HashMap<>();
        int windowStart =0;
        int max =0;
        for(int windowEnd = 0 ; windowEnd < s.length(); windowEnd++) {
            if(map.containsKey(s.charAt(windowEnd))) {
//                windowStart = map.get(s.charAt(windowEnd)) + 1; This doesn't work because it is possibe that map contains the key but we have already moved windowStart ahead
                // a b b a
                // 0 1 2 3
                // as soon as we encounter 2nd b, we move windowStart to 2. Now when we encounter 2nd a, map already contains a. but we can't move windowStart to a's index +1 which is 1
                windowStart = Math.max(windowStart, map.get(s.charAt(windowEnd))+1);
            }
            map.put(s.charAt(windowEnd), windowEnd);
            max = Math.max(max, windowEnd-windowStart +1);
        }
        return max;
    }

    public static void main(String[] args) {
        new LongestSubstringWithoutRepeating().lengthOfLongestSubstring("pwwkew");
    }
}
