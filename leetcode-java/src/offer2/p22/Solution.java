package offer2.p22;

import common.ListNode;

/**
 * 剑指 Offer II 022. 链表中环的入口节点
 *
 * https://leetcode.cn/problems/c32eOV/
 *
 * 给定一个链表，返回链表开始入环的第一个节点。从链表的头节点开始沿着 next 指针进入环的第一个节点为环的入口节点。
 * 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 *
 * 说明：不允许修改给定的链表。
 */

public class Solution {

    // 环外长度为 A, 环内长度为 B
    // 此时 fast 走过的路径 = 2 * slow 走过的路径
    // fast = A + fB, slow = A + sB
    // A + fB = 2 * (A + sB)
    // A + fB = 2A + 2sB
    // A = (f - 2s)B
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                while (head != fast) {
                    head = head.next;
                    fast = fast.next;
                }
                return head;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        assert new Solution().detectCycle(ListNode.build(1, 2, 3, 4)) == null;
    }

}
