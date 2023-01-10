package algorithms.leetcodelinkedlist;


public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        // Example 1
        // 4-> 5-> 5-> 4
        // Example 2
        // 4-> 5-> 4
        ListNode middle = findMiddle(head);
        ListNode headSecondHalf = reverse(middle); // case 1: 4-> 5  4-> 5, case 2: 4 4->5, 2nd half is bigger in case 2
        ListNode first = head;
        ListNode second = headSecondHalf;
        boolean isPalindrome = true;
        while(first !=null && second !=null) { // for case 2 when second reaches 5, first is null, so loop doesn't run
            if(first.val!=second.val) {
                isPalindrome = false;
                break;
            }
            first = first.next;
            second = second.next;
        }
       reverse(headSecondHalf); // We don't need the returned value of reverse function here. Previous node of middle is still pointing to correct node, it was never modified in this program
        return isPalindrome;
    }


    private ListNode reverse(ListNode head) {
        ListNode previous = null;
        while (head !=null) {
            ListNode next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }
        return previous;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // Example 1
        // 4-> 5-> 5'-> 4 -> returns node 5'
        // Example 2
        // 4-> 5-> 4 -> returns node 5
        while (fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
