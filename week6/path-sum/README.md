## 1. Problem
Determine whether a binary tree has a root-to-leaf path such that adding up all the values along the path equals a given `targetSum`.

## 2. Approach
At each step, I subtract the current node's value from `targetSum` and pass the remaining sum down to its children. If the traversal reaches a leaf node (`left == null && right == null`), I check if the remaining sum equals that leaf's value.

**Written Tracing (Example: Tree `[5, 4, 8, 11]`, `targetSum = 20`):**
1. Start at `5`: `remaining = 20 - 5 = 15`. Check left (`4`) and right (`8`).
2. At `4`: `remaining = 15 - 4 = 11`. Check left (`11`).
3. At `11`: `11` is a leaf and `remaining == 11`, so return `true`.
4. Logical OR (`||`) propagates `true` back to the root.

**Challenges:** 
Ensuring the check only triggers at actual leaf nodes rather than internal nodes whose partial sum happens to hit zero early.

## 3. Time & Space Complexity
* **Time Complexity:** $O(n)$ — Every node is visited at most once.
* **Space Complexity:** $O(h)$ (worst case $O(n)$) — Space used by the recursion stack.

## 4. Reflection / Improvement
* **Is there a more efficient approach?** $O(n)$ time is optimal since the valid path could be the very last one checked. An iterative DFS using two stacks (one for nodes, one for running sums) can prevent call stack overflow on massive trees.
* **What would you need to change?** Replace recursion with explicit `Stack` objects.
* **Improved Complexity:** $O(n)$ time and $O(h)$ explicit heap memory instead of stack memory.