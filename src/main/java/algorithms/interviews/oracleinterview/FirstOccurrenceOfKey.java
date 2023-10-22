package algorithms.interviews.oracleinterview;

public class FirstOccurrenceOfKey {
// Write a method that takes a sorted array and a key and returns the index of first occurrence of that key in the array

//-14 -10 2 108 108 243 285 285 285 401

  public static void main(String[] args) {
    System.out.println(search(new int[]{-14, -10, 2, 108, 108, 243, 285, 285, 285, 401}, 285));
  }

  public static int search(int[] arr, int key) {
    int start = 0;
    int end = arr.length -1;
    int result = -1;
    while(start <=end) {
      int mid = start + (end -start)/2;
      if(arr[mid] == key ) {
        result = mid;
        int index = result-1 ;
        while(index >= 0 && arr[index] == key) {
          result= index;
          index--;
        }
        break;
      } else if(arr[mid] > key) {
        end = mid-1;
      } else {
        start = mid + 1;
      }
    }
    return result;
  }


}
