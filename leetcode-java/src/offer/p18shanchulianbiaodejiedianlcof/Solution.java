package offer.p18shanchulianbiaodejiedianlcof;

import common.ListNode;

/**
 * 剑指 Offer 18. 删除链表的节点
 *
 * https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
 *
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * 返回删除后的链表的头节点。
 */

public class Solution {

    public ListNode deleteNode(ListNode head, int val) {
        ListNode root = new ListNode(-1, head);
        for (var node = root; node.next != null; ) {
            if (node.next.val == val) node.next = node.next.next;
            else node = node.next;
        }
        return root.next;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().deleteNode(ListNode.build(4,5,1,9), 5));
        System.out.println(new Solution().deleteNode(ListNode.build(4,5,1,9), 1));
        System.out.println(new Solution().deleteNode(ListNode.build(1,1,1,1), 1));
    }

}
