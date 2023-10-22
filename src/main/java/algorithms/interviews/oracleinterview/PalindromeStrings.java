package algorithms.interviews.oracleinterview;

public class PalindromeStrings {

  public static void main(String[] args) {
    // An array of string, which of them are palindrome
    findPalindromes(new String[] { "aba", "abba", "abc"});

  }


  public static void findPalindromes(String [] inputs ) {
    if (inputs ==null ) {
      return;
    }
     for(String input : inputs) {
      if(isPalindrome(input)) {
        System.out.println(input);
      }
    }
  }

  public static boolean isPalindrome(String input) {
    if(input == null) {
      return false;
    }
    int start = 0;
    int end = input.length() -1;

    while(start < end) {
      if(input.charAt(start) != input.charAt(end)) {
        return false;
      }
      start++;
      end--;
    }

    return true;
  }

}
