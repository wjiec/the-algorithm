package lcci2.p7intersectionoftwolinkedlistslcci;

import common.ListNode;

/**
 * 面试题 02.07. 链表相交
 *
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci/
 *
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 */

public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    public static void main(String[] args) {
        assert new Solution().getIntersectionNode(ListNode.build(1,2,3,4,5,6), ListNode.build(9,3,8,7)).val == 3;
    }

}
