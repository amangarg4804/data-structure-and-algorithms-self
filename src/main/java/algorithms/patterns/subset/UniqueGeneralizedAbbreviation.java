package algorithms.patterns.subset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UniqueGeneralizedAbbreviation {

  public static void main(String[] args) {
    System.out.println(generate("BAT"));
    System.out.println(generate("code"));
  }

  private static List<String> generate(String str) {
    int wordLength = str.length();
    List<String> result = new ArrayList<>();
    Queue<AbbreviatedWord> queue = new LinkedList<>();

    queue.offer(new AbbreviatedWord(new StringBuilder(), 0, 0));

    while (!queue.isEmpty()) {
      AbbreviatedWord currentWord = queue.poll();
      if(currentWord.length==str.length()) {
        if(currentWord.count!=0) {
          currentWord.str.append(currentWord.count);
        }
        result.add(currentWord.str.toString());
      } else {
        queue.offer(new AbbreviatedWord(new StringBuilder(currentWord.str), currentWord.length+1, currentWord.count+1 ));

        if(currentWord.count !=0) {
          currentWord.str.append(currentWord.count);
        }
        queue.offer(new AbbreviatedWord(currentWord.getStr().append(str.charAt(currentWord.length)), currentWord.length+1, 0));
      }
    }
    return result;
  }
}

class AbbreviatedWord {
  StringBuilder str;
  int length;
  int count;

  public AbbreviatedWord(StringBuilder str, int length, int count) {
    this.str = str;
    this.length = length;
    this.count = count;
  }

  public StringBuilder getStr() {
    return str;
  }

  public void setStr(StringBuilder str) {
    this.str = str;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}