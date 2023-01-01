package algorithms.leetcodetrie;

//Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
// Given all numbers are >=0 (no need to worry about sign bit)
public class MaximumXor {
    TrieNode root = new TrieNode();
    public int findMaximumXOR(int[] nums) {
        for(int num : nums) {
            insertNum(num); // Time complexity: O(N*32)- every number in the array has to inserted in trie
        }

        int maximumXor = 0; // all nums are positive
        for(int num : nums) {
            maximumXor = Math.max(maximumXor, maximumXor(num)); // Maximum xor will be O(N*32)
        }
        return maximumXor;
    }
    private void insertNum(int num) {
        TrieNode current = root;
        for(int i=31 ; i>=0; i--) {
            int bit = (num>>i) & 1; // to get ith bit. Getting 31st bit first to insert. Inserting from left to right. Most significant bit will be inserted first and later compared to maximize xor
            // NOTE that we are not converting the number to binary using division by 2 and %2 method
            if(current.children[bit] ==null) {
                current.children[bit] = new TrieNode();
            }
            current = current.children[bit];
        }
        current.value = num;
    }

    private int maximumXor(int num) {
        // maximum xor will be all 1's
        // we know the xor of a number with itself is 0 and opposite bit is 1
        // So our goal is find a number with complimenting bits
        // for a number 110011, maximum xor will be 11111, complimenting number is 001100
        //Which binary number would be larger? - The one which has the most significant bit set.
        //Num1 = 28th bit is set and all others are 0
        //Num2 = 28th bit is 0 and all others are 1.
        // Num 2 is larger
        TrieNode current = root;
        int max =0;
        for(int i=31; i>=0; i--) {
            int bit = (num >> i) & 1;
            if(current.children[1-bit]!=null) { // preference is to find complimenting bit to maximum xor result NOTE: to find compliment of a bit do 1-value. 1-0 = 1, 1-1 =0
                max = max | (1<< i); // to set ith bit in num.
                current = current.children[1-bit];
            } else { // either 0 will be set or 1 will be set, because all integers are 32 bit
                // no need to set bit as it is 0 by default
                current = current.children[bit];
            }
        }
        return current.value ^ num; // either return this or max, both work
//        return max;

    }

    class TrieNode {
        TrieNode [] children = new TrieNode[2]; // 0 and 1 in binary
        int value;
        // No need to store isNum, every number will be 32 bits.
    }

    public static void main(String[] args) {
        MaximumXor maximumXor = new MaximumXor();
                maximumXor.insertNum(10);
                maximumXor.insertNum(20);
    }
}