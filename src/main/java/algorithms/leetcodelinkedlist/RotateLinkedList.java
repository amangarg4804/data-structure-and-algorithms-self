package algorithms.leetcodelinkedlist;

public class RotateLinkedList {

    public ListNode rotateRight(ListNode head, int k) {
        // 1->2->3->4->5
        // 5->1->2->3->4
        // 4->5->1->2->3
        if(head ==null || head.next ==null) {
            return head;
        }
        int length = getLength(head);
        k = k % length;

        ListNode slow = head;
        ListNode fast = head;
        int moveAhead = k;
        while (moveAhead !=0) { // maintaining gap of k nodes between slow and fast
            fast = fast.next;
            moveAhead--;
        }
        while (fast.next !=null) { // fast should reach last node of linked list, when fast reaches last node, slow will be pointing to new tail of linked list after rotation, meaning all nodes after slow should be moved to head one by one
            slow = slow.next;
            fast = fast.next;
        }
        ListNode dummyHead = new ListNode(-1); // created a new list
        ListNode current = dummyHead;
        ListNode tail = slow;
        while (tail.next!=null) {
            current.next = tail.next;
            tail = tail.next;
            current = current.next;
        }
        current.next = head; // appending original head to tail of new list
        slow.next =null;

        return dummyHead.next;
    }


    public ListNode rotateRight2(ListNode head, int k) {
        // 1->2->3->4->5
        // 5->1->2->3->4
        // 4->5->1->2->3
        if(head ==null || head.next ==null) {
            return head;
        }
        int length = getLength(head);
        k = k % length;
        if(k==0) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        int moveAhead = k;
        while (moveAhead !=0) { // maintaining gap of k nodes between slow and fast
            fast = fast.next;
            moveAhead--;
        }
        while (fast.next !=null) { // fast should reach last node of linked list, when fast reaches last node, slow will be pointing to new tail of linked list after rotation, meaning all nodes after slow should be moved to head one by one
            slow = slow.next;
            fast = fast.next;
        }
        ListNode newHead = slow.next;
        fast.next = head;
        slow.next =null;
        return newHead;
    }

    private int getLength(ListNode head) {
        int length = 0;
        while(head!=null) {
            length++;
            head = head.next;
        }
        return length;
    }
}
