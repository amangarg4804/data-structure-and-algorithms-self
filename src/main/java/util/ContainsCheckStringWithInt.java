package util;

import java.util.ArrayList;
import java.util.List;

public class ContainsCheckStringWithInt {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("123");
        System.out.println(list.contains(123));
    }
}
