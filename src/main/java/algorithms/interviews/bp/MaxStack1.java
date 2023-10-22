package algorithms.interviews.bp;

import java.util.ArrayDeque;
import java.util.Deque;

class MaxStack1 {
    class Element {
        int val;
        int max;
        public Element(int val, int max) {
            this.val = val;
            this.max = max;
        }
    }

    private Deque<Element> stack;
    private Deque<Element> aux;

    /** initialize your data structure here. */
    public MaxStack1() {
        stack = new ArrayDeque<Element>();
        aux = new ArrayDeque<Element>();
    }

    public void push(int x) {
        int max = x;
        if (!stack.isEmpty()) {
            max = Math.max(x, stack.peek().max);
        }

        stack.push(new Element(x, max));
    }

    public int pop() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.pop().val;
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek().val;
    }

    public int peekMax() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek().max;
    }

    public int popMax() {
        if (stack.isEmpty()) {
            return -1;
        }
        int max = stack.peek().max;
        while (!stack.isEmpty() && stack.peek().val != max) {
            aux.push(stack.pop());
        }
        // pop the max
        if (!stack.isEmpty()) {
            stack.pop();
        }

        while (!aux.isEmpty()) {
            push(aux.pop().val);
        }
        return max;
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
