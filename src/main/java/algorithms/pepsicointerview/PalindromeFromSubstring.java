package algorithms.pepsicointerview;

import java.util.ArrayList;
import java.util.List;

public class PalindromeFromSubstring {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        // a palindrome can be formed by rearranging letters of a String if frequency of maximum characters with odd frequency is 1
        List<Boolean> result = new ArrayList<>();
        for(int i=0; i< queries.length; i++) {
            String input = s.substring(queries[i][0], queries[i][1] +1);
            int[] freq = new int[26];
            for(int j=0; j< input.length(); j++) {
                freq[input.charAt(j)-'a']++;
            }

            int oddCount = 0;
            for(int j=0; j< freq.length; j++) {
                if(freq[j] %2!=0) {
                    oddCount++;
                }
            }
            if(oddCount <=1 || oddCount/2 <= queries[i][2]) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }

    public List<Boolean> canMakePaliQueries2(String s, int[][] queries) {
        // First we prepare an array of arrays, which keeps track of frequency of each character at a particular index starting from 0 index
        // Example: abca ->
        // adding a -> {{1,0,0,....}, }
        // adding b ->      {{1,0,0,....}, {1, 1, 0, ,0...}}
        // adding c -> {{1,0,0,....}, {1, 1, 0, ,0...}, {1, 1, 1, ,0...}}
        // adding a->  {{1,0,0,....}, {1, 1, 0, ,0...}, {1, 1, 1, ,0...}, {2, 1, 1, ,0...}}
        // so an array item at  index i contains frequency of each character from 0 index to i index
        // if we need to find frequency of characters between 2 indexes i, j, We can easily subtract frequency at i-1 from frequency at j

        // example: to find frequency of 'a' between 1st and 3rd index we can do arr[3]['a' -'a'] -arr[0, 'a' -'a'] = 1
        int[][] freq = new int[s.length()][26];
        for(int i =0; i< s.length(); i++) {
            if(i==0) {
                freq[i] = new int[26];
            } else {
                freq[i] = freq[i-1].clone() ;// clone the previous array
            }
            freq[i][s.charAt(i)-'a']++;
        }
        List<Boolean> result = new ArrayList<>();
        for(int i=0; i< queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            int odd =0;
            for(int j=0; j< 26; j++) { // count odd frequencies. Notice that previous solution was iterating over all characters of substring, here we only iterate over constant no of times
                // we have to subtract queries[start-1][j], but if start =0, index will be out of bounds
                int startFreqToSubtract = start==0? 0: freq[start-1][j];
                int count = freq[end][j] -startFreqToSubtract;
                if(count%2!=0) {
                    odd++;
                }
            }
            if(odd <=1 || odd/2 <= queries[i][2]) {
                result.add(true);
            } else {
                result.add(false);
            }

        }
        return result;
    }
}
