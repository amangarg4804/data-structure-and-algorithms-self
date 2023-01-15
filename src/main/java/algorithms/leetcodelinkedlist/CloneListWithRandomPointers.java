package algorithms.leetcodelinkedlist;

import java.util.HashMap;
import java.util.Map;

public class CloneListWithRandomPointers {
    public Node copyRandomList(Node head) {
        Map<Node, Node> oldToNew = new HashMap<>();
        Node current = head;
        while(current!=null) { // one pass to clone all nodes in linked list and preparing map
            Node clone = new Node(current.val);
            oldToNew.put(current, clone);
            current = current.next;
        }
        current = head;
        while (current !=null) {// 2nd pass to set references of cloned nodes
            Node clone = oldToNew.get(current);
            clone.next = oldToNew.get(current.next);
            clone.random = oldToNew.get(current.random);
            current = current.next;
        }
        return oldToNew.get(head);

    }

    public Node copyRandomList1(Node head) {
        if(head ==null) {
            return null;
        }
        // without extra space
        // 3 passes:
        // 1) clone each node and make the cloned node next node of original node n.next = n' (interleaving both lists)
        // 2) iterate over the list and set random pointers of cloned node n'.random = n.random.next (n.random will point to original random node and random's next will point to cloned random)
        // 3) iterate over the list again and break the two linked lists. n = n.next.next , n' = n'.next.next
        Node current = head;
        while(current!=null) {
            Node clone = new Node(current.val);
            Node next = current.next;
            current.next = clone ;
            clone.next = next;
            current= next;
        }
        current = head;
        while (current !=null) {// 2nd pass to set random nodes
            Node cloned = current.next;
            if(current.random !=null) {
                cloned.random = current.random.next;
            }
            current = current.next.next; // moving to next original node. current.next can't be null because current.next is cloned node
        }
        current = head;
        Node clonehead = current.next;
        Node clone = clonehead;

        while (current.next.next!=null) { // current.next can't be null because of current.next pointing to cloned node
            Node next = current.next.next;
            Node nextClone = clone.next.next; // 1-> 1'-> 2-> 2'-> 3 -> 3'  => 1-> 2->2'->3->3',  1' ->2'-> 3->3'
            current.next = next;
            clone.next = nextClone;
            current =  current.next;
            clone = clone.next;
        }
        current.next=null;
        return clonehead;
    }

    public Node copyRandomList2(Node head) {
        if(head ==null) {
            return null;
        }
        // without extra space
        // 3 passes:
        // 1) clone each node and make the cloned node next node of original node n.next = n' (interleaving both lists)
        // 2) iterate over the list and set random pointers of cloned node n'.random = n.random.next (n.random will point to original random node and random's next will point to cloned random)
        // 3) iterate over the list again and break the two linked lists. n = n.next.next , n' = n'.next.next
        Node current = head;
        while(current!=null) {
            Node clone = new Node(current.val);
            Node next = current.next;
            current.next = clone ;
            clone.next = next;
            current= next;
        }
        current = head;
        while (current !=null) {// 2nd pass to set random nodes
            Node cloned = current.next;
            if(current.random !=null) {
                cloned.random = current.random.next;
            }
            current = current.next.next; // moving to next original node. current.next can't be null because current.next is cloned node
        }
        current = head;
        Node dummyHead = new Node(0);
        Node previousClone = dummyHead;
        while (current!=null) {
            Node next = current.next.next;// current.next can't be null because of current.next pointing to cloned node
            Node nextClone = current.next;
            current.next = next;
            previousClone.next = nextClone;
            current =  current.next;
            previousClone = previousClone.next;
        }
        return dummyHead.next;
    }

    Map<Node, Node> oldToNew = new HashMap<>();
    public Node copyRandomList3(Node head) {
        //recursive
        if(head ==null) {
            return null;
        }
        if(oldToNew.containsKey(head)) {
            return oldToNew.get(head);
        }
        Node clone = new Node(head.val);
        oldToNew.put(head, clone);
        clone.next = copyRandomList3(head.next);
        clone.random = copyRandomList3(head.random);
        return clone;
    }
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
