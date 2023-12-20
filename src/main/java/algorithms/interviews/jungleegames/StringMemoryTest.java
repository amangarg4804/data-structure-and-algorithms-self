package algorithms.interviews.jungleegames;

public class StringMemoryTest {
    public static void main(String[] args) {
        String a     ="abc";
        String b = "abc";
        String a_b = "ab";
        String a_c = "c";
        System.out.println(a==b);
        System.out.println((a_b+a_c)==a);
        String c = new String("abc");
        System.out.println(a==c);
        System.out.println(b==c);
        String d = c;
        System.out.println(d==c);
        System.out.println(a==c);
    }
}
