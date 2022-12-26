package algorithms.leetcodetrie;

public class TrieUsingMap {

    TrieNodeMap root;
    public TrieUsingMap() {
        root = new TrieNodeMap();
    }

    public void insert(String word) {
        TrieNodeMap current = root;
        for(int i=0; i< word.length() ; i++) {
            if(current.map.get(word.charAt(i)) == null) {
                current.map.put(word.charAt(i), new TrieNodeMap());
            }
            current = current.map.get(word.charAt(i));
        }
        current.isWord = true;
    }

    public boolean search(String word) {
        TrieNodeMap current = root;
        for(int i=0; i< word.length(); i++) {
            if(current.map.get(word.charAt(i) )==null) {
                return false;
            }
            current = current.map.get(word.charAt(i));
        }
        return current.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNodeMap current = root;
        for(int i=0; i< prefix.length(); i++) {
            if(current.map.get(prefix.charAt(i)) ==null) {
                return false;
            }
            current = current.map.get(prefix.charAt(i));
        }
        return true;
    }
}
