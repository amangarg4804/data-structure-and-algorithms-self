package algorithms.geekforgeeks.course.array;

import java.util.ArrayList;

public class PlusOne {
  static ArrayList<Integer> increment(ArrayList<Integer> arr , int N) {
    // code here
    int carry = 1;
    for(int i=N-1; i >=0 ; i--) {
      int current = arr.get(i);
      int sum = current + carry;
      int resultDigit = sum%10;
      if(sum > 9) {
        carry = sum/10;
      } else {
        carry=0;
      }
      arr.set(i, resultDigit);
    }
    if(carry==1) {
      arr.add(0, 1);
    }
    return arr;
  }

}
