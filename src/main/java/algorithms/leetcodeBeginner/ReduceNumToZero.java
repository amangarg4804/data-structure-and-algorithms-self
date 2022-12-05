package algorithms.leetcodeBeginner;

public class ReduceNumToZero {

    public int numberOfSteps(int num) {
        int steps =0;
        while(num !=0) {
            if(num%2==0) {
                num =num/2;

            } else {
                num--;
            }
            steps++;
        }
        return steps;
    }

    public int numberOfStepsBitwise(int num) {
        int steps =0;
        while(num !=0) {
            if((num & 1)==0) { // num = 00000111, bitmask =0000001 or 1 NOTE: bitwise & with 1  will be 0 for numbers that are divisible by 2
                                // NOTE: Odd number in binary always has right most bit as 1. 1 & 1 =1 . 1& 0= 0
              num = num>>1; // NOTE: right shift 1 time to divide by 2
            } else {
                num--;
            }
            steps++;
        }
        return steps;
    }
}
