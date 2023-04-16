package util;

import java.util.regex.Pattern;

public class PatternMatcher {

    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9.,&#: *()-//]*$");

    public static void main(String[] args) {
        String line3 = "&#";
        System.out.println(PATTERN.matcher(line3).matches());
    }
}
