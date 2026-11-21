## 1. Problem
Return the preorder traversal of a binary tree's node values (visit current node first, then left subtree, then right subtree).

## 2. Approach
I implemented a recursive helper method that first appends the current node's value to the result list, then recursively calls itself on the left child, and finally on the right child.

**Written Tracing (Example: `[1, null, 2, 3]`):**
1. Visit `1`: add `1` -> `[1]`.
2. Call `preorder(1.left)` -> `null` (returns).
3. Call `preorder(1.right)` -> node `2`.
4. Visit `2`: add `2` -> `[1, 2]`.
5. Call `preorder(2.left)` -> node `3`: add `3` -> `[1, 2, 3]`.

**Challenges:** 
Straightforward recursive logic; the primary care needed was preserving the exact `Root -> Left -> Right` ordering.

## 3. Time & Space Complexity
* **Time Complexity:** $O(n)$ — Each of the $n$ nodes is processed once.
* **Space Complexity:** $O(n)$ — Space for the output list and $O(h)$ recursion stack.

## 4. Reflection / Improvement
* **Is there a more efficient approach?** Morris Preorder Traversal achieves $O(1)$ auxiliary space by temporarily threading right pointers of predecessors back to current nodes.
* **What would you need to change?** Use pointer manipulation in a `while` loop instead of recursion.
* **Improved Complexity:** $O(n)$ time and $O(1)$ auxiliary space.