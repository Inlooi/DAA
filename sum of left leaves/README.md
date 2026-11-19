## 1. Problem
Calculate the sum of all left leaves in a given binary tree.

## 2. Approach
A node cannot tell on its own whether it is a left or right child, so the parent node checks its own `left` child. If `root.left` exists and has no children (`left == null && right == null`), I add `root.left.val` to the sum. Otherwise, I recursively sum left leaves in `root.left`. I always recursively check `root.right` as well, since right subtrees can contain left leaves.

**Written Tracing (Example: `[3, 9, 20, null, null, 15, 7]`):**
1. At `3`: `3.left` is `9`, which is a leaf -> `sum += 9`. Recurse on `3.right` (`20`).
2. At `20`: `20.left` is `15`, which is a leaf -> `sum += 15`. Recurse on `20.right` (`7`).
3. At `7`: both children are `null` -> returns `0`.
4. Total returned: `9 + 15 = 24`.

**Challenges:** 
Initially, I forgot to recurse into `root.right`, missing left leaves nested inside right subtrees (like node `15` in the example).

## 3. Time & Space Complexity
* **Time Complexity:** $O(n)$ — Every node in the tree is checked once.
* **Space Complexity:** $O(h)$ (worst case $O(n)$) — Recursion call stack depth.

## 4. Reflection / Improvement
* **Is there a more efficient approach?** An iterative traversal (BFS or stack-based DFS) achieves the same $O(n)$ runtime without risking call stack overflow on skewed trees.
* **What would you need to change?** Use a `Queue` or `Stack` and apply the same `node.left` leaf check inside a `while` loop.
* **Improved Complexity:** $O(n)$ time and $O(w)$ heap space.