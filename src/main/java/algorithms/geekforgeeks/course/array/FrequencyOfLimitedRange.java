package algorithms.geekforgeeks.course.array;

public class FrequencyOfLimitedRange {
  public static void frequencyCount(int arr[], int N, int P)
  {
    // O(n) space and O(n) time
    int[] countArr = new int[P+1];

    for(int i=0; i < N ; i++) {
      countArr[arr[i]] +=1;
    }

    for(int i =0; i < arr.length ; i++ ) {

      if(i+1 < countArr.length){
        arr[i] = countArr[i+1];
      } else {
        arr[i] = 0;
      }
    }
  }

  public static void frequencyCount2(int arr[], int N, int P)
  {
    // O(n^2)  time abd O(n) space
    // iterate the array once for each number (1, 2, 3, 4) and count
  }

  public static void frequencyCount3(int arr[], int N, int P)
  {
    // O(n) space and O(n) time
    // use a hashmap instead of Array
  }
}
