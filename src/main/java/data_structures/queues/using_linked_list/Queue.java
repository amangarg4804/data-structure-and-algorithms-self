package data_structures.queues.using_linked_list;

public class Queue {
    private MySinglyLinkedNode first;
    private MySinglyLinkedNode last;
    private int length;

    public void enqueue(Integer i) {
        MySinglyLinkedNode newNode = new MySinglyLinkedNode(i);
        if(first == null) {
            this.first = newNode;
            this.last = newNode;
        } else {
            last.setNext(newNode);
            this.last = newNode;

        }
        length++;
    }

    public Integer dequeue() {
        if(first == null) {
            throw  new IllegalStateException();
        }
        Integer firstOut  = this.first.getValue();
        this.first = this.first.getNext();
        length--;
        return firstOut;
    }

}
