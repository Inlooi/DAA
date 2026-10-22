# 148. Sort List

## 1. Problem

I get the head of a singly linked list and must return the list sorted in ascending order.
Unlike an array, I cannot jump to an index in a linked list, I can only move forward
node by node, so the sorting method must fit this.

## 2. Approach

I used **Merge Sort** (top-down). It fits linked lists well because merging only needs
to change `next` pointers, and no random access is needed.

Steps of `sortList(head)`:

1. **Base case:** if the list is empty or has one node, it is already sorted, return it.
2. **Find the middle** with two pointers: `slow` moves 1 step, `fast` moves 2 steps.
   I start `fast` at `head.next` so that for an even length `slow` stops at the end of the
   first half (this avoids an endless recursion on a list of 2 nodes).
3. **Cut the list** in two: `second = slow.next; slow.next = null`.
4. **Sort both halves** recursively.
5. **Merge** two sorted lists: use a `dummy` node and a `tail` pointer, always attach the
   smaller head, move forward, and at the end attach what is left.

### Tracing: `head = [4, 2, 1, 3]`

Splitting (going down):

```
[4, 2, 1, 3]
   /      \
[4, 2]   [1, 3]
 /  \     /  \
[4] [2]  [1] [3]
```

Merging (going up):

| Merge | Compare | Result |
|-------|---------|--------|
| `[4]` + `[2]` | `4 <= 2`? no -> take `2`; then attach `4` | `[2, 4]` |
| `[1]` + `[3]` | `1 <= 3`? yes -> take `1`; then attach `3` | `[1, 3]` |
| `[2, 4]` + `[1, 3]` | `2<=1`? no -> `1`; `2<=3`? yes -> `2`; `4<=3`? no -> `3`; attach `4` | `[1, 2, 3, 4]` |

### Tracing: `head = [-1, 5, 3, 4, 0]`

Middle: `slow` = `5` (the second node), so the halves are `[-1, 5]` and `[3, 4, 0]`.

```
[-1, 5, 3, 4, 0]
    /          \
[-1, 5]      [3, 4, 0]
              /     \
           [3, 4]   [0]
```

- `[-1, 5]` -> `[-1]` + `[5]` -> `[-1, 5]`
- `[3, 4]` -> `[3]` + `[4]` -> `[3, 4]`
- `[3, 4]` + `[0]` -> `0` is smaller, take `0`, attach `[3, 4]` -> `[0, 3, 4]`
- `[-1, 5]` + `[0, 3, 4]` -> `-1`, `0`, `3`, `4`, then attach `5` -> `[-1, 0, 3, 4, 5]`

### Challenges and approaches that did not work

- **Copy values into an array, sort, write back:** it works, but it needs `O(n)` extra
  memory and it avoids the real point of the task (working with the list itself).
- **Insertion sort on the list:** simple, but `O(n^2)`. For 50 000 nodes it is too slow.
- **Quick sort on a list:** choosing a pivot and partitioning is awkward, and the worst case
  is `O(n^2)` (for example, many equal values or a sorted list).
- **Finding the middle:** my first idea was `fast = head`. With a 2-node list
  `[a, b]` this makes `slow` stop on `b`, so the "first half" is the whole list and
  the recursion never gets smaller (infinite recursion). Starting `fast = head.next` fixed it.
- **Forgetting to cut the list** (`slow.next = null`) is another trap: the first half
  would still be connected to the second half and the sort would break.
- Handling the leftover nodes in `merge`: after one list ends, the other list is already
  sorted, so I can attach it with one line instead of a loop.

## 3. Time Complexity

**Time Complexity: O(n log n).** Space: `O(log n)` for the recursion stack.

Why:

- Each level of recursion cuts the list in half, so there are about `log n` levels.
- On every level, all `n` nodes are touched once in total (finding the middle costs
  `O(length)` and merging costs `O(length)`), so one level costs `O(n)`.
- `log n` levels * `O(n)` per level = `O(n log n)`.
- No new nodes are created except one `dummy` per merge, so the only extra memory is
  the recursion stack of depth `log n`.

## 4. Reflection / Improvement

- Is there a more efficient approach? The time `O(n log n)` is already the best possible
  for comparison sorting. What can be improved is the **space**.
- **Bottom-up merge sort:** instead of recursion, first merge sublists of size 1, then size 2,
  then 4, and so on, using a loop. It needs a helper to cut the first `k` nodes
  and to merge, but it uses only pointers, so the space becomes **O(1)** while
  the time stays `O(n log n)`.
