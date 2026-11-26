## 1. Problem
Given the root of a BST and two integers `low` and `high`, return the sum of the values of all nodes whose value is in the range `[low, high]` (inclusive).

## 2. Approach
I use DFS, but I use the BST property to skip parts of the tree that cannot contain valid values:
- If `root.val < low`: this node and its whole left subtree are too small, so only search the right.
- If `root.val > high`: this node and its whole right subtree are too big, so only search the left.
- Otherwise the node is in range: add its value and search both children.

**Written Tracing (Tree `[10, 5, 15, 3, 7, null, 18]`, `low = 7`, `high = 15`):**
1. At `10`: in range, so `10 + left + right`.
2. Left child `5`: `5 < 7`, skip its left side and go to its right child `7`.
3. At `7`: in range, add `7` (children are `null`, so `0`).
4. Right child `15`: in range, add `15`; its left is `null`, its right `18` is `> 15`, so go to its left (`null`) → `0`.
5. Total: `10 + 7 + 15 = 32`.

**Challenges:**
Using the pruning correctly: when the node is out of range we only skip *one* side, not the whole tree.

## 3. Time & Space Complexity
* **Time Complexity:** $O(n)$ in the worst case — if every node is inside the range we have to visit all of them. Thanks to pruning, it is often much faster.
* **Space Complexity:** $O(h)$ — recursion stack, where `h` is the tree height (worst case $O(n)$ for a skewed tree).

## 4. Reflection / Improvement
* **Is there a more efficient approach?** The time is already optimal, because we must add up every node inside the range. We can avoid recursion with an iterative DFS.
* **What would you need to change?** Replace recursion with an explicit `Stack` of nodes and apply the same three rules when popping.
* **Improved Complexity:** $O(n)$ time and $O(h)$ heap space (no risk of stack overflow).
