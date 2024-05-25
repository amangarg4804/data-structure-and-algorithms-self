package util;

import java.util.ArrayList;
import java.util.List;

public class SubListBeyondLengthTest {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        System.out.println(l.subList(0, 2));
        System.out.println(l.subList(0, 3));
        System.out.println(l.subList(0, 4));


    }
}
