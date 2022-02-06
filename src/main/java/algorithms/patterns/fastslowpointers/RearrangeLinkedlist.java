package algorithms.patterns.fastslowpointers;

//Given the head of a Singly LinkedList, write a method to modify the LinkedList such that the nodes from the second half of the LinkedList are inserted alternately to the nodes from the first half in reverse order
// No extra space. In place
public class RearrangeLinkedlist {

  public static void main(String[] args) {
    ListNode head = new ListNode(2);
    head.next = new ListNode(4);
    head.next.next = new ListNode(6);
    head.next.next.next = new ListNode(8);
    head.next.next.next.next = new ListNode(10);
    head.next.next.next.next.next = new ListNode(12);
    reorder(head);
    while(head!=null) {
      System.out.println(head.value);
      head = head.next;
    }
  }

  public static void reorder(ListNode head) {
    ListNode middle = findMiddle(head);
    ListNode headSecondHalf = reverse(middle);
    ListNode headFirstHalf = head;

    while (headSecondHalf!=null && headFirstHalf !=null) {
      ListNode temp = headFirstHalf.next;
      headFirstHalf.next = headSecondHalf;
      headFirstHalf = temp;

      temp = headSecondHalf.next;
      headSecondHalf.next = headFirstHalf;
      headSecondHalf = temp;
    }

    if(headFirstHalf != null) {
      headFirstHalf.next = null;
    }
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
    while (fast !=null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
}
