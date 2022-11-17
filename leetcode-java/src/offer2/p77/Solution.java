package offer2.p77;

import common.Checker;
import common.ListNode;

/**
 * 剑指 Offer II 077. 链表排序
 *
 * https://leetcode.cn/problems/7WHec2/
 *
 * 给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 */

public class Solution {

    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    private ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) return null;
        if (head.next == tail) { head.next = null; return head; }

        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }

        return merge(sortList(head, slow), sortList(slow, tail));
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(), curr = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                curr.next = left;
                curr = curr.next;
                left = left.next;
            } else {
                curr.next = right;
                curr = curr.next;
                right = right.next;
            }
        }
        if (left != null) curr.next = left;
        if (right != null) curr.next = right;
        return dummy.next;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().sortList(ListNode.build(5,8,5)),
            ListNode.build(5,5,8));
        assert Checker.check(new Solution().sortList(ListNode.build(5,5,5)),
            ListNode.build(5,5,5));

        assert Checker.check(new Solution().sortList(ListNode.build(4,19,14,5,-3,1,8,5,11,15)),
            ListNode.build(-3,1,4,5,5,8,11,14,15,19));

        assert Checker.check(new Solution().sortList(ListNode.build(4,2,1,3)), ListNode.build(1,2,3,4));
        assert Checker.check(new Solution().sortList(ListNode.build(-1,5,3,4,0)), ListNode.build(-1,0,3,4,5));
        assert Checker.check(new Solution().sortList(ListNode.build()), ListNode.build());
    }

}
