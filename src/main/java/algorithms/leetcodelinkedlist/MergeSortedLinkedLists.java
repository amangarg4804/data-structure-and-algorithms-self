package algorithms.leetcodelinkedlist;

public class MergeSortedLinkedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode dummy = new ListNode(0);

        merge(dummy, list1, list2);
        return dummy.next;
    }

    private void merge(ListNode current, ListNode list1, ListNode list2) {
        if(list1==null) {
            current.next = list2;
            return;
        }
        if(list2 == null) {
            current.next = list1;
            return;
        }
        if(list1.val <= list2.val) {
            current.next = list1;
            merge(current.next, list1.next, list2);
        } else {
            current.next = list2;
            merge(current.next, list1, list2.next);
        }

    }
}
