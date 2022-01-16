package algorithms.geekforgeeks.course.array;

public class ProductExceptSelf {
  public static long[] productExceptSelf(int nums[], int n)
  {
    // code here
    long[] result = new long[n];
    int product =1;
    for(int i=0; i<n ;i++) {
      product *= nums[i];
    }

    for(int i=0; i < n;i++) {
      result[i] = product/nums[i];
    }
    return result;
  }
}
