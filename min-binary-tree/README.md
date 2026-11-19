## 1. Problem
Find the minimum depth of a binary tree, which is the shortest path from the root node down to the nearest leaf node (a node with no children).

## 2. Approach
Unlike maximum depth, we cannot simply take `Math.min(left, right) + 1` when one child is `null`, because a node with one child is not a leaf. If one subtree is `null`, the algorithm must recursively follow the non-null subtree. Only when both children exist do we take the minimum of both sides.

**Written Tracing (Example: `[2, null, 3, null, 4]`):**
1. At `2`, `left` is `null`, so we return `minDepth(3) + 1`.
2. At `3`, `left` is `null`, so we return `minDepth(4) + 1`.
3. At `4`, both children are `null`, returning `Math.min(0, 0) + 1 = 1`.
4. Unwinding back up: `3` returns `2`, and root `2` returns `3`.

**Challenges:** 
My initial attempt used `Math.min(minDepth(left), minDepth(right)) + 1` directly, which failed on skewed trees by treating a missing child as a leaf with depth `0`. Adding explicit null-child checks fixed the bug.

## 3. Time & Space Complexity
* **Time Complexity:** $O(n)$ — In the worst case, all $n$ nodes are visited.
* **Space Complexity:** $O(h)$ (worst case $O(n)$) — Determined by the recursion stack height.

## 4. Reflection / Improvement
* **Is there a more efficient approach?** Yes, Breadth-First Search (BFS) is much faster in practice for unbalanced trees because it stops as soon as it encounters the very first leaf node.
* **What would you need to change?** Traverse level-by-level with a queue and return the current depth immediately upon finding a node where `left == null && right == null`.
* **Improved Complexity:** Worst-case $O(n)$ time, but best/average case visits far fewer nodes.