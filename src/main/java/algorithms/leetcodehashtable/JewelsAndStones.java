package algorithms.leetcodehashtable;

import java.util.HashSet;
import java.util.Set;

public class JewelsAndStones {
    public int numJewelsInStones(String jewels, String stones) {
        int count = 0;
        Set<Character> set = new HashSet<>(); // can also use array of 128 characters
        for(int i=0; i< jewels.length(); i++) {
            set.add(jewels.charAt(i));
        }

        for(int i=0; i< stones.length(); i++) {
            if(set.contains(stones.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}
