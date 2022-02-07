package algorithms.patterns.fastslowpointers;

//We are given an array containing positive and negative numbers. Suppose the array contains a number ‘M’ at a particular index. Now, if ‘M’ is positive we will move forward ‘M’ indices and if ‘M’ is negative move backwards ‘M’ indices. You should assume that the array is circular which means two things:
//
//    If, while moving forward, we reach the end of the array, we will jump to the first element to continue the movement.
//    If, while moving backward, we reach the beginning of the array, we will jump to the last element to continue the movement.
// Write a method to determine if the array has a cycle. The cycle should have more than one element and should follow one direction which means the cycle should not contain both forward and backward movements.
public class CycleInCircularArray {

  public static void main(String[] args) {
    System.out.println(hasCycle(new int[]{1, 2, -1, 2, 2}));
    System.out.println(hasCycle(new int[]{2, 2, -1, 2}));
    System.out.println(hasCycle(new int[]{2, 1, -1, -2}));
  }

  public static boolean hasCycle(int[] arr) {

    for (int i = 0; i < arr.length; i++) {
      boolean isForward = arr[i] >= 0;
      int slow = i;
      int fast = i;
      do {
        slow = findNext(slow, arr, isForward);
        fast = findNext(fast, arr, isForward);
        if (fast != -1) {
          fast = findNext(fast, arr, isForward);
        }
      } while (fast != slow && fast != -1 && slow != -1);

      if (slow != -1 && slow == fast) {
        return true;
      }
    }
    return false;


  }

  private static int findNext(int currentIndex, int[] arr, boolean isForward) {
    boolean direction = arr[currentIndex] >= 0;
    if (direction != isForward) {
      return -1;
    }
    int nextIndex = (currentIndex + arr[currentIndex]) % arr.length;
    if (nextIndex < 0) {
      nextIndex = nextIndex + arr.length;
    }
    if (nextIndex == currentIndex) {
      return -1;
    }
    return nextIndex;
  }

}
