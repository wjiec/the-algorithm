package offer2.p78;

import common.Checker;
import common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 剑指 Offer II 078. 合并排序链表
 *
 * https://leetcode.cn/problems/vvXgSW/
 *
 * 给定一个链表数组，每个链表都已经按升序排列。
 *
 * 请将所有链表合并到一个升序链表中，返回合并后的链表。
 */

public class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.val));
        for (var v : lists) if (v != null) pq.add(v);

        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.remove();
            curr.next = node;
            curr = curr.next;

            if (node.next != null) pq.add(node.next);
            curr.next = null;
        }
        return dummy.next;
    }

    private static class Merger {
        public ListNode mergeKLists(ListNode[] lists) {
            ListNode ans = null;
            for (var list : lists) ans = merge(ans, list);
            return ans;
        }

        private ListNode merge(ListNode a, ListNode b) {
            if (a == null) return b;
            if (b == null) return a;

            ListNode dummy = new ListNode(), curr = dummy;
            while (a != null && b != null) {
                if (a.val < b.val) { curr.next = a; a = a.next; }
                else { curr.next = b; b = b.next; }

                curr = curr.next;
                curr.next = null;

            }

            if (a != null) curr.next = a;
            if (b != null) curr.next = b;

            return dummy.next;
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().mergeKLists(new ListNode[]{
            ListNode.build(1,4,5), ListNode.build(1,3,4), ListNode.build(2,6)
        }), ListNode.build(1,1,2,3,4,4,5,6));
        assert Checker.check(new Solution().mergeKLists(new ListNode[]{}), ListNode.build());
        assert Checker.check(new Solution().mergeKLists(new ListNode[]{ListNode.build()}), ListNode.build());

        assert Checker.check(new Merger().mergeKLists(new ListNode[]{
            ListNode.build(1,4,5), ListNode.build(1,3,4), ListNode.build(2,6)
        }), ListNode.build(1,1,2,3,4,4,5,6));
        assert Checker.check(new Merger().mergeKLists(new ListNode[]{}), ListNode.build());
        assert Checker.check(new Merger().mergeKLists(new ListNode[]{ListNode.build()}), ListNode.build());
    }

}
