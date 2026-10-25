package algorithms.leetcode.graph;

// There is a new alien language that uses the English alphabet. However, the order of the letters is unknown to you.
//
//You are given a list of strings words from the alien language's dictionary. Now it is claimed that the strings in words are sorted lexicographically by the rules of this new language.
//
//If this claim is incorrect, and the given arrangement of string in words cannot correspond to any order of letters, return "".
//
//Otherwise, return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there are multiple solutions, return any of them.
//
//
//
//Example 1:
//
//Input: words = ["wrt","wrf","er","ett","rftt"]
//Output: "wertf"
//Example 2:
//
//Input: words = ["z","x"]
//Output: "zx"
//Example 3:
//
//Input: words = ["z","x","z"]
//Output: ""
//Explanation: The order is invalid, so return "".
//
//
//Constraints:
//
//1 <= words.length <= 100
//1 <= words[i].length <= 100
//words[i] consists of only lowercase English letters.

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AlienDictionary {

    public String alienOrder(String[] words) {
// 4 data structures
        // 1. Adjacency list
        // 2. InDegree array
        // 3. Queue to push nodes having indegree 0
        // 4. Result list
        // How to build indegree and adjacency list in this case?

        // words[i] consists of only lowercase English letters. So, indegree could be an array of length 26
        // But map is easier to avoid char to int conversions etc.
        Map<Character, Integer> inDegree = new HashMap<>();
        Map<Character, Set<Character>> graph = new HashMap<>();

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!inDegree.containsKey(c)) {
                    inDegree.put(c, 0);
                }
            }
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String word1 = words[i];
                String word2 = words[j];
                if (word1.length() > word2.length() && word1.startsWith(word2)) {
                    return "";
                }
                int shorterWordLength = Math.min(word1.length(), word2.length());
                for (int k = 0; k < shorterWordLength; k++) {
                    char c1 = word1.charAt(k);
                    char c2 = word2.charAt(k);
                    if (c1 == c2) {
                        Set<Character> neighbours = graph.getOrDefault(c1, new HashSet<>());
                        graph.put(c1, neighbours);
                        inDegree.put(c1, inDegree.getOrDefault(c1, 0));
                        continue;
                    }
                    // c1 is before c2 in the input array
                    //  So c1 has an outgoing edge to c2
                    Set<Character> neighbours = graph.getOrDefault(c1, new HashSet<>());
                    boolean added = neighbours.add(c2);
                    graph.put(c1, neighbours);
                    if (added) {
                        // c2 indegree to be increased
                        inDegree.put(c2, inDegree.getOrDefault(c2, 0) + 1);
                    }

                    break;
                }
            }
        }

        Queue<Character> q = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                q.offer(entry.getKey());
            }
        }
        StringBuilder result = new StringBuilder();
        System.out.println(inDegree);
        System.out.println(graph);
        while (!q.isEmpty() && result.length() <= inDegree.size()) {
            Character currentC = q.poll();
            result.append(currentC);
            System.out.println("Polled: " + currentC);
            if (!graph.containsKey(currentC)) {
                continue;
            }
            for (Character neighbour : graph.get(currentC)) {
                // reduce neighbor's indegree
                int neighbourIndegree = inDegree.get(neighbour) - 1;
                inDegree.put(neighbour, neighbourIndegree);
                if (neighbourIndegree == 0) {
                    q.offer(neighbour);
                }
            }
        }
        System.out.println(result);
        System.out.println(inDegree);
        if (result.length() != inDegree.size()) {
            System.out.println(result);
            return "";
        }
        return result.toString();

    }

    public String alienOrder2(String[] words) {
// 4 data structures
        // 1. Adjacency list
        // 2. InDegree array
        // 3. Queue to push nodes having indegree 0
        // 4. Result list
        // How to build indegree and adjacency list in this case?

        // words[i] consists of only lowercase English letters. So, indegree could be an array of length 26
        // But map is easier to avoid char to int conversions etc.
        Map<Character, Integer> inDegree = new HashMap<>();
        Map<Character, Set<Character>> graph = new HashMap<>();

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!inDegree.containsKey(c)) {
                    inDegree.put(c, 0);
                }
            }
        }

        for (int i = 0; i < words.length-1; i++) {
                String word1 = words[i];
                String word2 = words[i+1];
                if (word1.length() > word2.length() && word1.startsWith(word2)) {
                    return "";
                }
                int shorterWordLength = Math.min(word1.length(), word2.length());
                for (int k = 0; k < shorterWordLength; k++) {
                    char c1 = word1.charAt(k);
                    char c2 = word2.charAt(k);
                    if (c1 == c2) {
                        Set<Character> neighbours = graph.getOrDefault(c1, new HashSet<>());
                        graph.put(c1, neighbours);
                        inDegree.put(c1, inDegree.getOrDefault(c1, 0));
                        continue;
                    }
                    // c1 is before c2 in the input array
                    //  So c1 has an outgoing edge to c2
                    Set<Character> neighbours = graph.getOrDefault(c1, new HashSet<>());
                    boolean added = neighbours.add(c2);
                    graph.put(c1, neighbours);
                    if (added) {
                        // c2 indegree to be increased
                        inDegree.put(c2, inDegree.getOrDefault(c2, 0) + 1);
                    }

                    break;
                }
        }

        Queue<Character> q = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                q.offer(entry.getKey());
            }
        }
        StringBuilder result = new StringBuilder();
        System.out.println(inDegree);
        System.out.println(graph);
        while (!q.isEmpty() && result.length() <= inDegree.size()) {
            Character currentC = q.poll();
            result.append(currentC);
            System.out.println("Polled: " + currentC);
            if (!graph.containsKey(currentC)) {
                continue;
            }
            for (Character neighbour : graph.get(currentC)) {
                // reduce neighbor's indegree
                int neighbourIndegree = inDegree.get(neighbour) - 1;
                inDegree.put(neighbour, neighbourIndegree);
                if (neighbourIndegree == 0) {
                    q.offer(neighbour);
                }
            }
        }
        System.out.println(result);
        System.out.println(inDegree);
        if (result.length() != inDegree.size()) {
            System.out.println(result);
            return "";
        }
        return result.toString();

    }
}
