# 🔗 Linked List Algorithm Implementations

This repository contains efficient Java solutions for common **Singly-Linked List** problems, complete with algorithmic breakdowns, step-by-step execution traces, complexity analyses, and reflection notes.

---

## 📌 Table of Contents
- [1. Linked List Cycle Detection (`onCycle.java`)](#1-linked-list-cycle-detection-oncyclejava)
  - [Problem Statement](#problem-statement)
  - [Approach & Solution](#approach--solution)
  - [Code Snippet](#code-snippet)
  - [Execution Trace & Example](#execution-trace--example)
  - [Challenges Faced](#challenges-faced)
  - [Complexity Analysis](#complexity-analysis)
  - [Reflection & Future Improvements](#reflection--future-improvements)
- [2. Merge Two Sorted Lists (`mergeList.java`)](#2-merge-two-sorted-lists-mergelistjava)
  - [Problem Statement](#problem-statement-1)
  - [Approach & Solution](#approach--solution-1)
  - [Code Snippet](#code-snippet-1)
  - [Execution Trace & Call Stack](#execution-trace--call-stack)
  - [Challenges Faced](#challenges-faced-1)
  - [Complexity Analysis](#complexity-analysis-1)
  - [Reflection & Future Improvements](#reflection--future-improvements-1)

---

## 1. Linked List Cycle Detection (`onCycle.java`)

### Problem Statement
Given the head of a singly-linked list, determine if the list contains a cycle. A cycle exists if a node can be reached again by continuously following the `next` pointer.

### Approach & Solution
The implementation utilizes **Floyd’s Cycle-Finding Algorithm** (also known as the *Tortoise and the Hare* two-pointer approach):
1. Two pointers (`slow` and `fast`) are initialized at the head of the list.
2. `slow` moves **1 step** at a time (`slow = slow.next`).
3. `fast` moves **2 steps** at a time (`fast = fast.next.next`).
4. If a cycle exists, `fast` enters the loop first, and the distance between `fast` and `slow` decreases by 1 node in each iteration until they meet (`fast == slow`), returning `true`.
5. If `fast` or `fast.next` reaches `null`, the list terminates, returning `false`.

### Code Snippet
```java
public class onCycle {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
```

### Execution Trace & Example
Consider a linked list with a loop: `1 -> 2 -> 3 -> 4 -> 2` *(Node 4 points back to Node 2)*.

| Iteration | `slow` Position | `fast` Position | Condition Check (`fast == slow`) |
| :---: | :---: | :---: | :---: |
| **Init** | `1` | `1` | `1 == 1` *(Loop start)* |
| **1** | `2` | `3` | `3 == 2` $\rightarrow$ `false` |
| **2** | `3` | `2` | `2 == 3` $\rightarrow$ `false` |
| **3** | `4` | `4` | `4 == 4` $\rightarrow$ **`true` (Cycle Found)** |

### Challenges Faced
- **Avoid Infinite Loops:** A naive single-pointer traversal would result in an infinite loop without additional tracking.
- **Memory Overhead:** Storing visited nodes in a `HashSet` provides a working solution but incurs an undesirable $O(N)$ space overhead.
- **Null Reference Safety:** To avoid a `NullPointerException` when calling `fast.next.next`, the `while` loop condition strictly guards against `fast == null` and `fast.next == null`.

### Complexity Analysis
- **Time Complexity:** $O(N)$
  - *Non-cyclic lists:* `fast` reaches `null` in $\frac{N}{2}$ operations.
  - *Cyclic lists:* `fast` catches up to `slow` in $K$ steps, where $K$ is bounded by the length of the cycle.
- **Space Complexity:** $O(1)$
  - Uses only two pointers, maintaining constant memory.

### Reflection & Future Improvements
- **Is there a more efficient approach?** No, $O(N)$ time and $O(1)$ auxiliary space are asymptotically optimal for cycle detection.
- **Extension:** To return the *node where the cycle begins* (LeetCode 142), upon collision, reset `slow` to `head` and advance both pointers 1 step at a time until they intersect.

---

## 2. Merge Two Sorted Lists (`mergeList.java`)

### Problem Statement
Given the heads of two sorted singly-linked lists, `list1` and `list2`, merge them into a single sorted list. The list should be created by splicing together the nodes of the first two lists.

### Approach & Solution
The solution uses a **recursive strategy**:
1. **Base Cases:** If `list1 == null`, return `list2`. If `list2 == null`, return `list1`.
2. **Comparison Step:** Compare `list1.val` and `list2.val`.
3. **Recursive Step:** Attach the node with the smaller value to the result of recursively calling `mergeTwoLists` on its `next` node and the remaining list.

### Code Snippet
```java
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
```

### Execution Trace & Call Stack
Given `list1 = 1 -> 3` and `list2 = 2 -> 4`:

1. `mergeTwoLists(1->3, 2->4)` $\rightarrow$ $1 \le 2$, select `1`.  
   `1.next = mergeTwoLists(3, 2->4)`
2. `mergeTwoLists(3, 2->4)` $\rightarrow$ $3 \le 2$ (false), select `2`.  
   `2.next = mergeTwoLists(3, 4)`
3. `mergeTwoLists(3, 4)` $\rightarrow$ $3 \le 4$, select `3`.  
   `3.next = mergeTwoLists(null, 4)`
4. `mergeTwoLists(null, 4)` $\rightarrow$ `list1 == null`, returns `4`.

**Unwinding Call Stack:**
- `3.next = 4` $\rightarrow$ `3 -> 4`
- `2.next = 3 -> 4` $\rightarrow$ `2 -> 3 -> 4`
- `1.next = 2 -> 3 -> 4` $\rightarrow$ `1 -> 2 -> 3 -> 4`

### Challenges Faced
- **Stack Overflow Risk:** Deep lists could trigger a `StackOverflowError` due to excessive recursion frames.
- **Iterative Complexity:** Iterative approaches require multiple null-checks and edge-case management when establishing the initial head node without using a dummy node.

### Complexity Analysis
- **Time Complexity:** $O(N + M)$
  - Each recursive call processes exactly one node from either `list1` (length $N$) or `list2` (length $M$).
- **Space Complexity:** $O(N + M)$
  - Requires $O(N + M)$ auxiliary memory on the call stack due to recursion.

### Reflection & Future Improvements
- **Is there a more efficient approach?** An **iterative approach** achieves optimal space efficiency.
- **Modifications Needed:** Replace recursion with a `while` loop utilizing a `dummyHead` pointer:
  ```java
  ListNode dummy = new ListNode(0);
  ListNode current = dummy;
  while (list1 != null && list2 != null) {
      if (list1.val <= list2.val) {
          current.next = list1;
          list1 = list1.next;
      } else {
          current.next = list2;
          list2 = list2.next;
      }
      current = current.next;
  }
  current.next = (list1 != null) ? list1 : list2;
  return dummy.next;
  ```
- **Improved Complexity:** Time complexity remains $O(N + M)$, while space complexity drops to **$O(1)$ constant auxiliary space**.