package problem.p2095deletethemiddlenodeofalinkedlist;

import common.Checker;
import common.ListNode;

/**
 * 2095. Delete the Middle Node of a Linked List
 *
 * https://leetcode.cn/problems/delete-the-middle-node-of-a-linked-list/
 *
 * You are given the head of a linked list. Delete the middle node, and
 * return the head of the modified linked list.
 *
 * The middle node of a linked list of size n is the ⌊n / 2⌋th node from
 * the start using 0-based indexing, where ⌊x⌋ denotes the largest integer
 * less than or equal to x.
 *
 * For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
 */

public class Solution {

    public ListNode deleteMiddle(ListNode head) {
        int n = 0;
        for (ListNode curr = head; curr != null; curr = curr.next) n++;

        int mid = n / 2; ListNode curr = head;
        for (int i = 0; i < mid - 1; i++) curr = curr.next;

        if (curr.next == null) return null;
        else curr.next = curr.next.next;
        return head;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().deleteMiddle(ListNode.build(1,3,4,7,1,2,6)), ListNode.build(1,3,4,1,2,6));
        assert Checker.check(new Solution().deleteMiddle(ListNode.build(1,2,3,4)), ListNode.build(1,2,4));
        assert Checker.check(new Solution().deleteMiddle(ListNode.build(2,1)), ListNode.build(2));
    }

}
