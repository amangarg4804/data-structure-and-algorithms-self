package algorithms.geekforgeeks.course.array;

public class SortZerosOnesAndTwos {
  public static void sort012(int a[], int n)
  {
    int countArr[] = new int[3];

    for(int i=0; i< n; i++) {
      if(a[i]==0) {
        countArr[0]++;
      } else if(a[i]==1) {
        countArr[1]++;
      } else {
        countArr[2]++;
      }
    }

    int i = 0;
    while (countArr[0] > 0) {
      a[i]=0;
      countArr[0]--;
      i++;
    }
    while (countArr[1] > 0) {
      a[i]=1;
      countArr[1]--;
      i++;
    }
    while (countArr[2] > 0) {
      a[i]=2;
      countArr[2]--;
      i++;
    }
  }
}
