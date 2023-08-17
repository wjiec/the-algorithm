package weekly.w358.B;

import common.ListNode;

/**
 * 2816. Double a Number Represented as a Linked List
 *
 * https://leetcode.cn/contest/weekly-contest-358/problems/double-a-number-represented-as-a-linked-list/
 *
 * You are given the head of a non-empty linked list representing a non-negative integer without leading zeroes.
 *
 * Return the head of the linked list after doubling it.
 */

public class Solution {

    public ListNode doubleIt(ListNode head) {
        head = reverse(head);
        return reverse(add(head, head));
    }

    private ListNode add(ListNode a, ListNode b) {
        int carry = 0;
        ListNode dummy = new ListNode(), ai = a, bi = b;
        ListNode curr = dummy;
        while (ai != null || bi != null) {
            curr.next = new ListNode((ai != null ? ai.val : 0) + (bi != null ? bi.val : 0) + carry);
            carry = curr.next.val / 10;
            curr.next.val %= 10;
            curr = curr.next;

            if (ai != null) ai = ai.next;
            if (bi != null) bi = bi.next;
        }
        if (carry != 0) curr.next = new ListNode(carry);

        return dummy.next;
    }

    private ListNode reverse(ListNode list) {
        ListNode tail = null;
        while (list != null) {
            ListNode next = list.next;
            list.next = tail;
            tail = list;
            list = next;
        }
        return tail;
    }

    public static void main(String[] args) {
    }

}
