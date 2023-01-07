package algorithms.leetcodetrie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Input: words = ["abcd","dcba","lls","s","sssll"]
//Output: [[0,1],[1,0],[3,2],[2,4]]
//Explanation: The palindromes are ["abcddcba","dcbaabcd","slls","llssssll"]
public class PalindromePairs {
    // 3 cases
    // Case 1: If we have a string and we are able to find exact reverse of it
    // e.g abcd and its reverse dcba will form a palindrome -> abcddcba
    // Case 2: If we split the string at an index i, abccc , let's say at 2, giving us ab and ccc
    // and 2nd half is already a palindrome- ccc
    // It will become palindrome if we are able to find reverse of first half 'ab',
    // abccc + ba = abcccba -> palindrome
    // Case 3:  If we split the string at an index i, cccab , let's say at 3, giving us ccc and ab
    // And first half is already a palindrome - ccc
    // it will become palindrome if we are able to find reverse of 2nd half ab
    // ba + cccab -> bacccab-> Palindrome
    public List<List<Integer>> palindromePairs(String[] words) {
        // TLE
        List<List<Integer>> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for(int i=0; i< words.length; i++) {
            map.put(words[i], i);
        }

        for(int i=0; i< words.length; i++) {
            for(int j=0; j<= words[i].length(); j++) {
                // For input ["a",""] // duplicate issue is not for this case. For "a", we add [0,1] and [1,0]. For "", we don't add anything result
                // Expected answer : [[0,1],[1,0]] -> This why we have to do substring(0,0) as well
                String firstHalf = words[i].substring(0, j); // NOTE substring(0,0) for s1 will result in first half being "" and second half being s1 itself. substring(s.length) will result in first half being s1 itself and second half being empty. The same will happen for another string s2 which combining with s1 results in palindrome  So, we will be duplicating value in result
                String secondHalf = words[i].substring(j); // NOTE: no need to add words[i].length() as second param to substring
                if (isPalindrome(firstHalf)) {
                    String reverseSecondHalf = reverse(secondHalf);
                    if (map.containsKey(reverseSecondHalf) && map.get(reverseSecondHalf) != i) {
                        result.add(List.of(map.get(reverseSecondHalf), i));
                    }
                }
                if (isPalindrome(secondHalf) && secondHalf.length() != 0) {
                    String reversedFirstHalf = reverse(firstHalf);
                    if (map.containsKey(reversedFirstHalf) && map.get(reversedFirstHalf) != i) { // ["abcd","dcba"], for abcd it adds [0,1] and [1,0] because abcddcba and dcbaabcd both are palindromes. For dcba, it adds them again, duplicating the result
                        result.add(List.of(i, map.get(reversedFirstHalf)));
                    }
                }
            }
        }
        return result;
    }

    private String reverse(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    public static boolean isPalindrome(String input) {
        if(input == null) {
            return false;
        }
        int start = 0;
        int end = input.length() -1;

        while(start < end) {
            if(input.charAt(start) != input.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    public static void main(String[] args) {
        new PalindromePairs().palindromePairs(new String[]{"a", ""});
    }
}
