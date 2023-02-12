package util;

import org.apache.commons.lang3.StringUtils;

public class RemoveFirstNonAlphaneumeric {
    public static void main(String[] args) {
        System.out.println(removeFirstNonAlphaNumeric("#"));
    }
    public static String removeFirstNonAlphaNumeric(String input) {
        if(input==null) {
            return StringUtils.EMPTY;
        }
        int i = 0;
        while (i < input.length() && !Character.isLetterOrDigit(input.charAt(i))) {
            i++;
        }
        return input.substring(i);
    }


    public static String replace(String priority1, String priority2, String priority3) {
        if (!priority1.isEmpty()) {
            return priority1;
        } else if (!priority2.isEmpty()) {
            return priority2;
        }
        return priority3;
    }
}
