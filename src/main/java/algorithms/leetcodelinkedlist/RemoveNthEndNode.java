package algorithms.leetcodelinkedlist;

public class RemoveNthEndNode {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // two pass using dummyhead
        int length =getlength(head);
        // given that length is greater than n
        int index = length-n; // index of node to be deleted
        // 2->5->10->9
        // if n =2, length =4, index = 4-2 =2.
        // we need to get previous node
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode previous = dummyHead;

        for(int i =0; i< index; i++) {
         previous = previous.next;
        }
        ListNode toBeDeleted = previous.next;
        previous.next = toBeDeleted.next; //assuming that node to be deleted exists
        return dummyHead.next;
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        // two pass without dummyhead
        int length =getlength(head);
        // given that length is greater than n
        int index = length-n; // index of node to be deleted
        if(index ==0) {
            return head.next;
        }
        // 2->5->10->9
        // if n =2, length =4, index = 4-2 =2.
        // we need to get previous node
        ListNode previous = head;
        for(int i =0; i< index-1; i++) { //moving only index-2 times to get previous node
            previous = previous.next;
        }
        ListNode toBeDeleted = previous.next;
        previous.next = toBeDeleted.next; //assuming that node to be deleted exists
        return head;
    }

    private static int getlength(ListNode current) {
        int length =0;
        while (current !=null) {
            current = current.next;
            length++;
        }
        return length;
    }

    public ListNode removeNthFromEnd3(ListNode head, int n) {
        // one pass
        // use two pointers and maintain n distance between them
        // 2->5->10->9
        // if n =2
        ListNode fast = head;
        while (n!=0) {
            fast =fast.next; // for n=2, fast moves twice and reaches 10
            n--;
        }
        ListNode slow = head;
        if(fast==null) { // we are trying to delete head node. Take n=4 in above example
            return head.next;
        }
        // We want slow to reach previous of the node to be deleted. So, we move slow till fast.next!=null
        while (fast.next!=null) { // loop runs only once for above example, stops when fast reaches 9
            fast=fast.next;
            slow = slow.next;
        }

        ListNode toBeDeleted = slow.next;
        slow.next = toBeDeleted.next; //assuming that node to be deleted exists
        return head;
    }

    public ListNode removeNthFromEnd4(ListNode head, int n) {
        // one pass using dummyNode
        // use two pointers and maintain n distance between them
        // 2->5->10->9
        // if n =2
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode fast = dummyNode;

        while (n!=0) {
            fast =fast.next; // for n=2, fast moves twice and reaches 5. For n=4, fast reaches 9
            n--;
        }
        ListNode slow = dummyNode;
        // We want slow to reach previous of the node to be deleted. So, we move slow till fast.next!=null
        while (fast.next!=null) { // loop runs twice for n=2, stops when fast reaches 9. For n=4 loop doesn't run
            fast=fast.next;
            slow = slow.next;
        }

        ListNode toBeDeleted = slow.next;
        slow.next = toBeDeleted.next; //assuming that node to be deleted exists, head is not null
        return dummyNode.next;
    }

}
