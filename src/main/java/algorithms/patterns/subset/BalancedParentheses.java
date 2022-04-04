package algorithms.patterns.subset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BalancedParentheses {

  public static void main(String[] args) {
    System.out.println(generateParentheses(2));
    System.out.println(generateParentheses(3));
  }

  private static List<String> generateParentheses(int num) {
    List<String> result = new ArrayList<>();
    Queue<ParenthesesString> queue = new LinkedList<>();
    queue.offer(new ParenthesesString("", 0, 0));
    while (!queue.isEmpty()) {

      ParenthesesString parenthesesString = queue.poll();
      if (parenthesesString.openCount < num) {
        queue.offer(new ParenthesesString(parenthesesString.str + "(", parenthesesString.openCount + 1,
            parenthesesString.closeCount));
      }
      if (parenthesesString.closeCount < parenthesesString.openCount) {
        queue.offer(new ParenthesesString(parenthesesString.str + ")", parenthesesString.openCount,
            parenthesesString.closeCount + 1));
      }

      if (parenthesesString.closeCount == num) {
        result.add(parenthesesString.str);
      }
    }
    return result;
  }


}

class ParenthesesString {

  String str;
  int openCount;
  int closeCount;

  public ParenthesesString(String str, int openCount, int closeCount) {
    this.str = str;
    this.openCount = openCount;
    this.closeCount = closeCount;
  }

  public String getStr() {
    return str;
  }

  public void setStr(String str) {
    this.str = str;
  }

  public int getOpenCount() {
    return openCount;
  }

  public void setOpenCount(int openCount) {
    this.openCount = openCount;
  }

  public int getCloseCount() {
    return closeCount;
  }

  public void setCloseCount(int closeCount) {
    this.closeCount = closeCount;
  }
}