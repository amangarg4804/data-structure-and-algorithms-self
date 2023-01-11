package algorithms.leetcodelinkedlist;

public class MyDoublyLinkedList {
    DoublyLinkedListNode head;

    public MyDoublyLinkedList() {

    }

    public int get(int index) {
        if(index==0) {
            if(head ==null) {
                return -1;
            }
            return head.val;
        }
        DoublyLinkedListNode current = head;
        for(int i=0; i< index; i++) {
            if(current.next==null) {
                return -1;
            }
            current = current.next;
        }
        return current.val;
    }

    public void addAtHead(int val) {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(val);
        if(head==null) {
            head = newNode;
            return;
        }
        head.prev = newNode;
        newNode.next = head;
        head = newNode;
    }

    public void addAtTail(int val) {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(val);
        if(head==null) {
            head = newNode;
            return;
        }
        DoublyLinkedListNode current = head;
        while (current.next!=null) {
            current = current.next;
        }
        current.next = newNode;
        newNode.prev = current;
    }

    public void addAtIndex(int index, int val) {
        // Add a node of value val before the indexth node in the linked list.
        // If index equals the length of the linked list, the node will be appended to the end of the linked list.
        // If index is greater than the length, the node will not be inserted.
        if(index ==0) {
            addAtHead(val);
            return;
        }
        DoublyLinkedListNode current = head;
        int length =0;
        for(int i=0; i< index; i++) {
            length++;
            if(current ==null ) {
                return;
            }
            if(current.next==null) { // it would be less ugly if we track length of linked list
                if(index==length){
                    addAtTail(val);
                }
                return;
            }
            current = current.next;
        }
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(val);
        // current is the node at given index or the last node
        DoublyLinkedListNode previous = current.prev;
        newNode.next =current;
        newNode.prev = previous;
        current.prev = newNode;
        previous.next = newNode;
    }

    public void deleteAtIndex(int index) {
        if(index ==0) {
            if(head ==null) {
                return;
            }
            if(head.next !=null) {
                head.next.prev = null;
            }
            head = head.next;
            return;
        }
        DoublyLinkedListNode current = head;
        for(int i=0; i< index; i++) {
            if(current.next==null) {
                return;
            }
            current = current.next;
        }

        current.prev.next = current.next;
        if(current.next!=null) {
            current.next.prev = current.prev;
        }

    }

    private static class DoublyLinkedListNode {

        DoublyLinkedListNode prev;
        DoublyLinkedListNode next;
        int val;

        public DoublyLinkedListNode(int val) {
            this.val = val;
        }
    }
}
