package algorithms.patterns.fastslowpointers;

import java.util.HashSet;
import java.util.Set;

//Any number will be called a happy number if, after repeatedly replacing it with a number equal to the sum of the square of all of its digits,
// leads us to number ‘1’. All other (not-happy) numbers will never reach ‘1’.
// Instead, they will be stuck in a cycle of numbers which does not include ‘1’
public class HappyNumber {

  public static void main(String[] args) {
    System.out.println(find(12));
    System.out.println(find(23));

    System.out.println(findWithoutSet(12));
    System.out.println(findWithoutSet(23));
  }

  public static boolean find(int num) {
    int sum = 0;
    Set<Integer> sumSet = new HashSet<>();
    while (sum != 1) {
      sum = 0;
      while (num > 0) {
        int currentDigit = num % 10;
        num = num / 10;
        int square = currentDigit * currentDigit;
        sum += square;
      }
      if (!sumSet.add(sum)) {
        return false;
      }
      num = sum;
    }
    return true;
  }

  public static boolean findWithoutSet(int num) {
    int slow = num;
    int fast = num;
    do {
      slow = sumOfSquares(slow);
      fast = sumOfSquares(sumOfSquares(fast));
    } while (slow != fast);

    return slow == 1;
  }

  public static int sumOfSquares(int num) {
    int sum = 0;
    while (num > 0) {
      int currentDigit = num % 10;
      num = num / 10;
      int square = currentDigit * currentDigit;
      sum += square;
    }
    return sum;
  }

}
