package algorithms.leetcodelinkedlist;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (l1 != null || l2 != null) {
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int sum = l1Val + l2Val + carry;
            current.next = new ListNode(sum % 10);
            current = current.next;
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }

        }
        if(carry!=0) {
            current.next = new ListNode(carry);
        }
        return dummy.next;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {


        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (l1 != null || l2 != null) {
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int sum = l1Val + l2Val + carry;
            current.next = new ListNode(sum % 10);
            current = current.next;
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }

        }
        if(carry!=0) { // could also eliminate this condition by adding this condition in while loop
            current.next = new ListNode(carry);
        }
        return dummy.next;
    }
}
