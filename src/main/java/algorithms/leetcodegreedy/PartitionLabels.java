package algorithms.leetcodegreedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//You are given a string s. We want to partition the string into as many parts
// as possible so that each letter appears in at most one part.
//Note that the partition is done so that after concatenating all the parts in order,
// the resultant string should be s.
//Return a list of integers representing the size of these parts.
public class PartitionLabels {

    public List<Integer> partitionLabels1(String s) {
        // we store last index of each character in a map
        Map<Character, Integer> charToLastIndex = new HashMap<>();
        for(int i=0; i< s.length(); i++) {
            charToLastIndex.put(s.charAt(i),i);
        }
        List<Integer> result = new ArrayList<>();
        for(int i=0; i< s.length()  ; i++) {
            int lastIndex = charToLastIndex.get(s.charAt(i));
            //case 1: if lastIndex is same as current index. We can split the string here itself. e.g. first character of string appears only once in string
            if(lastIndex ==i) {
                result.add(1);
                continue;
            } // last index of a character can never be less than current index because it is the last index :)
            // case 2 : lastIndex of current character is greater than current index. e.g defegde
            // now we can iterate over all indexes from current index to last index to see the last index of all the characters in between is less than lastIndex
            // if we find a character with last index greater than current value of lastIndex, we should reassign lastIndex to that index
            int currentIndex = i;
            while (currentIndex < lastIndex) {
                int newlastIndex = charToLastIndex.get(s.charAt(currentIndex));
                if(newlastIndex > lastIndex) {
                    lastIndex = newlastIndex;
                }
                currentIndex++;
            }
            result.add(currentIndex-i+1);
            i=currentIndex;
        }
        return result;
    }

    public List<Integer> partitionLabels2(String s) {
        //shorter version
        // we store last index of each character in a map (we can also use char array)
        Map<Character, Integer> charToLastIndex = new HashMap<>();
        for(int i=0; i< s.length(); i++) {
            charToLastIndex.put(s.charAt(i),i);
        }
        // we store previous index of current partition. Initially previousIndex is -1. Because for string aaa, length is lastIndex - previousIndex = 2- (-1)
        int previousIndex =-1;
        int lastIndexOfCurrentPartition =0;
        List<Integer> result = new ArrayList<>();
        for(int i=0; i< s.length()  ; i++) {
            //d e f e g d e
            //0 1 2 3 4 5 6
            int lastIndexOfCurrentChar = charToLastIndex.get(s.charAt(i));
             lastIndexOfCurrentPartition = Math.max(lastIndexOfCurrentChar, lastIndexOfCurrentPartition);
             if(i == lastIndexOfCurrentPartition) {
                 result.add(lastIndexOfCurrentPartition -previousIndex);
                 previousIndex = lastIndexOfCurrentPartition;
             }
        }
        return result;
    }
}
