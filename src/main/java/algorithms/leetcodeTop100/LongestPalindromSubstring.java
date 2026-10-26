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

  // Approach 3: Expand From Centers
  //Intuition
  //
  //In the first approach, the palindrome check cost O(n). In the second approach, the palindrome check cost O(1). This allowed us to improve the time complexity from O(n
  //3
  // ) to O(n
  //2
  // ).
  //
  //The problem with the second approach is that we always iterated over O(n
  //2
  // ) states of i, j. Can we optimize further to minimize the number of iterations required?
  //
  //In the first approach, we implemented a palindrome check using two pointers. We started by checking the first and last characters, then the second and second last characters, and so on.
  //
  //Instead of starting the pointers at the edges and moving inwards, the same logic can be applied when starting the pointers at the center and moving outwards. A palindrome mirrors around its center. Let's say you had s = "racecar". If we start both pointers at the middle ("e") and move them away from each other, we can see that at every iteration, the characters match: e -> c -> a -> r.
  //
  //The previous two approaches focused on the bounds of a substring - i, j. There are O(n
  //2
  // ) bounds, but only O(n) centers. For each index i, we can consider odd-length palindromes by starting the pointers at i, i. To consider the even length palindromes, we can start the pointers at i, i + 1. There are n starting points for the odd-length palindromes and n−1 starting points for the even-length palindromes - that's 2n−1=O(n) starting points in total.
  //
  //This is very promising - we can lower the minimum iterations required if we focus on the centers instead of on the bounds. Let's use a helper method expand(i, j) that starts two pointers left = i and right = j. In this method, we will consider i, j as a center. When i == j, we are considering odd-length palindromes. When i != j, we are considering even-length palindromes. We will expand from the center as far as we can to find the longest palindrome, and then return the length of this palindrome.
  //
  //Let's say that we have a center i, i. We call expand and find a length of length. What are the bounds of the palindrome? Because we are centered at i, i, it means length must be odd. If we perform floor division of length by 2, we will get the number of characters dist on each side of the palindrome. For example, given s = "racecar", we have length = 7 and dist = 7 / 2 = 3. There are 3 characters on each side - "rac" on the left and "car" on the right. Therefore, we can determine that the bounds of the palindrome are i - dist, i + dist.
  //
  //What about a center at i, i + 1? length must be even now. If we have a palindrome with length 2, then length / 2 = 1, but there are zero characters on each side of the center. We can see that dist is too large by 1. Therefore, we will calculate dist as (length / 2) - 1 instead. Now, dist correctly represents the number of characters on each side. The bounds of the palindrome are i - dist, i + 1 + dist.
  //
  //Algorithm
  //
  //Create a helper method expand(i, j) to find the length of the longest palindrome centered at i, j.
  //Set left = i and right = j.
  //While left and right are both in bounds and s[left] == s[right], move the pointers away from each other.
  //The formula for the length of a substring starting at left and ending at right is right - left + 1.
  //However, when the while loop ends, it implies s[left] != s[right]. Therefore, we need to subtract 2. Return right - left - 1.
  //Initialize ans = [0, 0]. This will hold the inclusive bounds of the answer.
  //Iterate i over all indices of s.
  //Find the length of the longest odd-length palindrome centered at i: oddLength = expand(i, i).
  //If oddLength is the greatest length we have seen so far, i.e. oddLength > ans[1] - ans[0] + 1, update ans.
  //Find the length of the longest even-length palindrome centered at i: evenLength = expand(i, i + 1).
  //If evenLength is the greatest length we have seen so far, update ans.
  //Retrieve the answer bounds from ans as i, j. Return the substring of s starting at index i and ending with index j.
  public String longestPalindrome3(String s) {
    // oct 2026
    System.out.println(s);
    int[] indices = new int []{0,0};
    int maxLength = 0;
    for (int i =0 ; i< s.length() ; i++) {
      int oddLength = expandPalindrome(s, i, i);
      if(oddLength > maxLength) {
        System.out.println("oddLength = " + oddLength);

        // aba
        // i=1
        // length = 3
        // indices should be 0, 2

        // aaba
        // i=2
        // length = 3
        // indices should be 1, 3

        // If we perform floor division of length by 2, we will get the number of characters dist on each side of the palindrome.
        int dist = oddLength /2;
        indices[0] = i-dist;
        indices[1] =i + dist;
        maxLength = oddLength;
      }

      int evenLength = expandPalindrome(s, i, i+1);
      if(evenLength > maxLength) {
        // abba
        // i = 1

        // bb
        // i=1
        //  If we have a palindrome with length 2, then length / 2 = 1, but there are zero characters on each side of the center. We can see that dist is too large by 1. Therefore, we will calculate dist as (length / 2) - 1 instead.
        // center is i,i+1 together
        System.out.println("evenLength = " + evenLength);

        int dist = evenLength/2 -1;
        indices[0] = i-dist;
        indices[1] =i+1+dist;
        maxLength = evenLength;
      }
    }

    return s.substring(indices[0], indices[1]+1);
  }

  private int expandPalindrome(String s, int left, int right) {
    while(left<=right) {
      if(left<0 || right >=s.length() || s.charAt(left) != s.charAt(right)) {
        break;
      }
      left--;
      right++;
    }
    // left =0, right =2 length is generally  right-left + 1 = 3
    // but before breaking the loop we did left-- and right ++, so we need to subtract 2
    return right -left -1;
  }
  //Given n as the length of s,
  //Time complexity: O(n2)
  //There are 2n−1=O(n) centers. For each center, we call expand, which costs up to O(n).
  //Although the time complexity is the same as in the DP approach, the average/practical runtime of the algorithm is much faster.
  // This is because most centers will not produce long palindromes, so most of the O(n) calls to expand will cost far less than n iterations.
  //The worst case scenario is when every character in the string is the same.
  //Space complexity: O(1)
  //We don't use any extra space other than a few integers. This is a big improvement on the DP approach.
}
