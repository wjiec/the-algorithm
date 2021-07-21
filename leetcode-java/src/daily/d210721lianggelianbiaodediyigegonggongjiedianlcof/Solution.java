package daily.d210721lianggelianbiaodediyigegonggongjiedianlcof;

import common.ListNode;

/**
 * 剑指 Offer 52. 两个链表的第一个公共节点
 *
 * https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
 *
 * 输入两个链表，找出它们的第一个公共节点。
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
        System.out.println(new Solution().getIntersectionNode(ListNode.build(1,2,3), ListNode.build(4,5,6)));
    }

}
