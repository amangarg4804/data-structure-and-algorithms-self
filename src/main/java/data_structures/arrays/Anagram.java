package data_structures.arrays;

public class Anagram {

  public static void main(String[] args) {

  }

  public static  boolean isAnagram(String value1,String value2) {
    // Solution by a candidate
    if (value1.length() != value2.length()) {
      return false;
    }

    for (int i = 0; i < value1.length(); i++) {
      char ch = value1.charAt(i);
      int index = value2.indexOf(ch);
      if (index == -1) {
        return false;
      } else if (index == 0) {
        value2 = value2.substring(index + 1, value2.length());
      } else {
        String firstStr = value2.substring(0, index);
        value2 = value2.substring(index + 1, value2.length());
        value2 = firstStr + value2;
      }
    }

    return true;
  }
}
