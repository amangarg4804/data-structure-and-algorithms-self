package algorithms.leetcodetrie;

import java.util.LinkedList;
import java.util.Queue;

class MapSum {
    TrieNode root;
    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode current = root;
        for(int i=0; i< key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if(current.children[index] ==null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.value = val;
    }

    public int sum(String prefix) {
        TrieNode current = root;
        for(int i=0; i< prefix.length(); i++) {
            int index = prefix.charAt(i) -'a';
            if(current.children[index] ==null) {
                return 0;
            }
            current = current.children[index];
        }
        Queue<TrieNode> q = new LinkedList<>();
        q.offer(current);
        int sum =0;
        while (!q.isEmpty()) {
            TrieNode node = q.poll();
            sum+= node.value;
            for(int i=0; i< node.children.length; i++) {
                if(node.children[i] !=null) {
                    q.offer(node.children[i]);
                }
            }
        }
        return sum;
    }


    private class TrieNode {
        TrieNode[] children = new TrieNode[26];; // we don't need char here, char is represented by index
        int value;
    }
}
