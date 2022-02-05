package algorithms.patterns.fastslowpointers;

// Given the head of a Singly LinkedList, write a method to check if the LinkedList is a palindrome or not.
// constant space and the input LinkedList should be in the original form once the algorithm is finished. O(N) time complexity
public class PalindromeLinkedList {

  public static void main(String[] args) {
    ListNode head = new ListNode(2);
    head.next = new ListNode(4);
    head.next.next = new ListNode(6);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(2);

    System.out.println(isPalindrome(head));
    head.next.next.next.next.next = new ListNode(2);
    System.out.println(isPalindrome(head));
  }

  public static boolean isPalindrome(ListNode head) {
    ListNode middle = findMiddle(head);
    ListNode headSecondHalf = reverse(middle);
    ListNode copyHeadSecondHalf = headSecondHalf;

    while (head != null && headSecondHalf != null) {
      if (head.value != headSecondHalf.value) {
        break;
      }
      head = head.next;
      headSecondHalf = headSecondHalf.next;
    }
    reverse(copyHeadSecondHalf);
    if (head == null || headSecondHalf == null) {
      return true;
    }
    return false;
  }

  private static ListNode reverse(ListNode head) {
    ListNode previous = null;
    while (head != null) {
      ListNode next = head.next;
      head.next = previous;
      previous = head;
      head = next;
    }
    return previous;
  }

  private static ListNode findMiddle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }


}
