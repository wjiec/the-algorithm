package lcci.s2.p5sumlistslcci;

import common.Checker;
import common.ListNode;

/**
 * 面试题 02.05. 链表求和
 *
 * https://leetcode.cn/problems/sum-lists-lcci/
 *
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 *
 * 这些数位是反向存放的，也就是个位排在链表首部。
 *
 * 编写函数对这两个整数求和，并用链表形式返回结果。
 */

public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();

        int carry = 0; ListNode curr = dummy;
        while (l1 != null || l2 != null || carry != 0) {
            int s = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            curr.next = new ListNode(s % 10); carry = s / 10;
            curr = curr.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().addTwoNumbers(ListNode.build(7,1,6), ListNode.build(5,9,2)), ListNode.build(2,1,9));
        assert Checker.check(new Solution().addTwoNumbers(ListNode.build(6,1,7), ListNode.build(2,9,5)), ListNode.build(9,1,2));
    }

}
