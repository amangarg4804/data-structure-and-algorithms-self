package algorithms.leetcodelinkedlist;

public class PalindromeLinkedListRecursive {

    ListNode start;
    public boolean isPalindrome(ListNode head) {
        if(head ==null) {
            return true;
        }
        start = head;
        return isPalindromeRecursive(head);
    }

    private boolean isPalindromeRecursive( ListNode end) {
        if(end==null) {
            return true;
        }
        boolean result = isPalindromeRecursive(end.next); // 1-> 2->3-> 2-> 1
        if(!result) {
            return false;
        }
        if(start.val != end.val) {
            return false;
        }
        start = start.next;
        return true;
    }

    //  // 1-> 2->3-> 2-> 1
    // recursion stack:
    // 1, 1 -> 1 , 1 -> return true.
    // 1, 2, -> 2, 2 -> change start to 1, return true
    // 1, 3 -> 3, 3 -> change start to 2, return true
    // 1, 2 -> 2, 2 -> change start to 3, return true
    // 1, 1, -> 1, 1-> change start to 2, return true
    // 1, null -> return true
    // Notice that we are iterating the list twice, once by end pointer and then by start pointer.
    // Also notice, once we reach middle element, start and end nodes are referencing the same nodes
    // is there a way to break recursion after we reach middle element (start ==end)?
}
