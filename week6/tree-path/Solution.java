import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root != null) {
            buildPaths(root, "", paths);
        }
        return paths;
    }

    private void buildPaths(TreeNode node, String currentPath, List<String> paths) {
        currentPath += node.val;
        if (node.left == null && node.right == null) {
            paths.add(currentPath);
        } else {
            currentPath += "->";
            if (node.left != null) buildPaths(node.left, currentPath, paths);
            if (node.right != null) buildPaths(node.right, currentPath, paths);
        }
    }
}
