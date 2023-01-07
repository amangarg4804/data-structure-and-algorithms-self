package algorithms.leetcodetrie;

import java.util.ArrayList;
import java.util.List;

public class PalindromePairsUsingTrie {

    TrieNode root = new TrieNode();

    public List<List<Integer>> palindromePairs(String[] words) {
     List<List<Integer>> result = new ArrayList<>();

     for(int i=0; i< words.length; i++) {
        insert(words[i], i);
     }
     for(int i =0;i < words.length; i++) {
         search(words[i], result, i); // Now we are going to match each word against every word in Trie
     }
     return result;
    }

    private void search(String word, List<List<Integer>> result, int currentWordIndex) {
        TrieNode current = root;
        for(int i=0 ; i< word.length(); i++) {
            // first try to find if any word is ending at this trienode. How to find it? By checking the index is not -1
            if(current.wordIndex!=-1 && current.wordIndex !=currentWordIndex && isPalindrome(word, i, word.length()-1)) {
                result.add(List.of(currentWordIndex, current.wordIndex));
            }
                // Case 1 : We are trying to find a word which can be a suffix to current word and form a palindrome.
            // if second part of word we are iterating over is palindrome - defbcb- and trieNode contains a word fed (inserted as def in trienode)
            // So a palindrome is formed by appending the word we are iterating over with the word fed (the index of fed will be stored in Trienode). defbcb + fed
            current = current.children[word.charAt(i) - 'a'];
            if(current==null) {
                return; // if no node exists further, nothing to do
            }
        }
        // Case 2: Here also we are trying to find a suffix to current word.
        // Current word has finished at a trieNode, let's say the word was abcd.
        // At node d , if a word existed with starting part as palindrome, like brbdcba, then that word and the current word can form a palindrome
        // abcd + brbdcba
        for(int i: current.indexes) {
            if(i==currentWordIndex) {
                continue;
            }
            result.add(List.of(currentWordIndex, i));
        }

    }

    private void insert(String word, int currentWordIndex) {
        TrieNode current =root;
        for( int i=word.length() -1; i>=0; i--) { //inserting word in reverse
            int trieNodeIndex = word.charAt(i) -'a';
            if(current.children[trieNodeIndex] ==null) {
                current.children[trieNodeIndex] = new TrieNode();
            }
            if(isPalindrome(word, 0, i)) {
                current.indexes.add(currentWordIndex); // remaining string is palindrome. telling this node that there exists a word at index i for which the first part is a palindrome, for example: bcbdef. the node at d will store the index
            }
            current = current.children[trieNodeIndex];

        }
        current.indexes.add(currentWordIndex); // If given string is empty. Empty string is palindrome too
        current.wordIndex = currentWordIndex; // storing word's index as per given input in trienode
    }

    public static boolean isPalindrome(String input, int start, int end) {
        if(input == null) {
            return false;
        }

        while(start < end) {
            if(input.charAt(start) != input.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int wordIndex = -1; // This is done to avoid boolean isWord and having another variable for storing index of word. -1 for nodes that are not end of a word. 0 and mor
        List<Integer> indexes = new ArrayList<>();

    }
}

