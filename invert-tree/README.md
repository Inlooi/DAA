## 1. Problem
Invert (mirror) a binary tree so that every left child becomes a right child and every right child becomes a left child.

## 2. Approach
Using postorder-style recursion, if the root is `null`, return `null`. Otherwise, save a reference to `root.left`, assign `root.left` to the inverted `root.right`, and assign `root.right` to the inverted saved left child.

**Written Tracing (Example: `[4, 2, 7, 1, 3, 6, 9]`):**
1. At `4`, swap left (`2`) and right (`7`).
2. Recursively invert subtree `7` -> swaps `6` and `9` to become `[7, 9, 6]`.
3. Recursively invert subtree `2` -> swaps `1` and `3` to become `[2, 3, 1]`.
4. Final tree: `[4, 7, 2, 9, 6, 3, 1]`.

**Challenges:** 
Forgetting to store `root.left` in a temporary variable before overwriting it would overwrite the original left subtree before it gets inverted.

## 3. Time & Space Complexity
* **Time Complexity:** $O(n)$ — Every node's children are swapped once.
* **Space Complexity:** $O(h)$ (worst case $O(n)$) — Recursion stack depth proportional to tree height.

## 4. Reflection / Improvement
* **Is there a more efficient approach?** Time complexity cannot beat $O(n)$, as every node must be visited to swap its pointers. However, an iterative BFS queue approach prevents stack overflow on very deep trees.
* **What would you need to change?** Use a `Queue` to poll each node and swap its `left` and `right` references iteratively.
* **Improved Complexity:** $O(n)$ time and $O(w)$ heap space (where $w$ is max tree width).