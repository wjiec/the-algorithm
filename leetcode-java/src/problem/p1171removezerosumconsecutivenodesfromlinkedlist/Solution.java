package problem.p1171removezerosumconsecutivenodesfromlinkedlist;

import common.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1171. Remove Zero Sum Consecutive Nodes from Linked List
 *
 * https://leetcode.cn/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
 *
 * Given the head of a linked list, we repeatedly delete consecutive sequences of nodes
 * that sum to 0 until there are no such sequences.
 *
 * After doing so, return the head of the final linked list. You may return any such answer.
 */

public class Solution {

    public ListNode removeZeroSumSublists(ListNode head) {
        int top = -1;
        ListNode[] stack = new ListNode[1001];
        for (ListNode curr = head; curr != null; curr = curr.next) {
            if (curr.val == 0) continue;
            if (top >= 0) {
                int tt = top, sum = curr.val;
                while (tt >= 0 && sum != 0) {
                    sum += stack[tt--].val;
                }
                if (sum == 0) {
                    top = tt;
                    continue;
                }
            }
            stack[++top] = curr;
        }
        if (top < 0) return null;

        ListNode ans = stack[0], curr = ans;
        for (int i = 1; i <= top; i++) {
            curr.next = stack[i];
            curr = curr.next;
        }
        curr.next = null;

        return ans;
    }

    private static class Optimization {
        public ListNode removeZeroSumSublists(ListNode head) {
            ListNode dummy = new ListNode(0, head);
            Map<Integer, ListNode> map = new HashMap<>();

            int sum = 0;
            for (ListNode curr = dummy; curr != null; curr = curr.next) {
                sum += curr.val;
                map.put(sum, curr);
            }

            sum = 0;
            for (ListNode curr = dummy; curr != null; curr = curr.next) {
                sum += curr.val;
                curr.next = map.get(sum).next;
            }
            return dummy.next;
        }
    }

    public static void main(String[] args) {
        // 5,-2,-5
        System.out.println(new Solution().removeZeroSumSublists(ListNode.build(5,-3,-4,1,6,-2,-5)));

        // 3,1 or 1,2,1
        System.out.println(new Solution().removeZeroSumSublists(ListNode.build(1,2,-3,3,1)));
        // 1,2,4
        System.out.println(new Solution().removeZeroSumSublists(ListNode.build(1,2,3,-3,4)));
        // 1
        System.out.println(new Solution().removeZeroSumSublists(ListNode.build(1,2,3,-3,-2)));
        System.out.println(new Solution().removeZeroSumSublists(ListNode.build(1,2,3,-3,-2,-1)));


        System.out.println(new Optimization().removeZeroSumSublists(ListNode.build(5,-3,-4,1,6,-2,-5)));
        System.out.println(new Optimization().removeZeroSumSublists(ListNode.build(1,2,-3,3,1)));
        System.out.println(new Optimization().removeZeroSumSublists(ListNode.build(1,2,3,-3,4)));
        System.out.println(new Optimization().removeZeroSumSublists(ListNode.build(1,2,3,-3,-2)));
        System.out.println(new Optimization().removeZeroSumSublists(ListNode.build(1,2,3,-3,-2,-1)));
    }

}
