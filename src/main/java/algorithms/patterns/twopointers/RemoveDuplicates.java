package algorithms.patterns.twopointers;

//Given an array of sorted numbers, remove all duplicates from it.
//    You should not use any extra space; after removing the duplicates in-place return the new length of the array.
public class RemoveDuplicates {

  public static void main(String[] args) {
    System.out.println(countUnique(new int[]{2, 3, 3, 3, 6, 9, 9}));
    System.out.println(countUnique(new int[]{2, 2, 2, 11}));

  }

  public static int countUnique (int[] arr) {
    int left = 0;
    boolean lastSame = false;
    int count =0;
    for(int right = left +1; right< arr.length; right++, left++) {
      if(arr[left] == arr[right]) {
        if(!lastSame) {
          count++;
          lastSame=true;
        }
      } else {
        count++;
        lastSame=false;
      }

    }
    return count;
  }
  public static int countUniqueAndMoveLeft (int[] arr) {
    int nextNonDuplicate = 1;
    // 2, 3, 3, 3, 6, 9, 9
    for(int i = 1; i< arr.length; i++) {
      // i=5, nextNonDuplicate = 2
      if(arr[nextNonDuplicate-1] != arr[i]) {
        arr[nextNonDuplicate] = arr[i];
        nextNonDuplicate++;
      }
    }
    return nextNonDuplicate;
  }
}
