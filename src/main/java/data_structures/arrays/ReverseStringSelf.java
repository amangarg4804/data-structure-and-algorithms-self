package data_structures.arrays;

public class ReverseStringSelf {

    public static void main(String[] args) {
        System.out.println(reverseString("abcde"));
    }

    private static String reverseString(String input) {
        char[] inputArr = input.toCharArray();
        int length = inputArr.length;
        int leftIndex = 0;
        int rightIndex = length -1;
        while (leftIndex<rightIndex) {
            char temp = inputArr[leftIndex];
            inputArr[leftIndex] = inputArr[rightIndex];
            inputArr[rightIndex] = temp;
            leftIndex++;
            rightIndex--;
        }

        return new String(inputArr);
    }

}
