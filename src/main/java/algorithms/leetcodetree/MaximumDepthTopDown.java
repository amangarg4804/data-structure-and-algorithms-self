package algorithms.leetcodetree;

public class MaximumDepthTopDown {

    private int answer;

    public int maxDepth(TreeNode root) {
        maxDepth(root, 1);
        return answer;
    }

    private void maxDepth(TreeNode root, int depth) {
        if(root ==null) {
            return;
        }
        if(root.left ==null && root.right ==null) {
            answer = Math.max(depth, answer);
        }
        maxDepth(root.left, depth+1);
        maxDepth(root.right, depth +1 );
    }
}
