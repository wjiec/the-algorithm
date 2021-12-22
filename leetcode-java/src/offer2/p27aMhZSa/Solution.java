package offer2.p27aMhZSa;

import common.ListNode;

/**
 * 剑指 Offer II 027. 回文链表
 *
 * https://leetcode-cn.com/problems/aMhZSa/
 *
 * 给定一个链表的 头节点 head ，请判断其是否为回文链表。
 *
 * 如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。
 */

public class Solution {

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode reversed = reverse(slow.next);

        boolean result = true;
        ListNode l = head, r = reversed;
        while (result && r != null) {
            if (l.val != r.val) {
                result = false;
                break;
            }

            l = l.next;
            r = r.next;
        }

        slow.next = reverse(reversed);
        return result;
    }

    private ListNode reverse(ListNode head) {
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
        assert new Solution().isPalindrome(ListNode.build(1,2,3,3,2,1));
        assert new Solution().isPalindrome(ListNode.build(1,2));
    }

}
