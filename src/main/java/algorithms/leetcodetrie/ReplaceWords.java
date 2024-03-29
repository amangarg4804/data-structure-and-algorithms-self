package algorithms.leetcodetrie;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReplaceWords {
    TrieNode root = new TrieNode();

    public static void main(String[] args) {
        new ReplaceWords().replaceWords(List.of("cat", "bat", "rat"), "the cattle was rattled by the battery");
    }

    public String replaceWords(List<String> dictionary, String sentence) {

        buildTrie(dictionary);

        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            TrieNode current = root;
            for (int j = 0; j < words[i].length(); j++) {
                int index = words[i].charAt(j) - 'a';
                if (current.children[index] == null) { // a character in word exists that doesn't match any prefixes in dictionary
                    break;
                } else {
                    if (current.children[index].value != null) { // dictionary contains starting alphabets of given word, no need to go any further, we have found replacemnt
                        words[i] = current.children[index].value;// other way could be to not store value in Trie and use a string builder here to prepare the replacement string
                        break;
                    }
                    current = current.children[index];
                }
            }
        }
        return String.join(" ", words);
    }

    private void buildTrie(List<String> dictionary) {
        for (String s : dictionary) {
            TrieNode current = root;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                int index = s.charAt(i) - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();

                }
                current = current.children[index];
                sb.append(s.charAt(i));
            }
            current.value = sb.toString();
        }
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String value;

    }

    public String replaceWords1(List<String> dictionary, String sentence) {
        // bruteforce n*m*n
        Collections.sort(dictionary); // after sorting a will appear before aa
        String[] words = sentence.split(" ");
        for(int i=0; i< words.length; i++) {
            String word = words[i];
            for(String prefix : dictionary) {
                if(word.startsWith(prefix)) {
                    words[i] = prefix;
                    break;
                }
            }
        }
        return String.join(" ", words);
    }

    public String replaceWords2(List<String> dictionary, String sentence) {
        // bruteforce n*m*n
        Set<String> set = new HashSet<>(dictionary);
        String[] words = sentence.split(" ");
        for(int i=0; i< words.length; i++) {
            for(int j=1; j< words[i].length();j++) {
                String prefix = words[i].substring(0, j);
                if(set.contains(prefix)) {
                    words[i] = prefix;
                    break;
                }
            }
        }
        return String.join(" ", words);
    }
}