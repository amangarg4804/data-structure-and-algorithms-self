package algorithms.geekforgeeks.course.array;

public class AlternatePositiveAndNegative {

  void rearrange(int arr[], int n) {
    // code here

    int[] positive = new int[n];
    int[] negative = new int[n];
    int positiveIndex = 0;
    int negativeIndex = 0;
    for(int i=0; i< n ; i++) {
      if(arr[i] <0) {
        negative[negativeIndex++] = arr[i];
      } else {
        positive[positiveIndex++] = arr[i];
      }
    }
    int currentPositiveIndex = 0;
    int currentNegativeIndex = 0;
    int i=0;
    while(i< n) {
      if(currentPositiveIndex <positiveIndex ) {
        arr[i] = positive[currentPositiveIndex++];
        i++;
      }
      if(currentNegativeIndex < negativeIndex && i<n) {
        arr[i] = negative[currentNegativeIndex++];
        i++;
      }

    }
  }

  void rearrangeWithNoExtraSpace(int arr[], int n) {
    // code here

    int positive = 0;
    int negative =0;
    int k =0;
    while (k < n && positive < n && negative < n) {
      if(k % 2 == 0) {
        //if index is even, number should be positive
        if(arr[k]<0){
          positive =k;
          negative =k;
          while (positive < n && arr[positive] < 0) {
            positive++;
          }
          if(positive >=n) {
            break;
          }
          rotate(arr, negative, positive);
        }
        k++;
      } else {
        if(arr[k] >=0) {
          positive =k;
          negative =k;
          while (negative < n && arr[negative] >= 0) {
            negative++;
          }
          if(negative >=n) {
            break;
          }
          rotate(arr, positive, negative);
        }
        k++;
      }
    }
  }


  private void rotate(int arr[], int left , int right) {
    int temp = arr[right];
    for (int i =right-1 ; i >=left ; i--) {
      arr[i+1]=arr[i];
    }
    arr[left]= temp;
  }



}
