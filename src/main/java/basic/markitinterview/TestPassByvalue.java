package basic.markitinterview;

public class TestPassByvalue {
    public static void main(String[] args) {
      String value = "abc";
      method(value);
      System.out.println(value);
    }
    public static void method(String s) {
      s = "xyz";
    }
  }
