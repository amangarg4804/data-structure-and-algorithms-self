package algorithms.patterns.fastslowpointers;


public class LinkedListCycle {

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);

    System.out.println("LinkedList has cycle :" + hasCycle(head));
    head.next.next.next.next.next.next = head.next.next;
    System.out.println("LinkedList has cycle :" + hasCycle(head));
    head.next.next.next.next.next.next = head.next.next.next;
    System.out.println("LinkedList has cycle :" + hasCycle(head));
  }

  public static boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        return true;
      }
    }
    return false;
  }
}
