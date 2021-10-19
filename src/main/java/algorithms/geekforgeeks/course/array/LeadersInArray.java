package algorithms.geekforgeeks.course.array;

import java.util.ArrayList;

public class LeadersInArray {

  static ArrayList<Integer> leaders(int[] arr, int n) {
    // Your code here

    int greatestRight = 0;
    ArrayList<Integer> resultList = new ArrayList<>();
    for (int i = n - 1; i >= 0; i--) {
      if (arr[i] >= greatestRight) {
        greatestRight = arr[i];
        resultList.add(0, arr[i]);
        // Could also do Collections.reverse
        // Could also use another arraylist
      }
    }
    return resultList;
  }

  static ArrayList<Integer> leadersMethod2(int[] arr, int n) {
    // More efficient than previous
    // time = O (n), space = O(n)
    int greatestRight = 0;
    ArrayList<Integer> resultList = new ArrayList<>();
    for (int i = n - 1; i >= 0; i--) {
      if (arr[i] >= greatestRight) {
        greatestRight = arr[i];
        resultList.add(arr[i]);
        // Could also do Collections.reverse
        // Could also use another arraylist
      }
    }
    reverse(resultList, 0, resultList.size() - 1);
    return resultList;
  }

  private static void reverse(ArrayList<Integer> resultList, int left, int right) {
    while (left < right) {
      Integer temp = resultList.get(right);
      resultList.set(right, resultList.get(left));
      resultList.set(left, temp);
      left++;
      right--;
    }
  }

}
