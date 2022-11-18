package util;

public class RetryCountTest {
    public static void main(String[] args) {
        int retryCount = 5;
        while (retryCount-->0) {
            if(true) { // true or false
                System.out.println("hello");
            } else {
                break;
            }
        }
    }
}
