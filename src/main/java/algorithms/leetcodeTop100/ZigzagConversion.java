package algorithms.leetcodeTop100;

public class ZigzagConversion {

  public static void main(String[] args) {
    System.out.println(convert("PAYPALISHIRING", 3));
    System.out.println(convert("PAYPALISHIRING", 4));
  }
  public static String convert(String s, int numRows) {
    if(numRows ==0) {
      return s;
    }
    StringBuilder sb = new StringBuilder();
    for(int i =0 ; i< numRows; i++ ) {
      int increment  = numRows*2 -2;
      for(int j = i; j< s.length(); j += increment) {
        sb.append(s.charAt(j));
        if(i >0 && i< numRows -1 && j + increment -(2*i) < s.length()) {
          sb.append(s.charAt(j + increment -(2*i) ));
        }
      }
    }
    return sb.toString();
  }
}
