package algorithms.patterns.reversallinkedlist;

public class RotateLinkedList {

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);
    ListNode newHead = rotate(head, 3);
    while (newHead != null) {
      System.out.println(newHead.value);
      newHead = newHead.next;
    }

    ListNode head2 = new ListNode(1);
    head2.next = new ListNode(2);
    head2.next.next = new ListNode(3);
    head2.next.next.next = new ListNode(4);
    head2.next.next.next.next = new ListNode(5);
    ListNode newHead2 = rotate(head2, 8);
    while (newHead2 != null) {
      System.out.println(newHead2.value);
      newHead2 = newHead2.next;
    }

  }

  public static ListNode rotate(ListNode head, int k) {
    if(head ==null || head.next ==null || k<=0) {
      return head;
    }
    ListNode lastNode = head;
    int size = 1;
    while(lastNode.next!= null) {
      lastNode = lastNode.next;
      size++;
    }
    k = k%size;
    lastNode.next = head;
    int firstPartEnd = size-k;
    ListNode firstPartEndNode = head;
    while(firstPartEnd >1) {
      firstPartEndNode = firstPartEndNode.next;
      firstPartEnd--;
    }
    head = firstPartEndNode.next;
    firstPartEndNode.next = null;
    return head;
  }

}
