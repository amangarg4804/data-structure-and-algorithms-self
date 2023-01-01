package algorithms.leetcodetrie;

import java.util.HashSet;
import java.util.Set;

public class MaximumXorWithoutTrie {

    public int findMaximumXOR(int[] nums) {
        int maxXor = 0;
        int mask = 0;
        for(int i=31; i>=0; i--) {
            mask = mask | (1<<i); //setting ith bit. Mask values will be like 1000...00, 11000..00, 11100..00 and it the end it will be all 1's 11111
            Set<Integer> set = new HashSet<>(); // This set will contain i MSB values of numbers
            for(int num : nums) {
                set.add(num & mask);
                // num & mask will give us i MSBs of a number leaving other bits as null. For numbers 1111, 1100, 0100, 0010, and i=2, num& mask values will be 1100, 1100, 0100, 0000
            }
            int maxPossibleXor = maxXor | (1<<i); // What is the maxXor possible for this iteration?
            // It is previous value of maxXor with ith bit set as 1. If previous xor was 0100, for i=1 (0-based), for i=2, max xor can be 0110. For 1100, it will be 1110
            // Now we need to find whether two numbers exists in array that have xor equal to maxPossibleXor
            // such that maxPossibleXor = num1 ^num2
            // If we consider num1 in array. We can search for num2 in hashset such that num2 = maxPossibleXor ^ num1
            // Xor  is Associative. Just like addition and multiplication
            // (a^b)^c = a^(b^c)
            for(int num1 : set) {
                int num2 = maxPossibleXor ^ num1;
                if(set.contains(num2)) {
                    maxXor = maxPossibleXor;
                    break;
                }
            }
        }
        return maxXor;
    }
}
