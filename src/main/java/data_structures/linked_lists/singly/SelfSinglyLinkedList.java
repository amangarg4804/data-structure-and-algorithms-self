package data_structures.linked_lists.singly;

public class SelfSinglyLinkedList {

    Node head;

    private Node tail;

    private int length;

    public void prepend(Integer data) {
        Node node = createNode(data);
        if(this.head == null) {
            this.head = node;
            this.tail= node;
        } else {
            node.next = head;
            this.head = node;
        }
        length++;
    }

    public void add(Integer data) {
        append(data);
    }

    public void append(Integer data) {
        Node newNode = createNode(data);
        if(this.head == null) {
            this.head = newNode;
            this.tail= newNode;
        } else {
            Node current = this.head;
            while (current.next!= null) {
                current = current.next;
            }
            current.next = newNode;
            this.tail = newNode;
        }
        length++;
    }

    public void add (int index, Integer data) {
        if(index == 0) {
            prepend(data);
        } else {
            Node newNode = createNode(data);
            Node leader = head;
            while (--index > 0 ) {
                leader = leader.next;
            }
            Node existing = leader.next;
            leader.next = newNode;
            newNode.next = existing;
            length++;
        }
    }

    public void removeNodeAt(int index) {
        if(index ==0) {
            if(head!= null) {
                this.head = this.head.next;
                length--;
                return;
            } else {
                return;
            }
        }
        Node leader = this.head;
        while (--index > 0 ) {
            leader = leader.next;
        }
        leader.next = leader.next.next;
        length--;
    }


    public void remove(Integer data) {
        if (this.head == null ) {
            return;
        }
        if(this.head.getData().equals(data)) {
            this.head = head.next;
            length--;
        } else {
            Node leader = this.head;
            Node current = this.head.next;
            while(current != null) {
                if(current.getData().equals(data)) {
                    leader.next = current.next;
                    length--;
                    break;
                } else {
                    leader = leader.next;
                    current = current.next;
                }
            }
        }
    }


    private Node createNode(Integer data) {
        return new Node(data);
    }

    @Override
    public String toString() {
        return "SelfSinglyLinkedList{" +
                "head=" + head +
                ", tail=" + tail +
                ", length=" + length +
                '}';
    }

    public static class Node {
        Integer data;
        Node next;

        public Node(Integer data) {
            this.data = data;
        }

        public Integer getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        SelfSinglyLinkedList linkedList = new SelfSinglyLinkedList();
        linkedList.prepend(1);
        System.out.println(linkedList);
        linkedList.append(2);
        System.out.println(linkedList);
        linkedList.prepend(3);
        System.out.println(linkedList);
        linkedList.add(1, 4);
        System.out.println(linkedList);
        linkedList.remove(3);
        System.out.println(linkedList);

        System.out.println("Reversing");
        ReverseALinkedList.reverse(linkedList);
        System.out.println(linkedList);


    }
}
