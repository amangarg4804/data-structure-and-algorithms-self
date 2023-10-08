package algorithms.bp;


import java.util.Stack;

//https://leetcode.com/problems/min-stack/description/
public class MinStackUsingStacks {
    Stack<Integer> stack = new Stack();
    Stack<Integer> minStack = new Stack();

    public void push(int val) {
        stack.push(val);
        if(minStack.isEmpty() || val <=minStack.peek()) {
            minStack.push(val);
        }
    }

    public int pop() {
        int val = stack.pop();
        if(minStack.peek() ==val) {
            minStack.pop();
        }
        return val;
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
