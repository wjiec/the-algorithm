package problem.p1721swappingnodesinalinkedlist;

import common.Checker;
import common.ListNode;

/**
 * 1721. Swapping Nodes in a Linked List
 *
 * https://leetcode.cn/problems/swapping-nodes-in-a-linked-list/
 *
 * You are given the head of a linked list, and an integer k.
 *
 * Return the head of the linked list after swapping the values of the kth node
 * from the beginning and the kth node from the end (the list is 1-indexed).
 */

public class Solution {

    public ListNode swapNodes(ListNode head, int k) {
        int n = 0; ListNode a = null, b = null;
        for (ListNode curr = head; curr != null; curr = curr.next) {
            if (++n == k) a = curr;
        }
        for (ListNode curr = head; curr != null; curr = curr.next) {
            if (n-- == k) { b = curr; break; }
        }

        int t = a.val;
        a.val = b.val;
        b.val = t;
        return head;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().swapNodes(ListNode.build(1,2,3,4,5), 2), ListNode.build(1,4,3,2,5));
        assert Checker.check(new Solution().swapNodes(ListNode.build(7,9,6,6,7,8,3,0,9,5), 5), ListNode.build(7,9,6,6,8,7,3,0,9,5));
        assert Checker.check(new Solution().swapNodes(ListNode.build(1), 1), ListNode.build(1));
        assert Checker.check(new Solution().swapNodes(ListNode.build(1,2), 1), ListNode.build(2,1));
        assert Checker.check(new Solution().swapNodes(ListNode.build(1,2,3), 2), ListNode.build(1,2,3));
    }

}
