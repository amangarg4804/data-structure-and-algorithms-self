package algorithms.leetcodearraysandstrings;

import java.util.Arrays;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for(String s : strs) {
            while(s.indexOf(prefix)!=0) {
                prefix = prefix.substring(0, prefix.length()-1);
            }
        }
        return prefix;
    }

    public String longestCommonPrefix1(String[] strs) {
        // time: O(nLogn)
        Arrays.sort(strs); // to get first and last element we could also use compareTo instead of sorting the array. But compareTo complexity is O(n) which would result in O(n^2) solution
                            // NOTE: compareTo time complexity is equal to O(n), n being the length of smaller string
        String first = strs[0];
        String last = strs[strs.length-1];
        // we only need to compare first and last String after sorting. If first String is starts with "b"  and last string also starts with "b".
        // Since the array is sorted all the middle strings must be starting with b
        int i=0;
        while (i< first.length()) { // to check how many characters for first String match in last.
            if(first.charAt(i) != last.charAt(i)) { // It's ok to check the length of first String. index won't go out of range for last String. Check example from main method
                break;
            }
            i++;
        }
        return first.substring(0, i);
    }

    public static void main(String[] args) {
        String[] strs = new String[] {"",  "aba", "ab", "aaab", "aaaba"};
        Arrays.sort(strs);
        System.out.println(Arrays.toString(strs)); // prints [, aaab, aaaba, ab, aba]
        // NOTE: Empty is first element in sorted order of string, aaab comes before aaaba
        // If there is no index position at which they differ, then the shorter string lexicographically precedes the longer string

    }


}
