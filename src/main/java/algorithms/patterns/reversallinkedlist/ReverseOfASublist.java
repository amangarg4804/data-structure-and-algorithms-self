package algorithms.patterns.reversallinkedlist;
// Given the head of a LinkedList and two positions ‘p’ and ‘q’, reverse the LinkedList from position ‘p’ to ‘q’.
public class ReverseOfASublist {

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    ListNode reversedHead = reverse(head, 2, 4);
    while (reversedHead != null) {
      System.out.println(reversedHead.value);
      reversedHead = reversedHead.next;
    }
  }

  public static ListNode reverse(ListNode head, int p, int q) {
    if (p==q) {
      return head;
    }
    ListNode currentNode = head;
    ListNode lasNodeOfFirstPart = null;
    int i = 0;
    while (i < p-1) {
      lasNodeOfFirstPart = currentNode;
      currentNode = currentNode.next;
      i++;
    }
    ListNode lasNodeOfSubList = currentNode;
    ListNode previous = null;
    while(i< q) {
      ListNode next = currentNode.next;
      currentNode.next = previous;
      previous = currentNode;
      currentNode = next;
      i++;
    }

    if(lasNodeOfFirstPart != null) {
      lasNodeOfFirstPart.next = previous;
    } else {
      head = previous;
    }
    lasNodeOfSubList.next = currentNode;
    return head;
  }
}
