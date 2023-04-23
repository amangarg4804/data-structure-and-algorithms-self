package algorithms.leetcodegreedy;

import java.util.Deque;
import java.util.LinkedList;

//Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.
//
//Example 1:
//Input: num = "1432219", k = 3
//Output: "1219"
//Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
//Example 2:
//Input: num = "10200", k = 1
//Output: "200"
//Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
//Example 3:
//Input: num = "10", k = 2
//Output: "0"
//Explanation: Remove all the digits from the number and it is left with nothing which is 0.
public class RemoveKDigits {
    public String removeKdigits1(String num, int k) {
        // code works but with TLE 42/43 test cases passed
        // k=1
        // 31 - 1
        // 13 - 1
        // 131 - 11
        // 113 - 11
        // 139 - 13
        // At each digit, we can check the lowest possible value
        if(num.length() ==k) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        int resultLength = num.length() -k;
        int i=0;
        while(i< num.length() && k!=0) {
            int digitAti = num.charAt(i) -'0';
            // is this the smallest possible digit at this index?
            int smallestDigitIndex = i;
            for(int j=i+1; j<= i+k && j< num.length(); j++) {
                int digitAtj = num.charAt(j) -'0';
                if(digitAtj < digitAti) {
                    smallestDigitIndex = j;
                    digitAti = digitAtj; // for cases with repeated digits like 5337, we should not modify smallestDigitIndex when we have already found same digit before
                }
            }
            if(smallestDigitIndex-i > 0) {
                k -= smallestDigitIndex - i;

            }
            i = smallestDigitIndex+1;
            result.append( num.charAt(smallestDigitIndex) -'0');
            if(k==0) {
                break;
            }
        }
        while (k>0) { // it is possible k was not decremented in loop, so we should delete elements from the end "112"
            result.deleteCharAt(result.length()-1);
            k--;
        }

        while (i< num.length() && result.length() < resultLength) {
            result.append(num.charAt(i++) -'0');
        }
        while (result.charAt(0) =='0' && result.length() >1) { // remove leading 0's except for the case where result itself is "0"
            result.deleteCharAt(0);
        }
        return result.toString();
    }

    public String removeKdigits2(String num, int k) {
        // code works but with TLE 42/43 test cases passed
        // k=1
        // 31 - 1
        // 13 - 1
        // 131 - 11
        // 113 - 11
        // 139 - 13
        // At each digit, we can check the lowest possible value
        if(num.length() ==k) {
            return "0";
        }
        Deque<Character> stack = new LinkedList<>();
        for(int i = 0; i < num.length() ; i++) {
            while(!stack.isEmpty() && stack.peek() > num.charAt(i) && k>0) { // while required instead of if -> "1234567890" k=9
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }
        while (k>0) { // it is possible k was not decremented in loop, so we should delete elements from the end "112"
            stack.pop();
            k--;
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        result.reverse();
        while (result.charAt(0) =='0' && result.length() >1) { // remove leading 0's except for the case where result itself is "0"
            result.deleteCharAt(0);
        }

        return result.toString();
    }
}
