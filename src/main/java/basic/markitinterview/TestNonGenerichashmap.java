package basic.markitinterview;

import java.util.HashSet;
import java.util.Set;

public class TestNonGenerichashmap {
    public static void main(String[] args) {
      Set s = new HashSet<>();
      s.add(new Long(10));
      s.add(new Integer(10));
      System.out.println(s);
    }
  }
