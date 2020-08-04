package data_structures.linked_lists.singly;

import data_structures.linked_lists.singly.SelfSinglyLinkedList.Node;

public class ReverseALinkedList {

    public static Node reverse(SelfSinglyLinkedList list) {
        Node prev = null;
        Node current = list.head;
        while (current!=null) {
            Node temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        list.head = prev;
        return prev;
    }
}
