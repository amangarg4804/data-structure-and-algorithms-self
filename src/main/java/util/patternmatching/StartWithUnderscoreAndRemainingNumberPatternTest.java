package util.patternmatching;

import java.util.regex.Pattern;

public class StartWithUnderscoreAndRemainingNumberPatternTest {
    private static final Pattern SUFFIX_PATTERN = Pattern.compile("^_([1-9]|[1-9][0-9])$");
    public static void main(String[] args) {
        System.out.println(SUFFIX_PATTERN.matcher("_123").matches());
        System.out.println(SUFFIX_PATTERN.matcher("_32").matches());
        System.out.println(SUFFIX_PATTERN.matcher("_5").matches());
        System.out.println(SUFFIX_PATTERN.matcher("_1").matches());
    }
}
