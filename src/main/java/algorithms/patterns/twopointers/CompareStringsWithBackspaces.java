package algorithms.patterns.twopointers;

// Given two strings containing backspaces (identified by the character ‘#’), check if the two strings are equal.
public class CompareStringsWithBackspaces {

  public static void main(String[] args) {
    System.out.println(compare("xy#z", "xzz#"));
    System.out.println(compare("xy#z", "xyz#"));
    System.out.println(compare("xp#", "xyz##"));
    System.out.println(compare("xywrrmp", "xywrrmu#p"));
  }

  public static boolean compare(String str1, String str2) {
    int str1Index = str1.length() -1;
    int str2Index = str2.length() -1;

    while (str1Index >= 0 && str2Index >= 0) {
      int backspacesStr1 = 0;
      int backspacesStr2 = 0;
      while(str1Index >= 0 && str1.charAt(str1Index) == '#') {
        str1Index--;
        backspacesStr1++;
      }
      while(backspacesStr1 > 0) {
        str1Index--;
        backspacesStr1--;
      }
      while(str2Index >= 0 && str2.charAt(str2Index) == '#') {
        str2Index--;
        backspacesStr2++;
      }
      while(backspacesStr2 > 0) {
        str2Index--;
        backspacesStr2--;
      }

      if(str1Index >=0 && str2Index >=0) {
        char currentCharStr1 = str1.charAt(str1Index);
        char currentCharStr2 = str2.charAt(str2Index);
        if(currentCharStr1 != '#' && currentCharStr2 != '#' ) {
          if(currentCharStr1 != currentCharStr2) {
            return false;
          }
          str1Index--;
          str2Index--;
        }
      }
    }
    if(str1Index >=0 || str2Index >=0) {
      return false;
    }
    return true;
  }
}
