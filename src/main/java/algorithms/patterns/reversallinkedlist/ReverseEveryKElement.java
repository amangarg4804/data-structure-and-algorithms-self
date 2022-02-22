package algorithms.patterns.reversallinkedlist;

// Given the head of a LinkedList and a number ‘k’, reverse every ‘k’ sized sub-list starting from the head.
//If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
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

    ListNode newHead = reverse(head, 3);

    while (newHead != null) {
      System.out.println(newHead.value);
      newHead = newHead.next;
    }
  }

  public static ListNode reverse(ListNode head, int k) {
    if (k<=1 || head ==null) {
      return head;
    }
    ListNode currentNode = head;
    ListNode previous = null;
    while(true) {
      ListNode lasNodeOfPreviousPart = previous;
      ListNode lasNodeOfSubList = currentNode;
      int i = 0;
      while (currentNode != null && i<k) {
        ListNode next = currentNode.next;
        currentNode.next = previous;
        previous = currentNode;
        currentNode = next;
        i++;
      }

      if (lasNodeOfPreviousPart != null) {
        lasNodeOfPreviousPart.next = previous;
      } else {
        head = previous;
      }
      lasNodeOfSubList.next = currentNode;
      if(currentNode == null) {
        break;
      }
      previous = lasNodeOfSubList;
    }
    return head;
  }
}
