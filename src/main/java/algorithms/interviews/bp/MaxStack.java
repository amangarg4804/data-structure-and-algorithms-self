package algorithms.interviews.bp;

import java.util.Stack;

//https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/716-max-stack.html
// use for testing solution: O(Log n) pop operation: https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/stacks/max-stack-offical/ojquestion -
//to learn: https://aaronice.gitbook.io/lintcode/stack/max-stack -
// to learn: https://www.jiakaobo.com/leetcode/716.%20Max%20Stack.html
public class MaxStack {
    Stack<Integer> stack = new Stack();
    Stack<Integer> maxStack = new Stack();
    public MaxStack() {

    }

    public void push(int x) {
        stack.push(x);
        if(maxStack.isEmpty() || x >= maxStack.peek()) {
            maxStack.push(x);
        }
    }

    public int pop() {
        int val = stack.pop();
        if(maxStack.peek() ==val) {
            maxStack.pop();
        }
        return val;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        return 0;
    }
}
