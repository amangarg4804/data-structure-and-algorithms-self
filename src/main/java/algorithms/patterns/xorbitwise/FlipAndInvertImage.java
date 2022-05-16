package algorithms.patterns.xorbitwise;


public class FlipAndInvertImage {

  public static void main(String[] args) {
    print(flipAndInvert(new int[][]{ {1,0,1}, {1,1,1}, {0,1,1}}));
    print(flipAndInvert(new int[][]{ {1,1,0,0}, {1,0,0,1}, {0,1,1,1}, {1,0,1,0}}));
  }

  private static void print (int[][] arr) {
    for(int i = 0; i< arr.length ; i++) {
      for(int j= 0; j< arr[i].length; j++) {
        System.out.print(arr[i][j] + " ");
      }
      System.out.println();
    }

  }

  private static int[][] flipAndInvert(int[][] input) {
    for (int i = 0; i < input.length; i++) {
        int start = 0;
        int end = input[i].length -1;
        while (start < end) {
          int temp = input[i][start];
          input[i][start] = input[i][end] ^1;
          input[i][end] = temp ^1;
          start++;
          end--;
        }
        if(start==end) {
          input[i][start] = input[i][start] ^1;
        }
    }
    return input;
  }
}
