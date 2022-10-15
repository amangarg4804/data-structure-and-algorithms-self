package algorithms.leetcoderecursion;

public class SwapNodeInPairs {

    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next ==null) {
            return head;
        }
        ListNode nextHead = head.next.next;
        ListNode next = head.next; //2
        next.next= head;
        head.next = swapPairs(nextHead);
        return next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
