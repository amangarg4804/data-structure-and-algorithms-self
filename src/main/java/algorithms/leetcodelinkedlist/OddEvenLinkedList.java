package algorithms.leetcodelinkedlist;

public class OddEvenLinkedList {

    // 1->2->3->4->5->6
    // 1->3->2->4->5->6
    // 1->3->5->2->4->6
    public ListNode oddEvenList(ListNode head) {
        if(head ==null) {
            return head;
        }
        ListNode lastOdd = head;
        ListNode lastEven = head.next;
        while (lastEven != null && lastEven.next!=null) {
            ListNode nextOdd = lastEven.next;
            ListNode nextEven = lastEven.next.next; // could be null. Can't use nextOdd.next here, it won't be correct in 2nd iteration
            ListNode firstEven = lastOdd.next;
            lastOdd.next = nextOdd;
            nextOdd.next= firstEven;
            lastEven.next = nextEven;
            lastOdd= nextOdd;
            lastEven = nextEven;

        }
        return head;
    }
}
