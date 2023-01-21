package algorithms.leetcodehashtable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class FirstUniqueCharacter {

    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for(int i =0; i< s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) +1);
        }

        Optional<Map.Entry<Character, Integer>> entry = map.entrySet().stream().filter(characterIntegerEntry -> characterIntegerEntry.getValue()==1)
                .findFirst();
        if(entry.isEmpty()) {
            return -1;
        }
        char c = entry.get().getKey();
        return s.indexOf(c);
    }

    public int firstUniqChar2(String s) {
        Map<Character, Integer> map = new HashMap<>(); // can also use array of size 26 in place of map
        for(int i =0; i< s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) +1);
        }

        for(int i=0; i< s.length(); i++) {
            if(map.get(s.charAt(i))==1) {
                return i;
            }
        }
        return -1;
    }

}
