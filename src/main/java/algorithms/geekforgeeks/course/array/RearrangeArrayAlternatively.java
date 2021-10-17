package algorithms.geekforgeeks.course.array;

public class RearrangeArrayAlternatively {
  public static void rearrange(int arr[], int n){

    // Your code here
    int max = n-1;
    int min = 0;

    // Intuition: x%y = x -> if y is greater than x
    //https://www.youtube.com/watch?v=7Px1E0gWlhk

    int greatest = arr[max] + 1;

    for(int i = 0; i< n ; i++) {

      if(i%2 ==0) {

        arr[i] = arr[i] + (arr[max] % greatest) * greatest;
        // (arr[max] % greatest) is always arr[max]
        // multiplication with greatest, later when we divide it by greatest, it would return arr[max]
        // adding arr[i], when we do arr[i]% greatest, it would return original value
        max--;
      } else {
        arr[i] = arr[i] + (arr[min] % greatest) * greatest;
        min++;
      }
    }

    for(int i=0; i< n; i++) {
      arr[i] = arr[i]/ greatest;
    }



  }
}
