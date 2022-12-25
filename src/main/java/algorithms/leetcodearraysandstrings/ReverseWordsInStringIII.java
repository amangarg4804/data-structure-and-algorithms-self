package algorithms.leetcodearraysandstrings;


//1 <= s.length <= 5 * 104
//s contains printable ASCII characters.
//s does not contain any leading or trailing spaces.
//There is at least one word in s.
//All the words in s are separated by a single space.
public class ReverseWordsInStringIII {

    public String reverseWords(String s) {

        char[] chars = s.toCharArray();
        int windowStart = 0;
        int windowEnd = 1;
        while(windowStart < chars.length && windowEnd<chars.length) {
            if(s.charAt(windowEnd) == ' ') {
                reverse(chars, windowStart, windowEnd-1);
                windowStart = windowEnd+1;
            }
            if(windowEnd==chars.length-1) {
                reverse(chars, windowStart, windowEnd);
            }
            windowEnd++;
        }
        return new String(chars);
    }

    private void reverse(char[] chars, int windowStart, int windowEnd) {
        while (windowStart < windowEnd) {
            char temp = chars[windowStart];
            chars[windowStart] = chars[windowEnd];
            chars[windowEnd] = temp;
            windowStart++;
            windowEnd--;
        }

    }

    public String reverseWords1(String s) {
        String[] words = s.split(" ");
        for(int i=0; i< words.length; i++) {
           words[i] = new StringBuilder(words[i]).reverse().toString();
        }
        return String.join(" ", words);
    }

}
