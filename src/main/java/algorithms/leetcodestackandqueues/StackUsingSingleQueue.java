package algorithms.leetcodestackandqueues;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingSingleQueue {
    Queue<Integer> q = new LinkedList<>();


    public void push(int x) {
        int size = q.size();
        q.offer(x);
        for (int i =0; i< size;i++) {
            q.offer(q.poll());
        }
    }

    public int pop() {
        return q.poll();// assuming pop is called on non-empty stack
    }

    public int top() {
        return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
