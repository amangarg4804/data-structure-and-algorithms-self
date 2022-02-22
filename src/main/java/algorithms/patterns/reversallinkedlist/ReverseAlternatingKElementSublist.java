package algorithms.patterns.reversallinkedlist;

public class ReverseAlternatingKElementSublist {

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);
    head.next.next.next.next.next.next = new ListNode(7);
    head.next.next.next.next.next.next.next = new ListNode(8);

    ListNode newHead = reverse(head, 2);
    while (newHead != null) {
      System.out.println(newHead.value);
      newHead = newHead.next;
    }
  }

  public static ListNode reverse(ListNode head, int k) {
    if(k<=1 || head ==null) {
      return head;
    }
    ListNode current = head;
    ListNode previous = null;
    while (true) {
      ListNode lastNodeOfPrevious = previous;
      ListNode lastNodeOfCurrentSubList = current;
      int i =0;
      while(current != null && i< k) {
        ListNode next = current.next;
        current.next = previous;
        previous = current;
        current = next;
        i++;
      }
      if(lastNodeOfPrevious != null) {
        lastNodeOfPrevious.next = previous;
      } else {
       head = previous;
      }
      lastNodeOfCurrentSubList.next = current;


      for(int j=0; current !=null &&  j< k; j++) {
        previous = current;
        current = current.next;
      }

      if(current==null) {
        break;
      }
    }
    return head;
  }
}
