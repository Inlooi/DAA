## 1. Problem
Merge two binary trees into a new binary tree. If two nodes overlap at the same position, sum their values; otherwise, use the non-null node.

## 2. Approach
Using simultaneous recursion on `root1` and `root2`: if `root1` is `null`, return `root2`; if `root2` is `null`, return `root1`. When both exist, create a new `TreeNode` with value `root1.val + root2.val`, then recursively merge their left children and right children.

**Written Tracing (Example: `t1 = [1, 3, 2, 5]`, `t2 = [2, 1, 3, null, 4]`):**
1. Root `(1, 2)` -> creates node `3`.
2. Left `(3, 1)` -> creates node `4`.
   - Left of `(3, 1)` is `(5, null)` -> returns node `5`.
   - Right of `(3, 1)` is `(null, 4)` -> returns node `4`.
3. Right `(2, 3)` -> creates node `5`.

**Challenges:** 
Realizing that when one node is `null`, we can directly return the other node without recursing further down its subtree.

## 3. Time & Space Complexity
* **Time Complexity:** $O(\min(n, m))$ — Recursion only continues while both trees have overlapping nodes; once one is `null`, it returns immediately.
* **Space Complexity:** $O(\min(h_1, h_2))$ — The recursion depth is bounded by the shorter overlapping height of the two trees.

## 4. Reflection / Improvement
* **Is there a more efficient approach?** Note that returning `root1` or `root2` directly shares references with the input trees. If a completely independent deep copy is required, we would need to clone non-overlapping subtrees, increasing time to $O(n + m)$.
* **What would you need to change?** Modify `root1` in-place instead of allocating `new TreeNode(...)` at overlapping nodes to reduce memory allocations.
* **Improved Complexity:** Same $O(\min(n, m))$ time, with zero new node allocations.