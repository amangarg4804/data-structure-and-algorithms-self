package algorithms.leetcodeBeginner;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote.length() > magazine.length()) {
            return false;
        }
        Map<Character, Integer> map= new HashMap<>(); // constant space because it is given that magazine can contain only lowercase English alphabets (26)
        for(int i=0; i< magazine.length();i++) {
            map.put(magazine.charAt(i), map.getOrDefault(magazine.charAt(i), 0) +1);
        }

        for(int i=0; i< ransomNote.length() ;i++) {
            if(!map.containsKey(ransomNote.charAt(i)) || map.get(ransomNote.charAt(i))==0) { // could avoid first check.
                return false;
            }
            map.put(ransomNote.charAt(i), map.get(ransomNote.charAt(i))-1);
        }
        return true;
    }
}
