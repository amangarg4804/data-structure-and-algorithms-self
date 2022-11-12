package algorithms.leetcodeTop100;

// String to integer
public class Atoi {

  public int myAtoi(String s) {
    if (s == null || s.equals("")) {
      return 0;
    }
    int i = 0;
    int sign = 1;
    while (i < s.length() && s.charAt(i) == ' ') {
      i++;
    }
    System.out.println(i + " " + s.length());
    if (i < s.length() && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
      if (s.charAt(i) == '-') {
        sign = -1;
      }
      i++;
    }
    while (i < s.length() && s.charAt(i) == '0') {
      i++;
    }

    if (i >= s.length()) {
      return 0;
    }
    int result = 0;
    while (i < s.length() && Character.isDigit(s.charAt(i))) {
      int currentInt = s.charAt(i) - '0'; //NOTE: Converting character to integer by subtracting  '0' from it
      if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10
          && currentInt > Integer.MAX_VALUE % 10)) {
        return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
      result = result * 10 + currentInt;
      i++;
    }
    return result * sign;

  }
}
