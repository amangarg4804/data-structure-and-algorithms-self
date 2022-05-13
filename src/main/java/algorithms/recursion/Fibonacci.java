package algorithms.recursion;

public class Fibonacci {
  // Given a number N return the index value of the Fibonacci sequence, where the sequence is:

// 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144 ...
// the pattern of the sequence is that each value is the sum of the 2 previous values, that means that for N=5 → 2+3

//For example: fibonacciRecursive(6) should return 8

  static int num = 0;
  public static int fibonacciRecursively(int n) { //O(2^n)
    if(n < 2) {
      return n;
    }
    return  fibonacciRecursively(n - 1) + fibonacciRecursively(n - 2);
  }

  static int fibonacciRecursivelyEfficient(int n, int val, int prev) { //O(n)
    if (n == 0) {
      return prev;
    }
    if (n == 1) {
      return val;
    }
    return fibonacciRecursivelyEfficient(n - 1, val + prev, val);
  }

  public static int fibonacciIteratively(int n) { //O(n)
    if(n < 2) {
      return n;
    }
    int previousMinusOne = 0;
    int previous = 1;
    for(int i =2 ; i<= n; i++) {
      int temp = previous;
      previous = previousMinusOne + previous; // 1
      previousMinusOne = temp; // 1

    }
    return previous;
  }

  public static void main(String[] args) {
    num = 0;
    System.out.println(fibonacciIteratively(10));
    System.out.println(fibonacciRecursivelyEfficient(50, 1 , 0));
    System.out.println(fibonacciRecursively(50));
  }

}
