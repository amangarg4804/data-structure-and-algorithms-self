package algorithms.leetcoderecursion;

public class MergeTwoSortedLinkedLists {

    private class ListNode {
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
    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        if(list1 ==null) {
            return list2;
        }
        if(list2 ==null) {
            return list1;
        }
        if(list1.val < list2.val) {
            list1.next = mergeTwoListsRecursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoListsRecursive(list2.next, list1);
            return list2;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;

            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        current.next = list1 ==null ? list2: list1;
//        while (list1 != null) {
//            current.next = list1;
//            list1 = list1.next;
//            current = current.next;
//        }
//        while (list2 != null) {
//            current.next = list2;
//            list2 = list2.next;
//            current = current.next;
//        }
        return dummyHead.next;
    }


}
