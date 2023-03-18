package algorithms.leetcodegreedy;

import java.util.Arrays;

//You are given a positive integer num consisting of exactly four digits. Split num into two new integers new1 and new2 by using the digits found in num.
// Leading zeros are allowed in new1 and new2, and all the digits found in num must be used.
//
//For example, given num = 2932, you have the following digits: two 2's, one 9 and one 3.
// Some possible pairs [new1, new2] are [22, 93], [23, 92], [223, 9] and [2, 329].
//Return the minimum possible sum of new1 and new2.
//1000 <= num <= 9999
public class MinimumSum {
    public int minimumSum1(int num) {
        // first we find all digits and store them in array
        int[] numbers = new int[4];
        int index = 0;
        // 67/10 = 6, 67%10 = 7
        //670/10 = 67, 670%10 =0
        while (num > 0) {
            numbers[index++] = num % 10;
            num = num / 10;
        }
        // sort the digits
        Arrays.sort(numbers);
        // For both the number to have minimum sum, both num1 and num2 should be minimum possible numbers
        //num1 should start with 1st smallest digit
        // num2 should start with 2nd smallest digit
        // num1 should be appended with 3rd smallest digit
        // num2 should be appended with 4th smallest digit
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < numbers.length; i++) {
            num1 = num1 * 10 + numbers[i];
            //we swap num1 and num2
            int temp = num1;
            num1 = num2;
            num2 = temp;

        }
        return num1 + num2;

    }

    public int minimumSum(int num) {
        // first we find all digits and store them in array
        int[] numbers = new int[4];
        int index = 0;
        // 67/10 = 6, 67%10 = 7
        //670/10 = 67, 670%10 =0
        while (num > 0) {
            numbers[index++] = num % 10;
            num = num / 10;
        }
        // sort the digits
        Arrays.sort(numbers);

        int num1 = numbers[0] * 10 + numbers[2];
        int num2 = numbers[1] * 10 + numbers[3];
        return num1 + num2;

    }

}
