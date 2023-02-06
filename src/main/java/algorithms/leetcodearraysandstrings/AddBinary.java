package algorithms.leetcodearraysandstrings;

import java.util.Map;

// Given two binary strings a and b, return their sum as a binary string.
// Input: a = "11", b = "1"
//Output: "100"
public class AddBinary {
    Map<Integer , String> intToBinary = Map.of(0, "0",
            1, "1",
            2, "10",
            3, "11"); // NOTE: even binary numbers have 0 at the end and odd numbers have 1 at the end
    public String addBinary(String a, String b) {
        int aIndex = a.length()-1;
        int bIndex = b.length()-1;
        int carry = 0; // carry could be 1 and a and b could be 1, so max is 1 + 1 + 1 = 3 = 11 in binary
        StringBuilder result= new StringBuilder();
        while(aIndex>=0 || bIndex>=0) {  // can add || carry !=0 to avoid carry check at the end
            int aVal = aIndex >=0 ? a.charAt(aIndex) -'0': 0;
            int  bVal = bIndex >=0 ? b.charAt(bIndex) - '0': 0;
            int sum = aVal+bVal+carry;
            String sumBinary = intToBinary.get(sum);
            result.insert(0, sumBinary.charAt(sumBinary.length()-1)); // another approach could be to append at end and reverse the result later
            carry = sum >1? 1: 0;
            aIndex--;
            bIndex--;
        }
        if(carry>0) {
            result.insert(0, carry);
        }
        return result.toString();
    }

    public String addBinary1(String a, String b) {
        // without using map
        // Only 3 possibilities for sum 1,2,3- we can use sum%2,
        int aIndex = a.length()-1;
        int bIndex = b.length()-1;
        int carry = 0; // carry could be 1 and a and b could be 1, so max is 1 + 1 + 1 = 3 = 11 in binary
        StringBuilder result= new StringBuilder();
        while(aIndex>=0 || bIndex>=0 || carry !=0) {
            int aVal = aIndex >=0 ? a.charAt(aIndex) -'0': 0;
            int  bVal = bIndex >=0 ? b.charAt(bIndex) - '0': 0;
            int sum = aVal+bVal+carry;
            result.insert(0, sum%2); // another approach could be to append at end and reverse the result later
            carry = sum/2;
            aIndex--;
            bIndex--;
        }

        return result.toString();
    }
}
