package problem.p234palindromelinkedlist;

import common.ListNode;

public class Solution {

    private ListNode front = null;

    public boolean isPalindrome(ListNode head) {
        front = head;
        return backCheck(head);
    }

    private boolean backCheck(ListNode curr) {
        if (curr != null) {
            if (!backCheck(curr.next)) {
                return false;
            }
            if (curr.val != front.val) {
                return false;
            }
            front = front.next;
        }
        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().isPalindrome(ListNode.build(1,2));
        assert new Solution().isPalindrome(ListNode.build(1,2,2,1));
    }

}
