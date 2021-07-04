package offer.p25hebinglianggepaixudelianbiaolcof;

import common.ListNode;

/**
 * 剑指 Offer 25. 合并两个排序的链表
 *
 * https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
 *
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 */

public class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode root = new ListNode(), curr = root;
        for (; l1 != null && l2 != null; curr = curr.next) {
            if (l1.val <= l2.val) {
                curr.next = l1; l1 = l1.next;
            } else {
                curr.next = l2; l2 = l2.next;
            }
        }

        if (l1 != null) curr.next = l1;
        if (l2 != null) curr.next = l2;

        return root.next;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().mergeTwoLists(ListNode.build(1,2,4), ListNode.build(1,3,4)));
    }

}
