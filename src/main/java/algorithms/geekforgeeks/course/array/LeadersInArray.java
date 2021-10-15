package algorithms.geekforgeeks.course.array;

import java.util.ArrayList;

public class LeadersInArray {

  static ArrayList<Integer> leaders(int arr[], int n){
    // Your code here

    int greatestRight = 0;
    ArrayList<Integer> resultList = new ArrayList<>();
    for(int i = n-1; i>= 0; i--) {
      if(arr[i] >= greatestRight) {
        greatestRight= arr[i];
        resultList.add(0, arr[i]);
        // Could also do Collections.reverse
        // Could also use another arraylist
      }
    }
    return resultList;
  }
}
