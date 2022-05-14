package algorithms.patterns.xorbitwise;

// In a non-empty array of integers, every number appears twice except for one, find that single number.
public class SingleNumber {

  public static void main(String[] args) {
    System.out.println(singleNumber(new int[]{1, 1, 2, 2, 5, 6, 6}));
  }

  private static int singleNumber(int[] arr) {
    // time O(n) space O(0)
    int x1 = arr[0];
    for(int i = 1; i< arr.length ;i++) {
      x1 = x1^arr[i];
    }
    return x1;
  }

  // Other solution could be to use a hashmap and add keys. Remove a key if it already exists in map. In the end, we will be left with only single key
}
