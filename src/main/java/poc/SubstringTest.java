package poc;

public class SubstringTest {
    public static void main(String[] args) {
        String s1 = "abcd";
        System.out.println(s1.substring(0,0));// empty String
        System.out.println(s1.substring(0,1));// prints a
        System.out.println(s1.substring(s1.length())); // empty String
    }
}
