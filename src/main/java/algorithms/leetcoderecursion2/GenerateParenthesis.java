package algorithms.leetcoderecursion2;

//Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

//Input: n = 3
//        Output: ["((()))","(()())","(())()","()(())","()()()"]


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GenerateParenthesis {

    public static void main(String[] args) {
        new GenerateParenthesis().generateParenthesisIterative(3);
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesis(n, result, "", 0, 0);
        return result;
    }

    private void generateParenthesis(int n, List<String> result, String s, int open, int close) {
        if(close==n) {
            result.add(s);
            return;
        }

        if(open<n) {
            generateParenthesis(n, result, s+'(', open+1, close);
        }
        if(close <open) {
            generateParenthesis(n, result, s+')', open, close+1);
        }

    }
    public List<String> generateParenthesisIterative(int n) {
        List<String> result = new ArrayList<>();
        Queue<ParenthesisString> q = new LinkedList<>();
        q.offer(new ParenthesisString("", 0, 0)) ;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i=0; i< size; i++) {
                ParenthesisString ps = q.poll();
                if(ps.open<n) {
                    q.offer(new ParenthesisString(ps.s + '(' , ps.open+1, ps.close));

                }

                if(ps.close < ps.open) {
                    q.offer(new ParenthesisString(ps.s + ')', ps.open, ps.close+1));
                }
                if(ps.close==n) {
                    result.add(ps.s);
                }
            }
        }
        return result;
    }


    private class ParenthesisString {
        String s;
        int open;
        int close;

        public ParenthesisString(String s, int open, int close) {
            this.s = s;
            this.open = open;
            this.close = close;
        }
    }

}
