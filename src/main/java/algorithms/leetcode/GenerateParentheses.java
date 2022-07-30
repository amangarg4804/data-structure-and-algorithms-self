package algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
  public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    generateParenthesis("", n, result, 0, 0);
    return result;
  }

  public void generateParenthesis(String current, int n, List<String> result, int open, int close) {
    if(current.length() == 2*n) {
      result.add(current);
      return;
    }

    if(open < n) {
      generateParenthesis(current + "(", n, result, open + 1, close);
    }

    if(close < open) {
      generateParenthesis(current + ")", n, result, open, close + 1);
    }
  }
}
