package algorithms.leetcodetrie;

// bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
//There will be at most 3 dots in word for search queries.


import java.util.*;
public class WordDictionaryWithoutTrie {
    Map<Integer, Set<String>> lengthToWordMap;
    public WordDictionaryWithoutTrie() {
        lengthToWordMap = new HashMap<>();
    }

    public void addWord(String word) {
        Set<String> words = lengthToWordMap.getOrDefault(word.length(), new HashSet<>());
        words.add(word);
        lengthToWordMap.put(word.length(), words);
    }

    public boolean search(String word) {
        if(!lengthToWordMap.containsKey(word.length())) {
            return false;
        }
        Set<String> words = lengthToWordMap.get(word.length());
        if(!word.contains(".")) {
            return words.contains(word);
        }
        for(String s: words) {
            if(isMatched(s, word)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMatched(String s, String word) {
        for(int i=0; i< word.length(); i++) {
            if(word.charAt(i) !='.' && word.charAt(i) !=s.charAt(i)) {
                return false;
            }
        }
        return true;
    }


}
