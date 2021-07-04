package offer.p24fanzhuanlianbiaolcof;

import common.ListNode;

/**
 * 剑指 Offer 24. 反转链表
 *
 * https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
 *
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 */

public class Solution {

    public ListNode reverseList(ListNode head) {
        ListNode root = null;
        for (ListNode next; head != null; head = next) {
            next = head.next;
            head.next = root;
            root = head;
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverseList(ListNode.build(1,2,3,4,5,6)));
    }

}
