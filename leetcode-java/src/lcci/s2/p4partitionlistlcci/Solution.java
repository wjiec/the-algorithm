package lcci.s2.p4partitionlistlcci;

import common.Checker;
import common.ListNode;

/**
 * 面试题 02.04. 分割链表
 *
 * https://leetcode.cn/problems/partition-list-lcci/
 *
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，
 * 使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你不需要 保留 每个分区中各节点的初始相对位置。
 */

public class Solution {

    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode();
        ListNode left = dummy, right = null;
        while (head != null) {
            ListNode next = head.next;
            if (head.val < x) {
                left.next = head;
                left = left.next;
            } else {
                head.next = right;
                right = head;
            }

            head = next;
        }

        left.next = right;
        return dummy.next;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().partition(ListNode.build(1,4,3,2,5,2), 3), ListNode.build(2,2,1,5,3,4));
        assert Checker.check(new Solution().partition(ListNode.build(2,1), 2), ListNode.build(1,2));
    }

}
