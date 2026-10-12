public class intersection {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode first = headA;
        ListNode second = headB;

        if (first == null || second == null) {
            return null;
        }

        while(first != second){
            first = (first == null) ? headB : first.next;
            second = (second == null) ? headA : second.next;
        }
        return first;
        
    }
}
