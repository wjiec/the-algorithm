package problem.p203removelinkedlistelements;

import common.Checker;
import common.ListNode;

/**
 * 203. Remove Linked List Elements
 *
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 *
 * Given the head of a linked list and an integer val,
 * remove all the nodes of the linked list that has Node.val == val, and return the new head.
 */

public class Solution {

    public ListNode removeElements(ListNode head, int val) {
        ListNode rs = new ListNode(-1, head);
        for (ListNode curr = rs; curr.next != null;) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return rs.next;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().removeElements(ListNode.build(1,2,3,4,5,4), 4), 1,2,3,5);
        assert Checker.check(new Solution().removeElements(ListNode.build(7,7,7,7,7), 7));
        assert Checker.check(new Solution().removeElements(ListNode.build(7), 7));
        assert Checker.check(new Solution().removeElements(ListNode.build(7,7,7,7,7,8), 7), 8);
        assert Checker.check(new Solution().removeElements(ListNode.build(1,2,6,3,4,5,6), 6), 1,2,3,4,5);
    }

}
