package util;

import java.util.Objects;

public class ObjectEquality {
    public static void main(String[] args) {
        String sb = null;
        StringBuilder sb2 = new StringBuilder("cde");

        if(Objects.equals(sb, sb2.toString())) {
            System.out.println("same");
        }
    }
}
