package algorithms.leetcodebst;

public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length-1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if(start<0 || end >= nums.length || start > end) {
            return null;
        }

        if(start ==end ) {
            return new TreeNode(nums[start]);
        }
        int mid = start + (end-start)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid-1);
        root.right =sortedArrayToBST(nums, mid+1, end);
        return root;
    }
}
