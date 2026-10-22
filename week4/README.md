# Java Data Structures & Linked List Algorithms

A collection of core Java algorithms and linked list manipulation patterns, covering dynamic deduplication and optimal solutions for common linked list problems.

---

## Table of Contents

* [1. Collections: Dynamic List Deduplication](https://www.google.com/search?q=%231-collections-dynamic-list-deduplication)
* [2. Linked List: Remove Duplicates from Sorted List](https://www.google.com/search?q=%232-linked-list-remove-duplicates-from-sorted-list)
* [3. Linked List: Intersection of Two Lists](https://www.google.com/search?q=%233-linked-list-intersection-of-two-lists)
* [4. Linked List: Remove Elements by Value](https://www.google.com/search?q=%234-linked-list-remove-elements-by-value)
* [5. Linked List: Palindrome Check](https://www.google.com/search?q=%235-linked-list-palindrome-check)

---

## 1. Collections: Dynamic List Deduplication

### Concept

When working with standard Java `List` objects of unknown or dynamic size (e.g., `List<Integer>` or `List<String>`), you can safely remove duplicates in-place using `removeIf` alongside a `HashSet`.

```java
import java.util.*;

public class DeduplicateExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(10, 20, 10, 30, 20, 40));
        Set<Integer> seen = new HashSet<>();

        // Set.add() returns false if the item was already present
        numbers.removeIf(num -> !seen.add(num));

        System.out.println(numbers); // Output: [10, 20, 30, 40]
    }
}

```

* **Time Complexity:** $O(N)$
* **Space Complexity:** $O(N)$

---

## 2. Linked List: Remove Duplicates from Sorted List

### Problem

Given the `head` of a sorted linked list, delete all duplicates such that each element appears only once[cite: 1].

### Key Approach

Since the list is already sorted, duplicate values are adjacent[cite: 1]. Compare each node with its immediate neighbor (`current.next`) and bypass duplicate nodes by adjusting `current.next = current.next.next`[cite: 1].

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head; //[cite: 1]
        while (current != null && current.next != null) { //[cite: 1]
            if (current.val == current.next.val) { //[cite: 1]
                // Skip the duplicate node by pointing to the one after it[cite: 1]
                current.next = current.next.next; //[cite: 1]
            } else {
                // Advance pointer only when the next value is different[cite: 1]
                current = current.next; //[cite: 1]
            }
        }
        return head; //[cite: 1]
    }
}

```

* **Time Complexity:** $O(N)$[cite: 1]
* **Space Complexity:** $O(1)$[cite: 1]

---

## 3. Linked List: Intersection of Two Lists

### Problem

Find the reference node where two singly linked lists intersect[cite: 2].

### Key Approach

Use two pointers (`first` and `second`)[cite: 2]. Pointer `first` traverses List A then switches to List B[cite: 2]. Pointer `second` traverses List B then switches to List A[cite: 2]. Both pointers travel an equal distance ($Length_A + Length_B$), meeting at the intersection node or `null` if no intersection exists[cite: 2].

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode first = headA; //[cite: 2]
        ListNode second = headB; //[cite: 2]

        if (first == null || second == null) { //[cite: 2]
            return null; //[cite: 2]
        }

        while (first != second) { //[cite: 2]
            first = (first == null) ? headB : first.next; //[cite: 2]
            second = (second == null) ? headA : second.next; //[cite: 2]
        }
        return first; //[cite: 2]
    }
}

```

* **Time Complexity:** $O(M + N)$[cite: 2]
* **Space Complexity:** $O(1)$[cite: 2]

---

## 4. Linked List: Remove Elements by Value

### Problem

Remove all nodes from a linked list that have a specific value `val`[cite: 3].

### Key Approach

Use a **dummy (sentinel) node** placed before `head`[cite: 3]. This unifies edge-case logic when removing head nodes[cite: 3]. Inspect `current.next` and bypass matching target nodes[cite: 3].

```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0); //[cite: 3]
        dummy.next = head; //[cite: 3]
        ListNode current = dummy; //[cite: 3]

        while (current.next != null) { //[cite: 3]
            if (current.next.val == val) { //[cite: 3]
                current.next = current.next.next; //[cite: 3]
            } else {
                current = current.next; //[cite: 3]
            }
        }
        return dummy.next; //[cite: 3]
    }
}

```

* **Time Complexity:** $O(N)$[cite: 3]
* **Space Complexity:** $O(1)$[cite: 3]

---

## 5. Linked List: Palindrome Check

### Problem

Determine if a singly linked list reads the same forward and backward[cite: 4].

### Key Approach

1. Find the middle of the list using Fast & Slow pointers[cite: 4].
2. Reverse the second half of the list in-place[cite: 4].
3. Compare the node values of the first half and reversed second half[cite: 4].

```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        // Step 1: Find the middle using Fast & Slow pointers[cite: 4]
        ListNode slow = head; //[cite: 4]
        ListNode fast = head; //[cite: 4]
        while (fast != null && fast.next != null) { //[cite: 4]
            slow = slow.next; //[cite: 4]
            fast = fast.next.next; //[cite: 4]
        }

        // Step 2: Reverse the second half starting from 'slow'[cite: 4]
        ListNode secondHalf = reverseList(slow); //[cite: 4]
        ListNode firstHalf = head; //[cite: 4]

        boolean result = true; //[cite: 4]
        while (secondHalf != null) { //[cite: 4]
            if (firstHalf.val != secondHalf.val) { //[cite: 4]
                result = false; //[cite: 4]
                break; //[cite: 4]
            }
            firstHalf = firstHalf.next; //[cite: 4]
            secondHalf = secondHalf.next; //[cite: 4]
        }

        return result; //[cite: 4]
    }
    
    private ListNode reverseList(ListNode head) {
        ListNode prev = null; //[cite: 4]
        ListNode curr = head; //[cite: 4]
        while (curr != null) { //[cite: 4]
            ListNode nextTemp = curr.next; //[cite: 4]
            curr.next = prev; //[cite: 4]
            prev = curr; //[cite: 4]
            curr = nextTemp; //[cite: 4]
        }
        return prev; //[cite: 4]
    }
}

```

* **Time Complexity:** $O(N)$[cite: 4]
* **Space Complexity:** $O(1)$[cite: 4]

---

## Common Node Definition

All linked list problems share the following singly-linked list node definition[cite: 1, 3, 4]:

```java
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

```