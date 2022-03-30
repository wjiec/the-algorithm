package problem.p445addtwonumbersii;

import common.Checker;
import common.ListNode;

/**
 * 445. Add Two Numbers II
 *
 * https://leetcode-cn.com/problems/add-two-numbers-ii/
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */

public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r1 = reverse(l1), r2 = reverse(l2);

        int carry = 0;
        ListNode dummy = new ListNode(), c = dummy;
        for (ListNode c1 = r1, c2 = r2; c1 != null || c2 != null; ) {
            int v1 = c1 == null ? 0 : c1.val;
            int v2 = c2 == null ? 0 : c2.val;

            c.next = new ListNode(v1 + v2 + carry);
            carry = c.next.val / 10;
            c.next.val %= 10;

            c = c.next;
            if (c1 != null) c1 = c1.next;
            if (c2 != null) c2 = c2.next;
        }

        if (carry != 0) c.next = new ListNode(carry);
        return reverse(dummy.next);
    }

    public ListNode reverse(ListNode list) {
        ListNode prev = null, curr = list;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution()
                .addTwoNumbers(ListNode.build(7,2,4,3), ListNode.build(5,6,4)),
            ListNode.build(7,8,0,7));
        assert Checker.check(new Solution()
                .addTwoNumbers(ListNode.build(2,4,3), ListNode.build(5,6,4)),
            ListNode.build(8,0,7));
        assert Checker.check(new Solution()
                .addTwoNumbers(ListNode.build(0), ListNode.build(0)),
            ListNode.build(0));

        assert Checker.check(new Solution()
                .addTwoNumbers(ListNode.build(9,7,8,1), ListNode.build(2,1,9)),
            ListNode.build(1,0,0,0,0));
    }

}
