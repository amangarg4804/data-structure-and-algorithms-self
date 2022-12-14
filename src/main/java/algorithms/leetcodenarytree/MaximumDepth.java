package algorithms.leetcodenarytree;

public class MaximumDepth {

    int answer;

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        return maxDepth(root, 0);
    }

    private int maxDepth(Node root, int currentDepth) {
        if (root == null) {
            return answer;
        }
        answer = Math.max(answer, currentDepth + 1);
        for (Node child : root.children) {
            maxDepth(child, currentDepth + 1);
        }
        return answer;
    }

    private int maxDepth2(Node root, int currentDepth) {
        // time: O(n)
        if (root == null) {
            return currentDepth;
        }
        int maxDepth =currentDepth+1;
        for (Node child : root.children) {
            int childDepth = maxDepth(child) +1;
            maxDepth = Math.max(childDepth, maxDepth);
        }
        return maxDepth;
    }
}
