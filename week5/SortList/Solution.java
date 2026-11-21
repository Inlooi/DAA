/**
 * 148. Sort List
 * Top-down merge sort on a linked list: O(n log n) time, O(log n) stack space.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {

    public ListNode sortList(ListNode head) {
        // Base case: empty list or one node is already sorted
        if (head == null || head.next == null) {
            return head;
        }

        // 1. Find the middle with slow/fast pointers.
        //    fast starts one step ahead so that for even length
        //    slow stops at the END of the first half.
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Cut the list into two separate lists
        ListNode second = slow.next;
        slow.next = null;

        // 3. Sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(second);

        // 4. Merge two sorted lists
        return merge(left, right);
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (a != null && b != null) {
            if (a.val <= b.val) { // <= keeps the sort stable
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b; // attach the leftover tail
        return dummy.next;
    }
}
