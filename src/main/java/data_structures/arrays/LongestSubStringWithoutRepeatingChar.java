package data_structures.arrays;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStringWithoutRepeatingChar {

    public static void main(String[] args) {

        String[] inputArr = {"abcdeabcde", "ababcdabcde", "au", "pwwkew"};

        for (String str : inputArr) {
            System.out.println(substring(str));
        }
    }

    private static String substring(String str) {

        // Start from i=0
        // Check if a character is already seen before
        // If yes, save the string so far in this iteration
        // compare currentSize of stringStaringHere with longestSubstring.
        // If stringStaringHere is bigger, assign it to longestSubstring
        //
        // Keep iterating  and adding to string untill we find a repeating char
        // Save the current string into max
        // i++

        // pwwkew

        //au

        if (str == null || str.isEmpty()) {
            return "";
        }
        char[] arr = str.toCharArray();

        StringBuilder longestSubArray = new StringBuilder(String.valueOf(arr[0]));
        StringBuilder stringStaringHere = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                i = map.get(arr[i]);
                map.clear();
                stringStaringHere = new StringBuilder();
                continue;
            }
            map.put(arr[i], i);
            stringStaringHere.append(arr[i]);
            if (stringStaringHere.length() > longestSubArray.length()) {
                longestSubArray = stringStaringHere;
            }
        }

        return longestSubArray.toString();
    }


}
