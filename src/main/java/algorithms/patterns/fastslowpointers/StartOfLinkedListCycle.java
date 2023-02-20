package algorithms.patterns.fastslowpointers;

public class StartOfLinkedListCycle {

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);

    head.next.next.next.next.next.next = head.next.next;
    System.out.println("LinkedList cycle start :" + findCycleStart(head).value);
    head.next.next.next.next.next.next = head.next.next.next;
    System.out.println("LinkedList cycle start :" + findCycleStart(head).value);
    head.next.next.next.next.next.next = head;
    System.out.println("LinkedList cycle start :" + findCycleStart(head).value);
  }

  private static ListNode findCycleStart(ListNode head) {
    ListNode slow = head;
    ListNode fast = head; 

    int cycleLength = 0;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        cycleLength =  calculateCycleLength(slow);
        break;
      }
    }

    return cycleStart( cycleLength, head);
  }

  private static ListNode cycleStart(int cycleLength, ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (cycleLength > 0) {
      fast = fast.next;
      cycleLength--;
    }
    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }
    return slow;
  }

  private static int calculateCycleLength(ListNode slow) {
    ListNode current = slow.next;
    int length = 1;
    while (current != slow) {
      current = current.next;
      length++;
    }
    return length;
  }
}
