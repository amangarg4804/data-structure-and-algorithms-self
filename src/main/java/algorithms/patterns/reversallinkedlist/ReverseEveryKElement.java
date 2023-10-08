package algorithms.patterns.reversallinkedlist;

// Given the head of a LinkedList and a number ‘k’, reverse every ‘k’ sized sub-list starting from the head.
//If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
//bp
public class ReverseEveryKElement {

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);
    head.next.next.next.next.next.next = new ListNode(7);
    head.next.next.next.next.next.next.next = new ListNode(8);

    ListNode newHead = reverseKGroup(head, 3);

    while (newHead != null) {
      System.out.println(newHead.value);
      newHead = newHead.next;
    }
  }


  public static ListNode reverseKGroup(ListNode head, int k) {
    if (k<=1 || head ==null) {
      return head;
    }
    // 0-> 1-> 2-> 3-> 4-> 5-> 6-> 7->8
    // The group can be of 3 or 4 or 5, in one iteration we can only change one connection. So we require a while loop from 1 to k
    // At first, the connection between 2 and 1 wil be reversed.

    // After first iteration
    // 0->3->2->1 4-> 5-> 6-> 7 -> 8
    //TODO: solve iteratively
    int length = calculateLength(head);
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    ListNode previous = dummyHead;
    ListNode current;
    ListNode next;
    while (length >=k) {
      current = previous.next;//1
      next = current.next; //2
      for (int i =1; i< k; i++) {
        // here we alter connections
//        current.next =
      }
      length = length - k;
      previous = current;
    }
    return dummyHead.next;
  }

  private static int calculateLength(ListNode head) {
    int length = 0;
    while (head!=null) {
      length++;
      head = head.next;
    }
    return length;
  }

}
