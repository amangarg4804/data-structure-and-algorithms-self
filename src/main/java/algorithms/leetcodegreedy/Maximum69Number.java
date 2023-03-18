package algorithms.leetcodegreedy;

//You are given a positive integer num consisting only of digits 6 and 9.
//
//Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
//
//
//
//Example 1:
//
//Input: num = 9669
//Output: 9969
//Explanation:
//Changing the first digit results in 6669.
//Changing the second digit results in 9969.
//Changing the third digit results in 9699.
//Changing the fourth digit results in 9666.
//The maximum number is 9969.
public class Maximum69Number {

    public int maximum69Number (int num) {
        int[] digits = new int[5]; // 1 <= num <= 10000
        int index =4;
        while (num >0)  {
            digits[index--] = num%10;
            num = num/10;
        }
        int result=0;
        boolean changed = false;
        for(int i=index+1; i< digits.length; i++) { // index would be -1 if number was of length 5. Index would be 2 if number was of length 3. So, we start iterating from index+1 to avoid trailing zeros
            if(digits[i]==6 && !changed) {
                result = (result*10)+9;
                changed=true;
            } else {
                result = result*10 + digits[i];
            }

        }
        return result;
    }
}
