package algorithms.leetcodehashtable;

import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams1(String[] strs) {
        //  sort every string. Create a map with sorted String as key and the value from original array
        Map<String, List<String>> map = new HashMap<>();
        for(int i=0; i< strs.length; i++) {
            char[] arr = strs[i].toCharArray(); // O(n) time and space
            Arrays.sort(arr);
            String sorted= new String(arr);
            List<String> anagramStrings = map.getOrDefault(sorted, new ArrayList<>());
            anagramStrings.add(strs[i]); // add current non-sorted string
            map.put(sorted, anagramStrings);
        }
        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        // Without sorting the string
        Map<Map<Character,Integer>, List<String>> group = new HashMap<>();
        for(int i=0; i< strs.length; i++) {
            Map<Character, Integer> charFrequency = new HashMap<>();
            for( int j=0; j< strs[i].length(); j++) {
                charFrequency.put(strs[i].charAt(j), charFrequency.getOrDefault(strs[i].charAt(j), 0)+1);
            }

            List<String> anagrams = group.getOrDefault(charFrequency, new ArrayList<>());
            anagrams.add(strs[i]);
            group.put(charFrequency, anagrams);
        }
        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<Map<Character,Integer>, List<String>> entry : group.entrySet()) {
            result.add(entry.getValue());
        }
        return result; // can also do return new ArrayList<>(map.values()) // NOTE: Converting map's values to list
    }

    public List<List<String>> groupAnagrams3(String[] strs) {
        // Without sorting , Using key as frequency array's String value
        Map<String, List<String>> group = new HashMap<>();
        for(int i=0; i< strs.length; i++) {
            int[] frequency = new int[26];
            for( int j=0; j< strs[i].length(); j++) {
                frequency[strs[i].charAt(j) -'a']++;
            }
            String key = Arrays.toString(frequency); // char array to String is faster
            List<String> anagrams = group.getOrDefault(key, new ArrayList<>());
            anagrams.add(strs[i]);
            group.put(key, anagrams);
        }
        return new ArrayList<>(group.values()); // can also do return new ArrayList<>(map.values()) // NOTE: Converting map's values to list
    }

    public List<List<String>> groupAnagrams4(String[] strs) {
        // Without sorting , Using key as frequency array's String value
        Map<String, List<String>> group = new HashMap<>();
        for(int i=0; i< strs.length; i++) {
            char[] frequency = new char[26]; // char can hold 0 to 127. It is given that strs will contain only lower case alphabets
            for( int j=0; j< strs[i].length(); j++) {
                frequency[strs[i].charAt(j) -'a']++;
            }
            String key = new String(frequency);
            List<String> anagrams = group.getOrDefault(key, new ArrayList<>());
            anagrams.add(strs[i]);
            group.put(key, anagrams);
        }
        return new ArrayList<>(group.values());
    }
}
