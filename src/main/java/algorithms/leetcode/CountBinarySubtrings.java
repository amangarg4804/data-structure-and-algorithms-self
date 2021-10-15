package algorithms.leetcode;

public class CountBinarySubtrings {

  public int countBinarySubstrings(String s) {
    int[] group = new int[s.length()];
    int currentGroupIndex=0;

    group[0]=1;
    for(int i=1; i< s.length(); i++) {
      if(s.charAt(i-1)==s.charAt(i)) {
        group[currentGroupIndex]++;
      } else {
        currentGroupIndex++;
        group[currentGroupIndex]=1;
      }
    }
    int count=0;
    for(int i=1; i< group.length;i++) {
      count+=Math.min(group[i-1], group[i]);
    }
    return count;
  }

}
