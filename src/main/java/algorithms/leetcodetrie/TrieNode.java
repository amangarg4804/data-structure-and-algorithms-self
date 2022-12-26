package algorithms.leetcodetrie;

public class TrieNode {
    public static final int N  =26; // lowercase english characters only
    boolean isWord;
    TrieNode[] children = new TrieNode[N];
}
