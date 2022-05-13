package algorithms.youtube;

public class CheckFibonacci {

  public static void main(String[] args) {
    System.out.println(isFibonacci(5));
  }

  private static boolean isFibonacci(int n) {
    // Binet's formula- one or both of  5n^2 + 4 and 5n^ -4 should be a perfect square
    return isPerfectSquare(5*n*n + 4) || isPerfectSquare(5*n*n -4);
  }

  private static boolean isPerfectSquare(int n) {
    int s = (int) Math.sqrt(n);
    return (s*s == n);
  }

}
