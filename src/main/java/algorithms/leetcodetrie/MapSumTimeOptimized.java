package algorithms.leetcodetrie;

import java.util.HashMap;
import java.util.Map;

public class MapSumTimeOptimized {
    Map<String, Integer> map = new HashMap<>();
    TrieNode root = new TrieNode();

    public void insert(String key, int val) {
        Integer existing = map.get(key);
        int diff;
        if(existing!=null) {
            diff = val - existing; // difference between current value and already existing value, can be negative or positive or 0
        } else {
            diff = val;
        }
        TrieNode current = root;
        for(int i=0; i< key.length(); i++) {
            int index = key.charAt(i) -'a';
            if(current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current.children[index].value += diff; // NOTE: adding value to each character of word. If existing value was 7 and new value is 5, diff will be -2 and Value will be set as 7 -2 =5 which is equal to new value
            current = current.children[index];
        }
        map.put(key, val);
    }

    public int sum(String prefix) {
        TrieNode current = root;
        for(int i=0; i< prefix.length(); i++) {
            int index = prefix.charAt(i) -'a';
            if(current.children[index] ==null) {
                return 0;
            }
            current = current.children[index];
        }
        return current.value;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int value;
    }
}



