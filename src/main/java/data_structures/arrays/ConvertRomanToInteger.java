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
        System.out.println(convertRomanToInteger("IV"));
        System.out.println(getInt("XXX"));
        System.out.println(getInt("IV"));
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

    public static Integer getInt(String roman) {
        int result=0;
        char[] c = roman.toCharArray();
        for(int i=0; i< roman.length(); i++) {
            result +=map.get(c[i]);
            if(i> 0 && map.get(c[i]) > map.get(c[i-1])) {
                result = result - 2*map.get(c[i-1]);
            }
        }
        return result;
    }

    private static int getValueForIndex(String str, int index ) {
        return map.get(str.charAt(index));
    }

}
