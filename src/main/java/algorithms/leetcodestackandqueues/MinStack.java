package algorithms.leetcodestackandqueues;

import java.util.ArrayList;
import java.util.List;

class MinStack {

    class Node {
        int val;
        int min;

        Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }
    List<Node> list = new ArrayList<>();

    public MinStack() {

    }

    public void push(int val) {
        if(list.size() !=0) {
            list.add(new Node(val, Math.min(getMin(), val)));
        } else {
            list.add(new Node(val, val));
        }
    }

    public void pop() {
        list.remove(list.size()-1);
    }

    public int top() {
        return list.get(list.size()-1).val;
    }

    public int getMin() {
        return list.get(list.size()-1).min;
    }
}


