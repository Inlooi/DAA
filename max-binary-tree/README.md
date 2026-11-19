## 1. Problem
Find the maximum depth of a binary tree, defined as the number of nodes along the longest path from the root node down to the farthest leaf node.

## 2. Approach
Using recursion, the depth of a null node is `0`. For any existing node, I recursively compute the maximum depth of its left and right subtrees, take the larger of the two values, and add `1` to account for the current node.

**Written Tracing (Example: `[3, 9, 20, null, null, 15, 7]`):**
1. `maxDepth(3)` calls `maxDepth(9)` and `maxDepth(20)`.
2. `maxDepth(9)` has two null children -> returns `Math.max(0, 0) + 1 = 1`.
3. `maxDepth(20)` calls `maxDepth(15)` (returns `1`) and `maxDepth(7)` (returns `1`) -> returns `Math.max(1, 1) + 1 = 2`.
4. Back at `3`: returns `Math.max(1, 2) + 1 = 3`.

**Challenges:** 
Making sure the base case returned `0` for `null` rather than `1`, which would have overcounted depth by one level.

## 3. Time & Space Complexity
* **Time Complexity:** $O(n)$ — Each node is visited once to compute its height.
* **Space Complexity:** $O(h)$ (worst case $O(n)$) — Due to the recursion stack, where $h$ is the height of the tree.

## 4. Reflection / Improvement
* **Is there a more efficient approach?** Asymptotically, $O(n)$ time is optimal because every node must be inspected. However, an iterative Breadth-First Search (BFS) using a queue avoids potential stack overflow errors on deeply skewed trees.
* **What would you need to change?** Use a `Queue<TreeNode>` to process nodes level by level and increment a counter after each level.
* **Improved Complexity:** Still $O(n)$ time and $O(w)$ space (where $w$ is the maximum width of the tree).