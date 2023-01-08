package algorithms.leetcodelinkedlist;


public class MyLinkedList {
    ListNode head;
    public MyLinkedList() {

    }

    public int get(int index) {
        if(head ==null) {
            return -1;
        }
        ListNode current = head;
        for(int i=0; i< index; i++){
            if(current.next ==null) { // we can avoid this condition if we store length of linkedlist and return -1 if index is >=length
                return -1;
            }
            current = current.next;
        }
        return current.val;
    }

    public void addAtHead(int val) {
        ListNode newHead = new ListNode(val);
        newHead.next = head;
        head = newHead;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        if(head ==null) { // we could also save tail pointer
            head = newNode;
            return;
        }

        ListNode current = head;
        while (current.next!=null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public void addAtIndex(int index, int val) { // Node has to be inserted before the node at given index
        ListNode newNode = new ListNode(val);
        if(index ==0) {
            newNode.next = head;
            head = newNode;
            return;
        }
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode previousNode = dummyNode;// We want current to move to previous node. If index is 2, we want current to be at 1
        for (int i=0; i< index ;i++) { // to avoid dummyNode , we could initialize previous with head and run loop till i <index-1
            if(previousNode.next==null) {
                return;
            }
            previousNode = previousNode.next;
        }
        ListNode nextNode = previousNode.next;
        previousNode.next = newNode;
        newNode.next = nextNode;

    }

    public void deleteAtIndex(int index) {
        if(head ==null) {
            return;
        }
        if(index ==0){
            head = head.next;
            return;
        }
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode previousNode = dummyNode;// We want current to move to previous node. If index is 2, we want current to be at 1
        for (int i=0; i< index ;i++) {
            if(previousNode.next==null) {
                return;
            }
            previousNode = previousNode.next;
        }
        ListNode toBeDeletedNode = previousNode.next;// could be null
        if(toBeDeletedNode !=null) {
            previousNode.next = toBeDeletedNode.next;
        }


    }
    private class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}


