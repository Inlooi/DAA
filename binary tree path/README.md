## 1. Problem
Return all root-to-leaf paths in a binary tree formatted as `"root->node->leaf"` strings.

## 2. Approach
I pass a `currentPath` string down a recursive DFS helper. At each node, I append `node.val`. If the node is a leaf, I add the completed string to the result list; otherwise, I append `"->"` and continue down non-null children.

**Written Tracing (Example: `[1, 2, 3, null, 5]`):**
1. Root `1`: `currentPath = "1"`. Not a leaf -> append `"->"` (`"1->"`).
2. Left child `2`: `currentPath = "1->2"`. Not a leaf -> append `"->"` (`"1->2->"`).
3. Right child `5`: `currentPath = "1->2->5"`. Leaf -> add `"1->2->5"` to list.
4. Right child `3` of root: `currentPath = "1->3"`. Leaf -> add `"1->3"` to list.

**Challenges:** 
String concatenation in Java creates new `String` objects on each step, which naturally isolates paths between left and right branches without needing manual backtracking cleanup.

## 3. Time & Space Complexity
* **Time Complexity:** $O(n^2)$ in the worst case — Visiting all $n$ nodes takes $O(n)$, and copying/concatenating strings of length up to $n$ at each step can take $O(n)$ per node.
* **Space Complexity:** $O(n^2)$ — Storing all path strings and recursion stack frames.

## 4. Reflection / Improvement
* **Is there a more efficient approach?** Yes, using a mutable `StringBuilder` with backtracking instead of immutable `String` concatenation avoids allocating new string objects at every node.
* **What would you need to change?** Append to a single `StringBuilder`, recurse, and reset `sb.setLength(len)` after returning from child calls.
* **Improved Complexity:** Reduces intermediate object creation to $O(n)$ auxiliary space, while output size remains bounded by the total characters across all leaf paths.