package algorithms.turingtests;

public class LambdaInetgerToStringTest {
    public static void main(String[] args) {
        //SimpleFunctionalInterface obj = 2-> String.valueOf(2); won't compile
        SimpleFunctionalInterface obj1 = (int a) -> String.valueOf(a);//compiles
        SimpleFunctionalInterface obj2 = a -> String.valueOf(a); //compiles
    }
}

interface SimpleFunctionalInterface {
    String fromInt(int a);
}
