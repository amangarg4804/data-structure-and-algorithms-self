package algorithms;

public class ShiftOperators {

    public static void main(String[] args) {
        // right shift operator will divide the number by some power of 2
        int i = 20;
        System.out.println("Right shift");
        System.out.println(i>>1);
        System.out.println(i>>2);
        System.out.println(i>>3);

        // left shift operator will multiple the number by some power of 2
        System.out.println("left shift");
        System.out.println(i<<1);
        System.out.println(i<<2);
        System.out.println(i<<3);
    }

}
