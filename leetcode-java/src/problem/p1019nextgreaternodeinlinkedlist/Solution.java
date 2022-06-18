package problem.p1019nextgreaternodeinlinkedlist;

import common.Checker;
import common.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 1019. Next Greater Node In Linked List
 *
 * https://leetcode.cn/problems/next-greater-node-in-linked-list/
 *
 * You are given the head of a linked list with n nodes.
 *
 * For each node in the list, find the value of the next greater node. That is, for each node, find
 * the value of the first node that is next to it and has a strictly larger value than it.
 *
 * Return an integer array answer where answer[i] is the value of the next greater node of
 * the ith node (1-indexed). If the ith node does not have a next greater node, set answer[i] = 0.
 */

public class Solution {

    public int[] nextLargerNodes(ListNode head) {
        int idx = 0;
        // [value, index]
        Deque<int[]> stack = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (ListNode curr = head; curr != null; curr = curr.next) {
            list.add(0);
            while (!stack.isEmpty() && stack.peek()[0] < curr.val) {
                list.set(stack.pop()[1], curr.val);
            }
            stack.push(new int[]{curr.val, idx++});
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().nextLargerNodes(ListNode.build(2,1,5)), new int[]{5,5,0});
        assert Checker.check(new Solution().nextLargerNodes(ListNode.build(2,7,4,3,5)), new int[]{7,0,5,5,0});
    }

}
