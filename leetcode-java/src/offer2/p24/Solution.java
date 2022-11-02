package offer2.p24;

import common.ListNode;

/**
 * 剑指 Offer II 024. 反转链表
 *
 * https://leetcode-cn.com/problems/UHnkqh/
 *
 * 给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。
 */

public class Solution {

    public ListNode reverseList(ListNode head) {
        ListNode root = null, next;
        while (head != null) {
            next = head.next;
            head.next = root;
            root = head;
            head = next;
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverseList(ListNode.build(1,2,3,4,5,6)));
    }

}
