class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        // Node is too small: everything on its left is smaller too, so only go right
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        // Node is too big: everything on its right is bigger too, so only go left
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        // Node is inside [low, high]: count it and check both sides
        return root.val
                + rangeSumBST(root.left, low, high)
                + rangeSumBST(root.right, low, high);
    }
}
