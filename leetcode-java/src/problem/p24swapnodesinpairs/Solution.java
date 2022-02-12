package problem.p24swapnodesinpairs;

import common.ListNode;

/**
 * 24. Swap Nodes in Pairs
 *
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 *
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem
 * without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 */

public class Solution {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy, fast = head.next;
        while (fast != null) {
            var node = slow.next;
            slow.next = fast;
            node.next = fast.next;
            fast.next = node;

            slow = slow.next.next;
            if (slow.next == null) break;
            fast = slow.next.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().swapPairs(ListNode.build(1,2,3,4)));
        System.out.println(new Solution().swapPairs(ListNode.build(1,2,3,4,5)));
        System.out.println(new Solution().swapPairs(ListNode.build(1,2,3,4,5,6)));
        System.out.println(new Solution().swapPairs(null));
        System.out.println(new Solution().swapPairs(ListNode.build(1)));
    }

}
