package algorithms.leetcodelinkedlist;


import java.util.Deque;
import java.util.LinkedList;

public class FlattenLinkedList {

    public Node flatten(Node head) {
        flattenRecursive(head);
        return head;
    }

    private Node flattenRecursive(Node current) {
        // 5 cases:
        if(current==null) {
            return null; // case 1: current is null, only possible when head is null
        }
        if(current.child==null) {
            if(current.next==null) {
                return current; // case 2: when child is null and next is null
            }
            return flatten(current.next); // case 3: when child is null but current.next is not null;
        } else {
            Node child = current.child;
            Node next = current.next;
            child.prev = current;
            current.next = child;
            current.child =null;
            Node childListEndingNode = flattenRecursive(child);
            if(next!=null) {
                childListEndingNode.next = next;
                next.prev = childListEndingNode;
                return flattenRecursive(next);// case 4: when child is not null and next is not null
            }
            return flattenRecursive(child); // case 5: when child is not null and next is null
        }
    }

    public Node flatten1(Node head) {
        Deque<Node> stack = new LinkedList<>();
        Node current = head;
        while (!stack.isEmpty() || current !=null) {
            if(current.child !=null) {
                if(current.next!=null) {
                    stack.push(current.next);
                }
                current.next = current.child; // next should point to child if child exists
                current.next.prev = current; // current.next is child now
                current.child= null;
            } else if(current.next==null && !stack.isEmpty()) { // we have reached end node of a list/sublist. So we should check if stack has any nodes to be appended
                current.next = stack.pop();
                current.next.prev = current;
            }
            current = current.next;
        }
        return head;
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
