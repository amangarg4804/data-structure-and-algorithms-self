package algorithms.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCacheUsingNode {// beats 5%
    Map<Integer, Node> map = new HashMap<>();
    Deque<Node> q = new LinkedList<>();
    int capacity;
    public LRUCacheUsingNode(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        q.remove(node);
        q.addFirst(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            q.remove(map.get(key));
            map.remove(key);
        }
        if(map.size() == capacity) {
            Node lastNode = q.removeLast();
            map.remove(lastNode.key);
        }
        Node newNode = new Node(key, value);
        q.addFirst(newNode);
        map.put(key, newNode);
    }

    class Node {
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
