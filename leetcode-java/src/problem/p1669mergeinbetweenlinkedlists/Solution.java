package problem.p1669mergeinbetweenlinkedlists;

import common.Checker;
import common.ListNode;

/**
 * 1669. Merge In Between Linked Lists
 *
 * https://leetcode.cn/problems/merge-in-between-linked-lists/
 *
 * You are given two linked lists: list1 and list2 of sizes n and m respectively.
 *
 * Remove list1's nodes from the ath node to the bth node, and put list2 in their place.
 *
 * Build the result list and return its head.
 */

public class Solution {

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode[] cuts = cutoff(list1, a, b);
        if (cuts[0] == null) list1 = list2;
        else cuts[0].next = list2;

        ListNode n2 = list2;
        while (n2.next != null) n2 = n2.next;
        n2.next = cuts[1];

        return list1;
    }

    private ListNode[] cutoff(ListNode list, int a, int b) {
        ListNode[] pts = new ListNode[2]; // [head1, head2]
        ListNode dummy = new ListNode(0, list);
        for (int i = 0; i < a && dummy != null; i++) {
            dummy = dummy.next;
        }

        if (dummy == null) return pts;
        pts[0] = dummy;

        for (; a <= b + 1 && dummy != null; a++) dummy = dummy.next;
        pts[1] = dummy;

        return pts;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().mergeInBetween(
            ListNode.build(0,1,2,3,4,5), 3, 4,
            ListNode.build(1000000,1000001,1000002)
        ), ListNode.build(0,1,2,1000000,1000001,1000002,5));

        assert Checker.check(new Solution().mergeInBetween(
            ListNode.build(0,1,2,3,4,5,6), 2, 5,
            ListNode.build(1000000,1000001,1000002,1000003,1000004)
        ), ListNode.build(0,1,1000000,1000001,1000002,1000003,1000004,6));
    }

}
