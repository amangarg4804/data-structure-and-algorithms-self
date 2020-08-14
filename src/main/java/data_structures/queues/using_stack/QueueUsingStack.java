package data_structures.queues.using_stack;

import java.util.Stack;

public class QueueUsingStack {
    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();

    void Push(int x)
    {
        s1.push(x);
    }

    int Pop()
    {
        if(s2.empty()) {
            while(!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.empty() ? -1 : s2.pop();
    }
}
