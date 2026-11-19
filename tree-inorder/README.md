## 1. Problem
Given the root of a binary tree, return a list of its node values following an inorder traversal sequence (left subtree, current node, right subtree).

## 2. Approach
I used a recursive Depth-First Search (DFS) helper method that takes the current node and a list to store the results. For any non-null node, the function recursively traverses the left child, adds the current node's value to the list, and then recursively traverses the right child.

**Written Tracing (Example: `[1, null, 2, 3]`):**
1. Start at root `1`. Call `inorder(1.left)` -> `null` (returns).
2. Add `1` to `result`: `[1]`.
3. Call `inorder(1.right)` -> node `2`.
4. At `2`, call `inorder(2.left)` -> node `3`.
5. At `3`, left is `null`, add `3` to `result`: `[1, 3]`, right is `null` (returns).
6. Back at `2`, add `2` to `result`: `[1, 3, 2]`, right is `null` (returns).

**Challenges:** 
Initially, creating a new list inside every recursive call caused partial results to be lost. Passing a single `ArrayList` reference through the helper function solved this.

## 3. Time & Space Complexity
* **Time Complexity:** $O(n)$ — Every node in the tree is visited exactly once.
* **Space Complexity:** $O(n)$ — In the worst case (a completely skewed tree), the recursion call stack reaches a depth of $n$, alongside the output list of size $n$.

## 4. Reflection / Improvement
* **Is there a more efficient approach?** Yes, Morris Traversal eliminates the recursion stack and uses $O(1)$ auxiliary space (excluding the output list) by temporarily modifying tree pointers.
* **What would you need to change?** Replace recursion with an iterative loop that links each node's inorder predecessor to the current node.
* **Improved Complexity:** $O(n)$ time and $O(1)$ auxiliary space.