package algorithms.interviews.lenskartinterview;

import java.util.HashMap;
import java.util.Map;

public class ArrangeCharacters {
    public static void main(String[] args) {

    }

    private static String arrange(String input) {
        if(input==null || input.length() < 2) {
            return input;
        }
        // ABBBC
        // BABCB
        // ABBC
        // BABC
        // ABBBCCCDD
        // BACDBCDBC
        // B -3, A-1, C-1
        // B-2, A-1, C-1
        //
        // BACBB
        //
        StringBuilder result = new StringBuilder();
        Map<Character, Integer> frequency = new HashMap<>();
        for(int i =0; i< input.length(); i++) {
            frequency.put(input.charAt(i), frequency.getOrDefault(input.charAt(i), 0)+1);
        }

        // how many threads shedlock uses
        // problem with indexes other than space


        return result.toString();
    }
}
