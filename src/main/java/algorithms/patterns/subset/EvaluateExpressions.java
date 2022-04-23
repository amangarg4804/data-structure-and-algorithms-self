package algorithms.patterns.subset;

import java.util.ArrayList;
import java.util.List;

// Given an expression containing digits and operations (+, -, *),
// find all possible ways in which the expression can be evaluated by grouping the numbers and operators using parentheses.
public class EvaluateExpressions {

  public static void main(String[] args) {
    System.out.println(evaluate("1+2*3"));
    System.out.println(evaluate("2*3-4-5"));
  }

  private static List<Integer> evaluate(String str) {
    List<Integer> result = new ArrayList<>();
    if (!str.contains("+") && !str.contains("-") && !str.contains("*")) {
      result.add(Integer.valueOf(str));
    } else {
      for (int i = 0; i < str.length(); i++) {
        if (!Character.isDigit(str.charAt(i))) {
          List<Integer> leftParts = evaluate(str.substring(0, i));
          List<Integer> rightParts = evaluate(str.substring(i + 1));
          for (int left : leftParts) {
            for (int right : rightParts) {
              if (str.charAt(i) == '+') {
                result.add(left + right);
              } else if (str.charAt(i) == '-') {
                result.add(left - right);
              } else if (str.charAt(i) == '*') {
                result.add(left * right);
              }
            }
          }
        }
      }
    }
    return result;
  }
}
