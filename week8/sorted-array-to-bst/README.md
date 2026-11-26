## 1. Problem
Given an integer array `nums` sorted in ascending order, convert it into a height-balanced BST (the depths of the two subtrees of every node differ by at most 1).

## 2. Approach
The middle element of a sorted array is the best root: half of the numbers are smaller (they go left) and half are bigger (they go right). I repeat this on each half:
1. Pick `mid` of the current range as the node.
2. Build the left subtree from `[left, mid - 1]`.
3. Build the right subtree from `[mid + 1, right]`.
4. If the range is empty (`left > right`), return `null`.

**Written Tracing (`nums = [-10, -3, 0, 5, 9]`):**
1. Range `[0..4]`, `mid = 2` → root `0`.
2. Left range `[0..1]`, `mid = 0` → node `-10`, its right range `[1..1]` → node `-3`.
3. Right range `[3..4]`, `mid = 3` → node `5`, its right range `[4..4]` → node `9`.
4. Result: `0` with left `-10 (right -3)` and right `5 (right 9)`. This is a valid balanced BST (LeetCode accepts any valid answer).

**Challenges:**
Choosing `mid` safely: `left + (right - left) / 2` avoids integer overflow. Also, when the range has an even size, either middle works.

## 3. Time & Space Complexity
* **Time Complexity:** $O(n)$ — every element is turned into exactly one node.
* **Space Complexity:** $O(\log n)$ — recursion depth equals the height of the balanced tree (the $O(n)$ tree itself is the output and is not counted).

## 4. Reflection / Improvement
* **Is there a more efficient approach?** $O(n)$ time is optimal since we must create `n` nodes. Recursion depth is only $\log n$, so it is already memory-friendly.
* **What would you need to change?** An iterative version could use a stack or queue holding `(node, left, right)` ranges.
* **Improved Complexity:** Same $O(n)$ time; no real gain, just avoids recursion.
