package algorithms.leetcodetrie;

public class WordDictionaryRecursive {
    TrieNode root = new TrieNode();

    public void addWord(String word) {
        TrieNode current = root;
        for(int i=0;i< word.length();i++) {
            int index = word.charAt(i) -'a';
            if(current.children[index] ==null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isWord = true;
    }

    public boolean search(String word) {
        return searchRecursive(root, word, 0);
    }

    private boolean searchRecursive(TrieNode root, String word, int i) {
        if(i==word.length()) {
            return root.isWord;
        }
        if(word.charAt(i) =='.') {
            for(TrieNode child : root.children) {
                if(child != null && searchRecursive(child, word, i+1)) {
                    return true;
                }
            }
            return false;
        }

        if(root.children[word.charAt(i)-'a'] ==null) {  // code execution will reach here only if current chart is not .
            return false;
        }
        return searchRecursive(root.children[word.charAt(i) - 'a'], word, i+ 1);

    }

    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }

}
