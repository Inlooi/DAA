class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // If one of the subtrees is null, we must take the depth of the non-null subtree
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}