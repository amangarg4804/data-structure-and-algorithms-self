package algorithms.leetcodeTop100;

public class SwapNodesInPairs {

  public ListNode swapPairs(ListNode head) {
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    ListNode current = dummyHead;
    while (current.next !=null && current.next.next !=null) {
      ListNode firstNode = current.next;
      ListNode secondNode = current.next.next;
      firstNode.next = secondNode.next;
      current.next = secondNode;
      current.next.next = firstNode;
      current = current.next.next;
    }
    return dummyHead.next;
  }

  public static class ListNode {
    int val;
    ListNode next;
    ListNode() {
    }
    ListNode(int val) {
      this.val = val;
    }
    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}
