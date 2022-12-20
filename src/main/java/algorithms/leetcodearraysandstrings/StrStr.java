package algorithms.leetcodearraysandstrings;

public class StrStr {

    public int strStr(String haystack, String needle) {
        // time O(M*N) -> substring will take N
        if(needle.length() > haystack.length()) {
            return -1;
        }

        for(int i=0; i<= haystack.length()-needle.length();i++ ) {
            String candidate = haystack.substring(i, i+needle.length()); // can run another loop if we are not allowed to use substring
                                                        // inner loop method could be more efficient than this because We would break inner loop if first character doesn't match ?
            if(candidate.equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
