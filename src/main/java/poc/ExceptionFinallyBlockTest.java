package poc;

public class ExceptionFinallyBlockTest {
    public static void main(String[] args) {
        System.out.println(test("1"));
    }

    public static String test(String input) {
        if("1".equals(input)) {
            return "1";
        }
        try {
            if("2".equals(input)) {
                System.out.println(123);
                return "from try";
            }

        }finally {
            System.out.println("from finally");
        }
        return "abc";
    }
}
