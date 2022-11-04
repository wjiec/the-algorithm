package offer2.p25;

import common.Checker;
import common.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer II 025. 链表中的两数相加
 *
 * https://leetcode.cn/problems/lMSNwu/
 *
 * 给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<ListNode> stack1 = new ArrayDeque<>();
        Deque<ListNode> stack2 = new ArrayDeque<>();
        for (ListNode curr = l1; curr != null; curr = curr.next) stack1.push(curr);
        for (ListNode curr = l2; curr != null; curr = curr.next) stack2.push(curr);

        ListNode ans = null; int carry = 0;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int v = stack1.pop().val + stack2.pop().val + carry;
            ans = new ListNode(v % 10, ans); carry = v / 10;
        }

        while (!stack1.isEmpty()) {
            int v = stack1.pop().val + carry;
            ans = new ListNode(v % 10, ans); carry = v / 10;
        }

        while (!stack2.isEmpty()) {
            int v = stack2.pop().val + carry;
            ans = new ListNode(v % 10, ans); carry = v / 10;
        }

        if (carry != 0) ans = new ListNode(1, ans);

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().addTwoNumbers(
            ListNode.build(7,2,4,3), ListNode.build(5,6,4)
        ), ListNode.build(7,8,0,7));

        assert Checker.check(new Solution().addTwoNumbers(
            ListNode.build(2,4,3), ListNode.build(5,6,4)
        ), ListNode.build(8,0,7));

        assert Checker.check(new Solution().addTwoNumbers(
            ListNode.build(0), ListNode.build(0)
        ), ListNode.build(0));
    }

}
