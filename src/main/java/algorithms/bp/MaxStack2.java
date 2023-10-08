package algorithms.bp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// O(n) time complexity popMax operation
class MaxStack2 {
    Stack<Element> stack = new Stack<>();
    Stack<Element> aux = new Stack<>();
    public MaxStack2() {

    }

    public void push(int x) {
        int max = x;
        if (!stack.isEmpty()) {
            max = Math.max(max, stack.peek().max);
        }
        stack.push(new Element(x, max));
    }

    public int pop() {
        return stack.pop().val;
    }

    public int top() {
        return stack.peek().val;
    }

    public int peekMax() {
        return stack.peek().max;
    }

    public int popMax() {
        int max = stack.peek().max;
        while(stack.peek().val !=max) {
            aux.push(stack.pop());
        }
        stack.pop(); // pop the max element
        while(!aux.isEmpty()) {
            push(aux.pop()); // can't directly use stack.push here because max has to be recalculated for each value
        }
        return max;
    }

    private void push(Element e) {
        int max =e.val;
        if (!stack.isEmpty()) {
            max = Math.max(max, stack.peek().max);
        }
        e.max = max;
        stack.push(e);
    }

    private class Element {
        int val;
        int max;

        public Element(int val, int max) {
            this.val = val;
            this.max = max;
        }
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
