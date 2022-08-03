package algorithms.leetcodeTop100;

public class CountAndSay {

  public String countAndSay(int n) {
    if (n == 1) {
      return "1";
    }
    String s = countAndSay(n - 1);
    int nextIndex = 1;
    int currentNumber = s.charAt(0) - '0';
    int count = 1;
    StringBuilder sb = new StringBuilder();
    while (nextIndex < s.length()) {
      if (s.charAt(nextIndex) - '0' == currentNumber) {
        count++;
      } else {
        sb.append(count).append(currentNumber);
        currentNumber = s.charAt(nextIndex) - '0';
        count = 1;
      }
      nextIndex++;
    }
    sb.append(count).append(currentNumber);
    return sb.toString();
  }
}
