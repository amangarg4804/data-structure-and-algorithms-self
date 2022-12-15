package util;

import com.github.rholder.retry.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TestRetryer {
    public static void main(String[] args) {
        System.out.println(testBoolean());
        System.out.println(testString());
    }

    private static boolean testBoolean() {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfExceptionOfType(IOException.class)
                .retryIfRuntimeException()
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();

        try {
            return retryer.call(() -> method2());
        } catch (ExecutionException e) {
            System.out.println("****Execution Exception*****") ;
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (RetryException e) {
            System.out.println("****Retry Exception*****") ;
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static String testString() {
        Retryer<String> retryer = RetryerBuilder.<String>newBuilder()
                .retryIfExceptionOfType(IOException.class)
                .retryIfRuntimeException()
                .withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                .build();

        try {
            return retryer.call(() -> method3());
        } catch (ExecutionException e) {
            System.out.println("****Execution Exception*****") ;
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (RetryException e) {
            System.out.println("****Retry Exception*****") ;
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static boolean method1() {
        throw new RuntimeException();
    }
    private static boolean method2() {
        return false;
    }

    private static String method3() {
        throw new RuntimeException();
    }
}
