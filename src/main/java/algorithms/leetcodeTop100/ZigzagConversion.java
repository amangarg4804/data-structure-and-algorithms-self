package algorithms.leetcodeTop100;

public class ZigzagConversion {

  public static void main(String[] args) {
    System.out.println(convert("PAYPALISHIRING", 3));
    System.out.println(convert("PAYPALISHIRING", 4));
  }

  public static String convert(String s, int numRows) {
    // time O(n^2)
    if (numRows == 0) {
      return s;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < numRows; i++) {
      int increment = numRows * 2 - 2;
      for (int j = i; j < s.length(); j += increment) {
        sb.append(s.charAt(j));
        if (i > 0 && i < numRows - 1 && j + increment - (2 * i) < s.length()) {
          sb.append(s.charAt(j + increment - (2 * i)));
        }
      }
    }
    return sb.toString();
  }

  public String convertMethod2(String s, int numRows) {
    // time O(n)
    if (numRows == 0) {
      return s;
    }
    StringBuilder[] sb = new StringBuilder[numRows];

    for (int i = 0; i < numRows; i++) {
      sb[i] = new StringBuilder();
    }

    int index = 0;
    while (index < s.length()) {
      // down
      for (int j = 0; j < numRows && index < s.length(); j++) {
        sb[j].append(s.charAt(index++));
      }
      // up excluding zero
      for (int j = numRows - 2; j > 0 && index < s.length(); j--) {
        sb[j].append(s.charAt(index++));
      }
    }

    StringBuilder result = new StringBuilder();
    for (StringBuilder each : sb) {
      result.append(each);
    }
    return result.toString();
  }
}
