package algorithms.leetcodeTop100;

public class RemoveNthNodeFromEndOfList {

  public static void main(String[] args) {

  }

  static class ListNode {
    int val;
    ListNode next;
    ListNode (int x) {
      val =x;
    }
  }

  public ListNode removeNthFromEnd(ListNode head, int n) {
    //We just need to go to the node before the one to be removed  and
    // to maintain the distance n from last we can take two-pointers and maintain the same distance between them,
    // when one reaches null other reaches the required position and gets the job done
    ListNode dummy_head = new ListNode(0);
    dummy_head.next =head;
    ListNode fast = dummy_head;
    ListNode slow = dummy_head;
    // distance has to be 1 more than n because we want to slow to reach previous node
    for(int i =1 ; i<= n+1; i++) {
      fast = fast.next;
    }

    while (fast!=null) {
      fast = fast.next;
      slow = slow.next;
    }
    slow.next = slow.next.next;
    return dummy_head.next;
  }
}

