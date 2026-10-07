class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        //checks if the first list is empty. If it is, there is nothing left to merge from it, so it simply returns the rest of the second list. The reverse is checked right after.
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }

        //checks which list currently has the smaller number.
        if(list1.val <= list2.val){
           list1.next = mergeTwoLists(list1.next, list2);
           return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
        // The time complexity of this algorithm is O(N + M), where N is the length of list1 and M is the length of list2.
    }
}