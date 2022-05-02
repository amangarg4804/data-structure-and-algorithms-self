package algorithms.leetcodeTop100;

public class PalindromeNumber {
  public boolean isPalindrome(int x) {
    if(x < 0) {
      return false;
    }
    int reversed = reverse(x);
    return reversed ==x;
  }

  public int reverse(int x) {
    int reversed = 0;

    while (x != 0) {
      int digit = x %10;
      x = x/10;
      if(reversed >Integer.MAX_VALUE/10  ||  reversed==Integer.MAX_VALUE/10 && digit >7)  {
        return 0;
      }
      if(reversed < Integer.MIN_VALUE/10  ||  reversed==Integer.MIN_VALUE/10 && digit < -8)  {
        return 0;
      }
      reversed *=10;
      reversed +=digit;

    }
    return reversed;
  }
}
