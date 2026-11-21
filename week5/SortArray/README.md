# 912. Sort an Array

## 1. Problem

I get an array of integers and must return it sorted from smallest to biggest.
The rules are: no built-in sort, the time must be `O(n log n)`, and the extra memory
should be as small as possible. The array can contain duplicates (for example `[5,1,1,2,0,0]`).

## 2. Approach

I used **Heap Sort**. It sorts the array in place, so it needs no extra array.

Idea in simple words:

1. A **max-heap** is an array where every parent is bigger than or equal to its children.
   For index `i`, the children are at `2*i+1` (left) and `2*i+2` (right).
   Because of this rule, the biggest number is always at index `0`.
2. **Build the heap.** Go from the last parent (`n/2 - 1`) back to index `0`.
   For each parent run `siftDown`: if a child is bigger, swap with the bigger child
   and continue downward until the rule holds.
3. **Sort.** Swap the biggest value (index `0`) with the last element of the heap.
   Now that value is in its final place, so shrink the heap by 1 and run `siftDown`
   from index `0` to repair the heap. Repeat until the heap has size 1.

### Tracing: `nums = [5, 2, 3, 1]`

Heap as a tree: `5` is the root, its children are `2` and `3`, and `2` has a child `1`.

**Phase 1: build the heap** (`n = 4`, last parent = index 1)

| Step | Action | Array |
|------|--------|-------|
| i = 1 | children of `2` is only `1`; `1 <= 2` so stop | `[5, 2, 3, 1]` |
| i = 0 | children `2` and `3`, bigger is `3`; `3 <= 5` so stop | `[5, 2, 3, 1]` |

The array already is a max-heap.

**Phase 2: sort**

Sorted part is written after `//`.

| end | Action | Array |
|-----|--------|-------|
| 3 | swap `5` and `1` -> `[1,2,3,5]`; siftDown on size 3: `3` is bigger than `1`, swap | `[3, 2, 1] // 5` |
| 2 | swap `3` and `1` -> `[1,2,3,5]`; siftDown on size 2: `2` is bigger than `1`, swap | `[2, 1] // 3, 5` |
| 1 | swap `2` and `1` -> `[1,2,3,5]`; heap size 1, nothing to fix | `[1] // 2, 3, 5` |

Result: `[1, 2, 3, 5]`.

### Challenges and approaches that did not work

- **`Arrays.sort`** is a built-in function, so the task forbids it.
- **Bubble / selection / insertion sort** use no extra memory, but they are `O(n^2)`.
  With `n` up to 50 000 that is about 2.5 billion steps, too slow.
- **Merge sort on an array** is `O(n log n)`, but merging needs a temporary array,
  so the space is `O(n)`. The task asks for the smallest space, so I rejected it.
- **Quick sort** uses little memory, but with bad pivots (for example an already sorted
  array and the first element as pivot) it becomes `O(n^2)` time and the recursion can get
  `O(n)` deep. Heap sort has no bad case.
- **Counting sort** would be very fast here because values are in a small range,
  but it needs an extra counting array and it only works for small value ranges, so it is
  not a general solution.
- The hardest part for me was the index math in `siftDown`: I must compare the right
  child only if it exists (`right < size`), and after each swap the heap size must shrink
  so I do not touch the sorted part.

## 3. Time Complexity

**Time Complexity: O(n log n)** (best, average and worst case). **Space: O(1).**

Why:

- Building the heap: `n/2` calls of `siftDown`. Most nodes are near the bottom and move
  only a little, so the total is `O(n)`.
- Sorting: we do `n - 1` rounds, and each `siftDown` moves a value down at most the
  height of the heap, which is `log n`. So this part is `n * log n`.
- Total: `O(n) + O(n log n) = O(n log n)`.
- Space is `O(1)` because I only use a few variables and swap inside the same array
  (`siftDown` is a loop, not recursion).

## 4. Reflection / Improvement

- Is there a more efficient approach? In terms of Big-O, `O(n log n)` is the best possible
  for comparison sorting, so heap sort is already optimal for this limit and has the best
  space (`O(1)`).
- In practice, **quick sort** (random pivot) or **merge sort** are usually faster, because
  heap sort jumps around in memory and is not cache friendly. Merge sort also is stable,
  but costs `O(n)` memory.
- If the value range is small (here about `-50 000 ... 50 000`), **counting sort** can
  reach `O(n + k)`, where `k` is the size of the range, but it needs `O(k)` extra memory.
