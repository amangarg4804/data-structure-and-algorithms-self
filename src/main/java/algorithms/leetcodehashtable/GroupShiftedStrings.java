package algorithms.leetcodehashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
//
//"abc" -> "bcd" -> ... -> "xyz"
//Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
//
//For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], Return:
//
//[
//  ["abc","bcd","xyz"],
//  ["az","ba"],
//  ["acef"],
//  ["a","z"]
//]
//Note: For the return value, each inner list's elements must follow the lexicographic order.
public class GroupShiftedStrings {

    public List<List<String>> groupStrings(String[] strings) {
        // Notice the property of strings that belong to same shifting sequence abc xyz
        // abc - distance between a and b is 1, b and c is 1
        // xyz - distance between x and y is 1, y and z is 1
        // so, the strings belonging to same shifted sequence will have same distance between their characters
        // We can use this difference as key to group strings
        // what about the case where first character is larger than second e.g ya. In this case, we can do a -y +26

        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s: strings) {
            String key = getKey(s);
            List<String> sameSequenceStrings = map.getOrDefault(key, new ArrayList<>());
            sameSequenceStrings.add(s);
            map.put(key, sameSequenceStrings);

        }
        return new ArrayList<>(map.values());
    }

    private String getKey(String s) {
        StringBuilder key = new StringBuilder();
        for(int i=1; i< s.length(); i++) {
            char previous =s.charAt(i-1);
            char current = s.charAt(i);
            int diff = current - previous;
            if(diff < 0) {
                diff +=26;
            }
            key.append(diff).append("#"); // use any separator. This is done to distinguish between 1#2 and 12

        }
        key.append("."); // what if given string is only one character? for loop will never run. To differentiate between single char and empty string, we can do this
        return key.toString();
    }


}
