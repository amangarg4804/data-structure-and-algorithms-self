package data_structures.linked_lists;

import data_structures.linked_lists.singly.SelfSinglyLinkedList.Node;

public class DetectCycle {
    // tortoise hair
    // if both of these will meet, then there is a cycle;

    public static boolean detectLoop(Node head){
        Node hare = head;
        Node tortoise = head;
        while (hare != null && hare.next!=null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if(hare == tortoise) {
                return true;
            }
        }
        return false;
    }

}
