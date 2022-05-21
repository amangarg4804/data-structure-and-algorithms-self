package algorithms.patterns.topkelements;

import java.util.PriorityQueue;

// Given an array, find the sum of all numbers between the K1’th and K2’th smallest elements of that array.
public class SumElementsBetweenTwoNums {

  public static void main(String[] args) {
    System.out.println(sum(new int[]{1, 3, 12, 5, 15, 11}, 3, 6));
    System.out.println(sum(new int[]{3, 5, 8, 7}, 1, 4));
  }

  private static int sum (int[] arr, int k1, int k2) {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>( (n1, n2) -> n2 -n1);
    for(int i = 0; i< k2; i++) {
      maxHeap.offer(arr[i]);
    }
    for(int i = k2; i< arr.length; i++) {
      if(arr[i] < maxHeap.peek()) {
        maxHeap.poll();
        maxHeap.offer(arr[i]);
      }
    }
    maxHeap.poll();
    int sum = 0;
    for(int i = k1+1 ; i< k2 ;i++) {
      sum+=maxHeap.poll();
    }
    return sum;
  }

}
