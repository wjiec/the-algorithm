package offer2.p21;

import common.Checker;
import common.ListNode;

/**
 * 剑指 Offer II 021. 删除链表的倒数第 n 个结点
 *
 * https://leetcode.cn/problems/SLwz0R/
 *
 * 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */

public class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int curr = dfs(head, n) + 1;
        if (curr == n + 1) {
            return head.next;
        }
        return head;
    }

    private int dfs(ListNode node, int n) {
        if (node == null) return 0;

        int curr = dfs(node.next, n) + 1;
        if (curr == n + 1) {
            node.next = node.next.next;
        }
        return curr;
    }

    private static class DoublePointer {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0, head);
            ListNode left = dummy, right = head;
            for (int i = 0; i < n; i++) right = right.next;
            while (right != null) { left = left.next; right = right.next; }
            left.next = left.next.next;
            return dummy.next;
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().removeNthFromEnd(ListNode.build(1,2,3,4,5), 2), ListNode.build(1,2,3,5));
        assert new Solution().removeNthFromEnd(ListNode.build(1), 1) == null;
        assert Checker.check(new Solution().removeNthFromEnd(ListNode.build(1,2), 1), ListNode.build(1));
        assert Checker.check(new Solution().removeNthFromEnd(ListNode.build(1,2,3,4,5), 5), ListNode.build(2,3,4,5));

        assert Checker.check(new DoublePointer().removeNthFromEnd(ListNode.build(1,2,3,4,5), 2), ListNode.build(1,2,3,5));
        assert new DoublePointer().removeNthFromEnd(ListNode.build(1), 1) == null;
        assert Checker.check(new DoublePointer().removeNthFromEnd(ListNode.build(1,2), 1), ListNode.build(1));
        assert Checker.check(new DoublePointer().removeNthFromEnd(ListNode.build(1,2,3,4,5), 5), ListNode.build(2,3,4,5));

    }

}
