package algorithms.leetcodestackandqueues;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();


    public MyStack() {

    }

    public void push(int x) {
        if(empty()) {
            q1.offer(x);
            return;
        }

        if(!q1.isEmpty()) { //q2 is empty
            q2.offer(x);
            while(!q1.isEmpty()) {
                q2.offer(q1.poll());
            }
        }else if(!q2.isEmpty()) { // q1 is empty
            q1.offer(x);
            while (!q2.isEmpty()) {
                q1.offer(q2.poll());
            }
        }
    }

    public int pop() {
        return q1.isEmpty()? q2.poll(): q1.poll();// assuming pop is called on non-empty stack
    }

    public int top() {
        return q1.isEmpty()? q2.peek(): q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}
