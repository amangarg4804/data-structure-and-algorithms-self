package algorithms.leetcodehashtable;

import java.util.HashMap;
import java.util.Map;

// Two strings s and t are isomorphic if the characters in s can be replaced to get t.
//Input: s = "egg", t = "add"
//Output: true
//Input: s = "foo", t = "bar"
//Output: false
//Input: s = "foo", t = "bar"
//Output: false
public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) { // it is given that s and t are of same length so no need to check length
        Map<Character, Character> sTot = new HashMap<>();
        Map<Character, Character> toTos = new HashMap<>();
        for(int i=0; i<s.length();i++) {
            if(sTot.containsKey(s.charAt(i)) && sTot.get(s.charAt(i)) !=t.charAt(i)) {
                return false;
            }
            if(toTos.containsKey(t.charAt(i)) && toTos.get(t.charAt(i)) !=s.charAt(i)) {
                return false;
            }
            sTot.put(s.charAt(i), t.charAt(i));
            toTos.put(t.charAt(i), s.charAt(i));
        }
        return true;
    }

    public boolean isIsomorphic1(String s, String t) { // it is given that s and t are of same length so no need to check length
        // using one map, call the function twice interchanging values of s and t
        return isIsomorphicHelper(s, t) && isIsomorphicHelper(t, s);
    }

    public boolean isIsomorphicHelper(String s, String t) {
        Map<Character, Character> sTot = new HashMap<>();
        for(int i=0; i<s.length();i++) {
            if(sTot.containsKey(s.charAt(i)) && sTot.get(s.charAt(i)) !=t.charAt(i)) {
                return false;
            }
            sTot.put(s.charAt(i), t.charAt(i));
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new IsomorphicStrings().isIsomorphic("paper", "title"));
    }
}
