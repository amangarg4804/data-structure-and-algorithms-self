package algorithms.interview_question.amazon_interview_questions;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {
  static HashMap<Character, Character> map = new HashMap<>();
  public static boolean isValidParentheses(String string) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < string.length(); i++) {
      if(!map.containsKey(string.charAt(i))) {
        if("{[(".contains(string.charAt(i) + "")) {
          stack.push(string.charAt(i));
        }
      } else {
        char topElement = stack.empty() ? '#' : stack.pop();
        if(!map.get(string.charAt(i)).equals(topElement)) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    map.put('}', '{');
    map.put(']', '[');
    map.put(')', '(');
    String s = "{[1234]}{56}";
    System.out.println(isValidParentheses(s));
  }
}
