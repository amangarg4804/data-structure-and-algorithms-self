package algorithms.leetcodetrie;

public class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for(int i=0; i< word.length() ; i++) {
            int index = word.charAt(i) -'a'; // NOTE: subtract 'a' to get correct index a-'a' =0, b-'a' = 1, c-'a' = 2 and so on
            if(current.children[index] == null) { // node's value is defined by which index is set. Each index corresponds to an alphabet
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for(int i=0; i< word.length(); i++) {
            int index = word.charAt(i) -'a';
            if(current.children[index]==null) {
                return false;
            }
            current = current.children[index];
        }
        return current.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for(int i=0; i< prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if(current.children[index] ==null) {
                return false;
            }
            current = current.children[index];
        }
        return true;
    }
}
