package problem.p2573removenodesfromlinkedlist;

import common.Checker;
import common.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 6247. Remove Nodes From Linked List
 *
 * https://leetcode.cn/problems/remove-nodes-from-linked-list/
 *
 * You are given the head of a linked list.
 *
 * Remove every node which has a node with a strictly greater value anywhere to the right side of it.
 *
 * Return the head of the modified linked list.
 */

public class Solution {

    public ListNode removeNodes(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<>();
        for (; head != null; head = head.next) {
            while (!stack.isEmpty() && stack.peek().val < head.val) {
                stack.pop();
            }
            stack.push(head);
        }

        ListNode ans = null;
        while (!stack.isEmpty()) {
            stack.peek().next = ans;
            ans = stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().removeNodes(ListNode.build(5,2,13,3,8)), ListNode.build(13,8));
        assert Checker.check(new Solution().removeNodes(ListNode.build(1,1,1,1)), ListNode.build(1,1,1,1));
    }

}
