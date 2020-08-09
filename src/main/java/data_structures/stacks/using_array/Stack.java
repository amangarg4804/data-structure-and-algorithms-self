package data_structures.stacks.using_array;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Stack {

    private List<Integer> list= new ArrayList<>();

    public void push (Integer i) {
        list.add(i);
    }

    public Integer pop () {
        if(list.size() ==0) {
            throw new EmptyStackException();
        }
        return list.remove(list.size()-1);
    }

    public boolean isEmpty() {
        return list.size() ==0;
    }

    public Integer peek() {
        if(list.size() ==0) {
            throw new EmptyStackException();
        }
        return list.get(list.size()-1);
    }

    public void print() {
        for (int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
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
