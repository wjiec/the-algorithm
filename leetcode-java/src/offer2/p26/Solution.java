package offer2.p26;

import common.Checker;
import common.ListNode;

/**
 * 剑指 Offer II 026. 重排链表
 *
 * https://leetcode.cn/problems/LGjMqU/
 *
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 *  L0 → L1 → … → Ln-1 → Ln
 * 请将其重新排列后变为：
 *
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 *
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */

public class Solution {

    public void reorderList(ListNode head) {
        if (head == null) return;

        ListNode mid = middle(head);

        ListNode right = mid.next; mid.next = null;
        right = reverse(right);

        merge(head, right);
    }

    private void merge(ListNode a, ListNode b) {
        while (a != null && b != null) {
            ListNode an = a.next, bn = b.next;

            a.next = b; a = an;
            b.next = a; b = bn;
        }
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

    private ListNode middle(ListNode list) {
        ListNode slow = list, fast = list;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode list = ListNode.build(1,2,3,4);
        new Solution().reorderList(list);
        assert Checker.check(list, ListNode.build(1,4,2,3));
    }

}
