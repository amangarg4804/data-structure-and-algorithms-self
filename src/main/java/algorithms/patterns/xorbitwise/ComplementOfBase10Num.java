package algorithms.patterns.xorbitwise;

public class ComplementOfBase10Num {

  public static void main(String[] args) {
    System.out.println(bitwiseComplement(8));
    System.out.println(bitwiseComplement(10));
  }

  private static int bitwiseComplement(int num) {

    int noOfBits = 0;
    int n = num;
    while (n> 0) {
      noOfBits++;
      n = n >> 1;
    }
    int allBitsSet = (int)Math.pow(2, noOfBits) - 1;
    return allBitsSet ^ num;
    // time O(b), b is no of bits. Space O(1)
  }
}
