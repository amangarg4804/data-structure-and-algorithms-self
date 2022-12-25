package algorithms.leetcodearraysandstrings;

import java.util.*;

public class ReverseWordsInString {

    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        StringBuilder word =  new StringBuilder();

        for(int i=s.length() -1; i>=0; i--) {
            if(s.charAt(i) ==' ') {
                if(word.length()>0) {
                    result.append(word.reverse()).append(" "); // we could also iterate from start to end and keep a list of String. That would be O(n) instead of o(n^2)
                    word = new StringBuilder();
                }
            } else {
                word.append(s.charAt(i));
            }
        }
        if(word.length() >0) {
            result.append(word.reverse());
            return result.toString();
        }
        return result.deleteCharAt(result.length()-1).toString();
        // O(n^2) - n for loop, n for reversing every String
    }

    public String reverseWords1(String s) {
        String[] strs = s.split(" ");
        List<String> list = new ArrayList<>();
        for( int i=strs.length-1; i>=0; i--) {
            if(!strs[i].equals("")) {
                list.add(strs[i]);
            }

        }
       return String.join(" ", list);
        // O(n)
    }



    public String reverseWords2(String s) {
        List<String> allWords = new LinkedList<>(); // if we use Arraylist, we would have to reverse the result, if we add at 0th index, time complexity will be n^2
        StringBuilder word = new StringBuilder();
        for(int i=0; i< s.length(); i++) {
            char c = s.charAt(i);
            if(c == ' ') {
                if(word.length() > 0) {
                    allWords.add(0, word.toString());
                    word = new StringBuilder();
                }
            } else {
                word.append(c);
            }
        }
        if(word.length() >0) {
            allWords.add(0, word.toString());
        }
        return String.join(" ", allWords);
        // O(n)
    }


    public String reverseWords3(String s) {
        String[] strs = s.trim().split(" +"); // NOTE: trim removes all leading and trailing spaces. + here means "one of more". Here it means one or more space characters. So, we don't have to deal with empty strings anymore
        Collections.reverse(Arrays.asList(strs)); // NOTE: changes to the list will write through to array
        return String.join(" ", strs); // NOTE: method to join list elements with delimeter
        // O(n)
    }

    public static void main(String[] args) {
        System.out.println(new ReverseWordsInString().reverseWords1("  hello world  "));
    }
}
