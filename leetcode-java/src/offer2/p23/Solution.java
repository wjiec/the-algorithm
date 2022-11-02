package offer2.p23;

import common.ListNode;

/**
 * 剑指 Offer II 023. 两个链表的第一个重合节点
 *
 * https://leetcode-cn.com/problems/3u1WK4/
 *
 * 给定两个单链表的头节点 headA 和 headB ，请找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
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
        assert new Solution().getIntersectionNode(ListNode.build(1,2,3,4,5,6,1), ListNode.build(4,5,6)) == null;
    }

}
