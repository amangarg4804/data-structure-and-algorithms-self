package data_structures.linked_lists;

import data_structures.linked_lists.singly.SelfSinglyLinkedList.Node;

public class RotateLinkedList {

    public Node rotate(Node head, int k) {
        if(head ==null || head.next ==null) {
            return head;
        }
        Node inputHead = head;
        Node leaderOfNewHead = head;
        Node tail = head;
        int length=1;
        while (tail.next !=null) {
            tail = tail.next;
            length++;
        }
        if(length == k) {
            return head;
        }
        while (k-- != 1) {
            leaderOfNewHead = leaderOfNewHead.next;
        }

        Node newHead = leaderOfNewHead.next;
        leaderOfNewHead.next = null;
        tail.next = inputHead;

        return newHead;
    }

}
