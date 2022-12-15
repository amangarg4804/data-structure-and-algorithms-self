package algorithms.leetcodearraysandstrings;


public class PlusOne {

    public int[] plusOne(int[] digits) {

        int carry =1;
        for( int i = digits.length -1; i>=0; i--) {
            int current = digits[i] +carry;
            digits[i] = current%10;
            carry = current/10;
        }
        if (carry > 0) {
            int[] result = new int[digits.length+1];
            result [0] = carry;
            for(int i=0; i< digits.length; i++) {
                result[i+1] = digits[i];
            }
            return result;
        }
        return digits;
    }

    public int[] plusOne1(int[] digits) {
        for( int i = digits.length -1; i>=0; i--) {

            if(digits[i]<9) {
                digits[i]++;
                return digits; // if any digit is less than 9, just add 1 to it and return array, we don't need to create another array in this case
            } else {
                digits[i] =0; // if digit is 9 adding 1 to it will result in 0
            }

        }
        int[] result = new int[digits.length+1]; // code execution coming here means we didn't return from line 30, this can happen when input array had all nines- 9999
        result[0] =1;
        return result;


    }

}
