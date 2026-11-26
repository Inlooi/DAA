class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        // Walk down the tree: go left if val is smaller, right if bigger
        while (root != null && root.val != val) {
            root = (val < root.val) ? root.left : root.right;
        }
        return root; // either the matching node or null
    }
}
