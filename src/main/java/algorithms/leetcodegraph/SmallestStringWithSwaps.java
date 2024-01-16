package algorithms.leetcodegraph;

//You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
//
//You can swap the characters at any pair of indices in the given pairs any number of times.
//
//Return the lexicographically smallest string that s can be changed to after using the swaps.

//Input: s = "dcab", pairs = [[0,3],[1,2]]
//Output: "bacd"
//Explanation:
//Swap s[0] and s[3], s = "bcad"
//Swap s[1] and s[2], s = "bacd"

//Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
//Output: "abcd"
//Explanation:
//Swap s[0] and s[3], s = "bcad"
//Swap s[0] and s[2], s = "acbd"
//Swap s[1] and s[2], s = "abcd"

//Input: s = "cba", pairs = [[0,1],[1,2]]
//Output: "abc"
//Explanation:
//Swap s[0] and s[1], s = "bca"
//Swap s[1] and s[2], s = "bac"
//Swap s[0] and s[1], s = "abc"

import java.util.*;

//Constraints:
//
//1 <= s.length <= 10^5
//0 <= pairs.length <= 10^5
//0 <= pairs[i][0], pairs[i][1] < s.length
//s only contains lower case English letters.
public class SmallestStringWithSwaps {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        // From connected pairs we create connected components. Within the connected component, we can swap any character with any character
        // e.g if 0-1 and 1-4, In this case we can swap 0 with 4 directly
        // After forming lists of connected components, we sort the characters at those indices lexicographically.
        // Within the connected component, Each character is placed at its correct place in alphabetical order
        // Using Disjoint set to form connected components.
        OptimizedDisjointSet ds = new OptimizedDisjointSet(s.length());
        for (List<Integer> pair : pairs) {
            ds.union(pair.get(0), pair.get(1));
        }
        // Now we form connected components, which characters belong to same connected component? The ones with same root
        // So we create a Map with Root as key and all other nodes of the connected components aggregated ina list
        Map<Integer, List<Integer>> rootToNodes = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer root = ds.find(i);
            List<Integer> component = rootToNodes.getOrDefault(root, new ArrayList<>());
            component.add(i);//Note that this list is already sorted
            rootToNodes.put(root, component);
        }
        // for each component, get the chars from String and sort the indices as well as chars
        // Set the indices of result array
        char[] result = new char[s.length()];
        for (Map.Entry<Integer, List<Integer>> entry : rootToNodes.entrySet()) {
            List<Integer> indices = entry.getValue();
            List<Character> chars = new ArrayList<>();
            for (int i : indices) {
                chars.add(s.charAt(i));
            }
            chars.sort(Comparator.naturalOrder());
//            indices.sort(Comparator.naturalOrder());
// We don't need to sort the list of indices in this approach because,
//            as we iterate over vertices in ascending order,
//            we will store the vertices that belong to the same component in ascending order.
            for (int i = 0; i < indices.size(); i++) {
                result[indices.get(i)] = chars.get(i);
            }
        }
        return new String(result); // NOTE: creating a String from char array
    }

    public String smallestStringWithSwaps2(String s, List<List<Integer>> pairs) {
        // we can also use DFS to create connected components
        //using recursive DFS
        // first we have to create adjacency list
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            adjacencyList.add(new ArrayList<>()); // every Node in a graph has an adjacency list
        }
        for (List<Integer> pair : pairs) {
            adjacencyList.get(pair.get(0)).add(pair.get(1));
            adjacencyList.get(pair.get(1)).add(pair.get(0));
        }
        // create a visited array. Only if the index is not yet visited we perform DFS on it
        // intuition: Once a node is visited,  all its neighbours are also visited. so we can create a list add all neighbouring nodes
        boolean[] visited = new boolean[s.length()];
        List<List<Integer>> connectedComponents = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (!visited[i]) {
                List<Integer> connected = new ArrayList<>();
                dfs(i, visited, adjacencyList, connected);
                connectedComponents.add(connected);
            }
        }
        char[] result = new char[s.length()];
        for (int i = 0; i < connectedComponents.size(); i++) {// no need of this loop, we can prepare lists in the loop above by passing them to dfs method
            List<Integer> indices = connectedComponents.get(i);
            List<Character> chars = new ArrayList<>();
            for (int j : indices) {
                chars.add(s.charAt(j));
            }
            chars.sort(Comparator.naturalOrder());
            indices.sort(Comparator.naturalOrder());
            for (int j = 0; j < indices.size(); j++) {
                result[indices.get(j)] = chars.get(j);
            }
        }
        return new String(result); // NOTE: creating a String from char array
    }

    private void dfs(int i, boolean[] visited, List<List<Integer>> adjacencyList, List<Integer> connected) {
        visited[i] = true;
        connected.add(i);
        for (int neighbour : adjacencyList.get(i)) {
            if (!visited[neighbour]) {
                dfs(neighbour, visited, adjacencyList, connected);
            }
        }
    }
}
