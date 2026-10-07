public class onCycle {

    // Slow advances by 1 step (slow.next), while fast advances by 2 steps (fast.next.next).
    // If a cycle exists, fast will enter the loop first. Once slow enters the loop, 
    // fast closes the gap between them by 1 node on every iteration until they point to the exact same memory reference (fast == slow), 
    // triggering return true
    
    //Time complexity is O(N)
    public boolean hasCycle(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) return true;
        }
        return false;
    }
}