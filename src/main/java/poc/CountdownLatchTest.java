package poc;

import java.util.concurrent.CountDownLatch;

public class CountdownLatchTest {
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        for(int i=0;i< 10; i++) {
            latch.countDown();
            System.out.println("hello");
        }
        System.out.println("hello again");
    }
}
