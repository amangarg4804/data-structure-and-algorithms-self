package algorithms.leetcodetrie;

// bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
//There will be at most 3 dots in word for search queries.

import java.util.LinkedList;
import java.util.Queue;

public class WordDictionary {
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode current = root;
        for(int i=0; i< word.length(); i++){
            int index = word.charAt(i) -'a';
            if(current.children[index] ==null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isWord = true;
    }

    public boolean search(String word) {
        Queue<TrieNode> q = new LinkedList<>();
        q.offer(root);
        for(int i=0; i< word.length(); i++) {
            int size = q.size();
            if(size==0) {
                return false;
            }
//            if(word.charAt(i)=='.') {  // initial version, also works. Refactored to be more concise
//                for(int j=0; j<size; j++) {
//                    TrieNode current = q.poll();
//                    for(TrieNode child : current.children) {
//                        if(child !=null){
//                            q.offer(child);
//                        }
//                    }
//                }
//            } else {
//                for(int j=0; j< size; j++) {
//                    TrieNode current = q.poll();
//                    if(current.children[word.charAt(i)-'a'] !=null) {
//                        q.offer(current.children[word.charAt(i)-'a']);
//                    }
//                }
//
//            }
            for(int j=0; j< size; j++) {
                TrieNode current = q.poll();
                if(word.charAt(i)=='.') {
                    for(TrieNode child : current.children) {
                        if(child !=null){
                            q.offer(child);
                        }
                    }
                } else if(current.children[word.charAt(i)-'a'] !=null) {
                    q.offer(current.children[word.charAt(i)-'a']);
                }
            }

        }

        while (!q.isEmpty()){
            TrieNode node = q.poll();
            if(node.isWord) {
                return true;
            }
        }

        return false;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }
}
