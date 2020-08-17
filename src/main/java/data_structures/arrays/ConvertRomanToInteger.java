package data_structures.arrays;

import java.util.HashMap;
import java.util.Map;

public class ConvertRomanToInteger {
    private static final Map<Character, Integer> map = new HashMap<>();

    static  {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    public static void main(String[] args) {
        System.out.println(convertRomanToInteger("XXX"));
    }

    private static int convertRomanToInteger(String s) {
        int result = getValueForIndex(s, 0);

        for (int i = 1; i< s.length(); i++) {
            int currentValue  = getValueForIndex(s, i);
            result += currentValue;
            int previousValue  = getValueForIndex(s, i-1);
            if(currentValue > previousValue) {
                result = result - 2*previousValue;
            }
        }

        return result;
    }

    private static int getValueForIndex(String str, int index ) {
        return map.get(str.charAt(index));
    }

}
