package algorithms.leetcodeBeginner;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String s = "";
            if (i % 3 != 0 && i % 5 != 0) {
                s = String.valueOf(i);
            } else {
                if (i % 3 == 0) {
                    s += "Fizz";
                }
                if (i % 5 == 0) {
                    s += "Buzz";
                }
            }
            result.add(s);
        }
        return result;
    }

    public List<String> fizzBuzz1(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String s = "";
            if (i % 3 == 0) {
                s += "Fizz";
            }
            if (i % 5 == 0) {
                s += "Buzz";
            }
            if (s.isEmpty()) {
                s = String.valueOf(i);
            }
            result.add(s);
        }
        return result;
    }
}
