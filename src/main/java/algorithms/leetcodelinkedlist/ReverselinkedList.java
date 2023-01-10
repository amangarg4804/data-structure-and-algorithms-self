package algorithms.leetcodelinkedlist;

public class ReverselinkedList {
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next ==null) {
            return head;
        }
        ListNode newHead = head;
        // we keep moving blacknode.next to head.
        // '4' -> 8 -> 12 -> 20
        // 8 -> '4' -> 12 -> 20
        // 12 -> 8 -> '4' -> 20
        // 20 -> 12 -> 8 -> '4'
        // black.next is null, no node left to be made head
        while (head.next!=null) {
            ListNode next = head.next;
            head.next = next.next;
            next.next =newHead;
            newHead = next;
        }
        return newHead;
    }

    public ListNode reverseList1(ListNode head) {
        ListNode previous = null;
        // reversing pointers. Single quote denotes previous. Double quote is head
        // 'null' "4" -> 8 -> 12 -> 20-> null
        // null <-'4' "8" -> 12 -> 20 -> null
        // null <- 4 <- '8' "12" ->20->null
        // null <- 4 <- 8 <- '12' "20" ->null
        // null <- 4 <- 8 <- 12 <- '20' -> "null"
        // head is null, all connections are reversed. return previous
        while (head!=null) {
            ListNode next = head.next;
            head.next = previous;
            previous = head;
            head = next;

        }
        return previous;
    }

    public ListNode reverseListRecursive(ListNode head) {
        // reversing pointers. Single quote denotes previous. Double quote is head
        // 'null' "4" -> 8 -> 12 -> 20-> null
        // null <-'4' "8" -> 12 -> 20 -> null
        // null <- 4 <- '8' "12" ->20->null
        // null <- 4 <- 8 <- '12' "20" ->null
        // null <- 4 <- 8 <- 12 <- '20' -> "null"

        return reverseListRecursive( head, null);
    }

    private ListNode reverseListRecursive(ListNode head, ListNode previous) {
        if(head ==null) {
            // head is null, all connections are reversed. return previous
            return previous;
        }
        ListNode next = head.next;
        head.next = previous;
        return reverseListRecursive(next, head);
    }
}
