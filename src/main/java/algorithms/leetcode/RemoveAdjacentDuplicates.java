package algorithms.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class RemoveAdjacentDuplicates {

  public static void main(String[] args) {

  }

  public String removeDuplicates(String s, int k) {
    StringBuilder sb = new StringBuilder();
    Deque<Pair> stack = new LinkedList<>();

    char[] inputCharacters = s.toCharArray();

    for(char c: inputCharacters) {

      if(stack.isEmpty()) {
        stack.addFirst(new Pair(c, 1));
        continue;
      }

      if(stack.peekFirst().character !=c) {
        stack.addFirst(new Pair(c, 1));
        continue;
      }

      if(stack.peekFirst().character ==c) {
        if(stack.peekFirst().characterCount == k-1) {
          stack.pollFirst();
        } else {
          Pair topOfStackPair = stack.peekFirst();
          topOfStackPair.characterCount++;
        }
      }
    }

    while (!stack.isEmpty()) {
      Pair pair = stack.pollLast();
      for(int i=0; i<pair.characterCount; i++) {
        sb.append(pair.character);
      }
    }
    return sb.toString();
  }

}

class Pair {
  char character;
  int characterCount;

  public Pair(char character, int characterCount) {
    this.character = character;
    this.characterCount = characterCount;
  }
}