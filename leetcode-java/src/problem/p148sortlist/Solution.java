package problem.p148sortlist;

import common.ListNode;

/**
 * 148. Sort List
 *
 * https://leetcode-cn.com/problems/sort-list/
 *
 * Given the head of a linked list, return the list after sorting it in ascending order.
 */

public class Solution {

    public ListNode sortList(ListNode head) {
        if (head == null) return null;

        int len = 0;
        for (ListNode curr = head; curr != null; curr = curr.next) len++;

        ListNode dummy = new ListNode(0, head);
        for (int subLen = 1; subLen < len; subLen *= 2) {
            ListNode prev = dummy, curr = dummy.next;
            while (curr != null) {
                ListNode a = curr;
                for (int i = 1; i < subLen && curr.next != null; i++) curr = curr.next;

                ListNode b = curr.next; curr.next = null; curr = b;
                for (int i = 1; i < subLen && curr != null; i++) curr = curr.next;

                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }

                prev.next = merge(a, b);
                while (prev.next != null) prev = prev.next;
                curr = next;
            }
        }

        return dummy.next;
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(), curr = dummy;
        for (; a != null && b != null; curr = curr.next) {
            if (a.val < b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
        }

        if (a != null) curr.next = a;
        if (b != null) curr.next = b;

        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().sortList(ListNode.build(4,2,1,3)));
        System.out.println(new Solution().sortList(ListNode.build(-1,5,3,4,0)));
        System.out.println(new Solution().sortList(ListNode.build()));

        System.out.println(new Solution().sortList(ListNode.build(2,1,3)));
        System.out.println(new Solution().sortList(ListNode.build(2,1)));
        System.out.println(new Solution().sortList(ListNode.build(1)));
    }

}
