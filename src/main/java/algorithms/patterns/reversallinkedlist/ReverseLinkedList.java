package algorithms.patterns.reversallinkedlist;

public class ReverseLinkedList {

  public static void main(String[] args) {
    ListNode head = new ListNode(2);
    head.next = new ListNode(4);
    head.next.next = new ListNode(6);
    head.next.next.next = new ListNode(8);
    head.next.next.next.next = new ListNode(10);
    ListNode reversedHead = reverse(head);
    while (reversedHead != null) {
      System.out.println(reversedHead.value);
      reversedHead = reversedHead.next;
    }
  }

  public static ListNode reverse(ListNode head) {
    ListNode previous = null;
    while (head != null) {
      ListNode next = head.next;
      head.next = previous;
      previous = head;
      head = next;
    }
    return previous;
  }
}
