package data_structures.arrays;

public class MultipleOf3 {

    public static void main(String[] args) {
        String input = "110100101010010010101";
        System.out.println(isMultipleOf3(input));
    }

    private static int isMultipleOf3(String input) {
        int position = 0;
        int positionCompliment = 0;
        for (int i = 0 ; i< input.length(); i++) {
            if(i%2 ==0 && input.charAt(i)=='1') {
                position++;
            }
            if(i%2 !=0 && input.charAt(i)=='1') {
                positionCompliment++;
            }
        }
        if((position - positionCompliment) % 3 ==0) {
            return 1;
        }
        return 0;
    }

}
