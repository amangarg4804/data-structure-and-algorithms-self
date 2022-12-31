package algorithms.patterns.xorbitwise;

// Given an array of n-1 integers in the range from 1 to n, find the one number that is missing from the array.


// NOTE:
// Taking XOR of a number with itself returns 0, e.g.
//1 ^ 1 = 0
//29 ^ 29 = 0
//Taking XOR of a number with 0 returns the same number, e.g.,
//1 ^ 0 = 1
//31 ^ 0 = 31
//XOR is Associative & Commutative, which means:
//(a ^ b) ^ c = a ^ (b ^ c)
//a ^ b = b ^ a
public class MissingNumber {

  public static void main(String[] args) {
    System.out.println(findMissingNumber(new int[] {1, 2, 3, 4, 6}));
  }

  private static int findMissingNumber(int[] arr) {
    int n = arr.length + 1;
    int x1 = 1;
    for (int i = 2; i <= n; i++) {
      x1 = x1 ^ i;
    }

    int x2 = arr[0];
    for (int i = 1; i < arr.length; i++) {
      x2 = x2 ^ arr[i];
    }
    return x1 ^ x2;
  }


}
