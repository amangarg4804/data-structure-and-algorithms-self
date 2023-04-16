package algorithms.leetcodegreedy;

//Given a string s, partition the string into one or more substrings such that the characters in each substring are unique.
// That is, no letter appears in a single substring more than once.
//
//Return the minimum number of substrings in such a partition.
//
//Note that each character should belong to exactly one substring in a partition

import java.util.HashSet;
import java.util.Set;

//Input: s = "abacaba"
//Output: 4
//Explanation:
//Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
//It can be shown that 4 is the minimum number of substrings needed.
public class OptimalPartition {
    public int partitionString(String s) {
        //abacab
        // ab, ac , ab,
        // a, bac, ab

        // abbbc
        // ab, b , bc
        // a , b, b, b, c
        //

        // we can use hashset to keep track of duplicate. As soon as we encounter duplicate character, we increment result (number of substrings)

        Set<Character> chars = new HashSet<>();
        int noOfSubStrings=0;
        for(int i =0; i< s.length(); i++) {
            if(chars.contains(s.charAt(i))) {
                noOfSubStrings++;
                chars.clear();
            }
            chars.add(s.charAt(i));
        }
        if(!chars.isEmpty()) {
            noOfSubStrings++;
        }
        return noOfSubStrings;

    }
}
