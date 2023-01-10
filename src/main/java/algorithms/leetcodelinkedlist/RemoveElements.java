package algorithms.leetcodelinkedlist;

//Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val,
// and return the new head.
public class RemoveElements {

    public ListNode removeElements(ListNode head, int val) {
        if(head==null) {
            return null;
        }
        ListNode current = head;
        while (current!=null && current.val==val) { // if val = 3 and initial nodes are 3. 3 -> 3 -> 3 -> 5 -> 3-> 3->2
            head = head.next;
            current = head;
        }

        while (current!=null && current.next!=null) {
            if(current.next.val ==val) {
                current.next = current.next.next;
                continue; // If current is 5, we shouldn't move current to next before remove all 3. 3 -> 3 -> 3 -> 5 -> 3-> 3->2
            }
            current = current.next;
        }
        return head;
    }

    public ListNode removeElements1(ListNode head, int val) {
        ListNode dummy = new ListNode(-1); // given val is >=0, so we use -1
        dummy.next =head;
        ListNode previous = dummy;
        ListNode current = head;
        while (current!=null) {
            if(current.val==val) {
                previous.next = current.next;
            } else {
                previous = previous.next;
            }
            current = current.next;
        }
        return dummy.next;
    }
}
