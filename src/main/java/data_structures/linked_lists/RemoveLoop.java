package data_structures.linked_lists;

import data_structures.linked_lists.singly.SelfSinglyLinkedList.Node;

public class RemoveLoop {

    //method1 is to modify the linked list node data structure to have a boolean flag isVisited
    // that tells whether this node is visited or not
    //1) We traverse the linked list starting from head and keep setting this boolean to true
    // 2) We also keep checking if the isVisited flag is true or not
    // 3) if true, we know that this node is being visited again and we can set it's previous to null
    // 4) I think we need to also get this previous and set its next to null
    // 5) I think we could maintain a pointer to leader of each node
    // But what if we can't modify the linked list?

    public static void removeLoop(Node head) {
        if (head == null) {
            return;
        }
        Node fast = head;
        Node slow = head;
        boolean isLoop = false;

        // First we find out whether there is a loop or not
        while (fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                isLoop = true;
                break;
            }
        }

        if (isLoop) {
            if (fast == head) {
                // If they both meet at head and cycle starts from head
                while (slow.next != fast) {
                    slow = slow.next;
                }
                slow.next = null;
            } else {
                // Now the idea is distance of head node from the node where the cycle starts
                // is equal to distance of current position of fast/slow node
                slow = head;
                while (slow.next != fast.next) {
                    slow = slow.next;
                    fast = fast.next;
                }
                fast.next = null;
            }

        }

    }
}
