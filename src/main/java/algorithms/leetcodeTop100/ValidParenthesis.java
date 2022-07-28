package algorithms.leetcodeTop100;

import java.util.Deque;
import java.util.LinkedList;

public class ValidParenthesis {

  public static boolean isValid(String s) {
    Deque<Character> stack = new LinkedList<>();
    for(int i=0; i< s.length(); i++) {
      char currentChar = s.charAt(i);
      if("[{(".indexOf(currentChar) != -1) {
        stack.push(currentChar);
      } else if(stack.isEmpty()) {
        return false;
      } else{
        char existing = stack.pop();
        if(!((existing == '(' && currentChar ==')')
            || (existing == '{' && currentChar =='}')
            || existing == '[' && currentChar ==']')) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }
}
