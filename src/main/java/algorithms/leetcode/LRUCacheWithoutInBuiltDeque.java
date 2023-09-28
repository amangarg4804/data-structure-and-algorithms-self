package algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

//  This solution still uses doubly linked list, just that not the java built in Deque
public class LRUCacheWithoutInBuiltDeque {// beats 74%
    Map<Integer, Node> map = new HashMap<>();
    int capacity;
    Node head;
    Node tail;
    public LRUCacheWithoutInBuiltDeque(int capacity) {
        this.capacity = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        //head and tail are fixed. They are not part of the cache. Key 0 can be used in cache too. It will be part of the map.
        // It doesn't matter if multiple Nodes have key, value 0, what matters is key in map
        // when we are creating head and tail nodes, we are not adding any key in map, so these are not part of cache
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        remove(node);
        add(node);
        return node.value;
    }
    private void remove(Node node ) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private  void add(Node node) {
        map.put(node.key, node);
        // while adding the node, we are adding to the node at head.next position
        // so the previous node of this node should be head
        // next node of this node should be head.next
        Node nextNode = head.next;
        node.next = nextNode;
        nextNode.prev = node;
        head.next = node;
        node.prev = head;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            remove(map.get(key));
        }
        if(map.size() == capacity) {
            remove(tail.prev); // tail.prev is the least recently used node. tail.prev is last node in this linked list. tail is just a dummy node
        }
        add(new Node(key, value));
    }

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
