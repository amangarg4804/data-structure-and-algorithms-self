package algorithms.leetcodestackandqueues;

import java.util.Deque;
import java.util.LinkedList;

public class DecodeString {
    //"3[a]2[bc]"
    // 3[a2[c]]
    // "2[abc]3[cd]ef"

    public  static void main (String[] args) {
//        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(decodeString("100[leetcode]"));
    }
    public static  String decodeString(String s) {
        Deque<StringBuilder> strings = new LinkedList<>();
        Deque<Integer> counts = new LinkedList<>();
        int count = 0;
        StringBuilder currentString =  new StringBuilder();

        for(int i=0; i< s.length() ; i++) {
            Character c = s.charAt(i);

            if(Character.isDigit(c ) ) {
//                count = c-'0';
//                int digitIndex = i+1;
//                while(digitIndex < s.length() && Character.isDigit(s.charAt(digitIndex))) {
//                        count = count *10 + (s.charAt(digitIndex)-'0');
//                        digitIndex++;
//                        i++;
//                }
                count = count * 10 + (c -'0');
            } else if(c =='[') { // Strings - aaa , count -  2, currentStr = "bc"
                strings.push(currentString);
                counts.push(count);
                count =0;
                currentString = new StringBuilder();
            } else if(c==']') {
                StringBuilder prefix = strings.pop();
                Integer currentCount = counts.pop();
                StringBuilder finalString = new StringBuilder();
                while(currentCount-- > 0) {
                    finalString.append(currentString);
                }
                currentString = prefix.append(finalString);
            } else {
                currentString.append(c);
            }
        }
        return currentString.toString();
    }
}
