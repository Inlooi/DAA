## 1. Problem
Return the postorder traversal of a binary tree's node values (left subtree, then right subtree, then current node).

## 2. Approach
Using a recursive helper function, the algorithm traverses the left subtree completely, then the right subtree completely, and appends the current node's value to the list last.

**Written Tracing (Example: `[1, null, 2, 3]`):**
1. Start at `1`: left is `null`, go right to `2`.
2. At `2`: go left to `3`.
3. At `3`: left is `null`, right is `null`, add `3` -> `[3]`.
4. Back at `2`: right is `null`, add `2` -> `[3, 2]`.
5. Back at `1`: add `1` -> `[3, 2, 1]`.

**Challenges:** 
Tracing the unwinding order on paper to confirm parents are always recorded after all descendants.

## 3. Time & Space Complexity
* **Time Complexity:** $O(n)$ — Every node is visited once.
* **Space Complexity:** $O(n)$ — Output list plus $O(h)$ recursion stack depth.

## 4. Reflection / Improvement
* **Is there a more efficient approach?** Iterative postorder traversal using a single stack or Morris postorder traversal avoids recursion stack limits.
* **What would you need to change?** Track the previously visited node iteratively to know when a right subtree has finished processing.
* **Improved Complexity:** $O(n)$ time and $O(1)$ auxiliary space with Morris traversal.