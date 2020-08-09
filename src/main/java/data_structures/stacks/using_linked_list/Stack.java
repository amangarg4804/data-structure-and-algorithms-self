package data_structures.stacks.using_linked_list;

import java.util.EmptyStackException;

public class Stack {

    private MySinglyLinkedNode top;

    public void push(Integer i) {
        MySinglyLinkedNode newNode = new MySinglyLinkedNode(i);
        if (top == null) {
            this.top = newNode;
        } else {
            newNode.setNext(top);
            this.top = newNode;
        }
    }

    public Integer pop() {
        if (this.top == null) {
            throw new EmptyStackException();
        }
        Integer popped = this.top.getValue();
        this.top = this.top.getNext();
        return popped;
    }

    public Integer peek() {
        if (this.top == null) {
            throw new EmptyStackException();
        }
        return this.top.getValue();
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    public void print() {
        MySinglyLinkedNode current = top;
        while (current != null) {
            System.out.println(current.getValue());
            current = current.getNext();
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.print();
        System.out.println("Peek " + stack.peek());
        stack.print();
        System.out.println("Pop " +stack.pop());
        stack.print();
        stack.push(3);
        System.out.println("Pop " + stack.pop());
    }

}
