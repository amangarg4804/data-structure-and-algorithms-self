package algorithms.leetcodestackandqueues;

import java.util.ArrayList;
import java.util.List;

//Methods pop, top and getMin operations will always be called on non-empty stacks.
// bp
class MinStack {
    List<Node> nodes;
    public MinStack() {
        nodes = new ArrayList<>();
    }

    public void push(int val) {
        if(nodes.isEmpty()) {
            nodes.add(new Node(val, val));
            return;
        }
        nodes.add(new Node(Math.min(val, getMin()), val));
    }

    public void pop() {
        nodes.remove(nodes.size() -1);
    }

    public int top() {
        return nodes.get(nodes.size() -1).val;
    }

    public int getMin() {
       return nodes.get(nodes.size() -1).min;
    }

    private static class Node {
        int min;
        int val;

        public Node(int min, int val) {
            this.min = min;
            this.val = val;
        }
    }
}


