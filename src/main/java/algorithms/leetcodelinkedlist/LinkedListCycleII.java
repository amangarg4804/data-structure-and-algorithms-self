package algorithms.leetcodelinkedlist;

// Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
public class LinkedListCycleII {

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // First we check whether there is a cycle
        while (fast!=null && fast.next!=null) {
            slow = slow.next ;
            fast = fast.next.next;
            if(slow ==fast) {
                break;
            }
        }
        // after the loop ends, how do we know whether the loop existed due to break condition or the condition in while loop returning false
        if(fast==null || fast.next==null) {
            return null;
        }
        // at this point, we are sure there is a cycle in linked list, now the idea is that if we initialise one of the references (fast or slow) to head.
        // And move them both one step at a time, they will meet at intersection point
        slow = head;
        while (slow!=fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
