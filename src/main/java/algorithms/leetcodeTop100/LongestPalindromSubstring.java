package algorithms.leetcodeTop100;

public class LongestPalindromSubstring {

  public static void main(String[] args) {

  }

  public static String longestPalindrome(String s) {
    // time O(n^3)
    int left = 0;
    int right = 0;
    int max = 0;
    for(int i =0 ; i< s.length(); i++) {
      for (int j=0; j< s.length(); j++) {
        if(isPalindrome(s, i, j)) {
          if(max < j-i) {
            max = j-i;
            left = i;
            right = j;
          }
        }
      }
    }
    return s.substring(left, right+1);
  }

  private static boolean isPalindrome (String s, int start, int end) {
    while(start<end) {
      if(s.charAt(start) != s.charAt(end)) {
        return false;
      }
      start++;
      end--;
    }
    return true;
  }

  public static String longestPalindromeOptimized(String s) {
    // time O(n^2)
    if(s==null || s.length() <1) {
      return "";
    }
    int left = 0;
    int right = 0;
    for(int i = 0; i< s.length(); i++) {
      int len1 = expandFromMiddle(s, i, i);
      int len2 = expandFromMiddle(s, i, i+1);
      int len = Math.max(len1, len2);
      if(right-left +1 < len) {
        left = i - ((len-1) /2); //  caac
        right = i+ (len/2);
      }
    }
    return s.substring(left, right +1); //NOTE: in a substring , first index is inclusive and 2nd index is exclusive. "hamburger".substring(4, 8) returns "urge"
  }

  private static int expandFromMiddle(String s, int left, int right) {
    if(s==null || left>right) {
      return 0;
    }

    while(left>=0 && right <s.length() && s.charAt(left) == s.charAt(right)) {
      left--;
      right++;
    }

    return right -left -1;
  }
}
