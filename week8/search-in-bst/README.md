## 1. Problem
Given the root of a binary search tree (BST) and an integer `val`, find the node whose value equals `val` and return the subtree rooted at it. If there is no such node, return `null`.

## 2. Approach
In a BST, everything in the left subtree is smaller than the node and everything in the right subtree is bigger. So at each node I can throw away half of the tree:
- If `val == root.val`, found it.
- If `val < root.val`, go left.
- If `val > root.val`, go right.

I do this in a simple loop (no recursion).

**Written Tracing (Tree `[4, 2, 7, 1, 3]`, `val = 2`):**
1. Start at `4`: `2 < 4`, move left.
2. At `2`: `2 == 2`, stop and return this node (subtree `[2, 1, 3]`).

For `val = 5`: `4` → right → `7` → `5 < 7`, left is `null`, so return `null`.

**Challenges:**
Remembering to stop when `root` becomes `null` (value not in the tree).

## 3. Time & Space Complexity
* **Time Complexity:** $O(h)$ — we follow one path down the tree, where `h` is the height. For a balanced tree this is $O(\log n)$; for a skewed tree it is $O(n)$.
* **Space Complexity:** $O(1)$ — the loop uses no extra memory.

## 4. Reflection / Improvement
* **Is there a more efficient approach?** Not really; $O(h)$ is already the best you can do for a plain BST. The recursive version is shorter but uses $O(h)$ stack space.
* **What would you need to change?** To guarantee $O(\log n)$ you would use a self-balancing tree (AVL or Red-Black).
* **Improved Complexity:** $O(\log n)$ time with a balanced tree.
