package algorithms.patterns.binarysearch;

public class NextLetter {

  public static void main(String[] args) {
    System.out.println(searchNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'f'));
    System.out.println(searchNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'b'));
    System.out.println(searchNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'm'));
    System.out.println(searchNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'h'));
  }

  private static char searchNextLetter(char[] letters, char key) {
    if(letters[letters.length-1] <= key || key < letters[0]) {
      return letters[0];
    }

    int start =0;
    int end = letters.length -1;
    int nextLetter = -1;
    while (start <=end) {
      int mid = start + (end-start)/2;
      if(letters[mid] > key) {
        end = mid-1;
        nextLetter = mid;
      } else {
        start = mid +1;
      }
    }
    return letters[nextLetter];
  }
}
