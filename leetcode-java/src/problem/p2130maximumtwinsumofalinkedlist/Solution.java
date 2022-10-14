package problem.p2130maximumtwinsumofalinkedlist;

import common.ListNode;

/**
 * 2130. Maximum Twin Sum of a Linked List
 *
 * https://leetcode.cn/problems/maximum-twin-sum-of-a-linked-list/
 *
 * In a linked list of size n, where n is even, the ith node (0-indexed) of the
 * linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
 *
 * For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2.
 * These are the only nodes with twins for n = 4.
 * The twin sum is defined as the sum of a node and its twin.
 *
 * Given the head of a linked list with even length, return the maximum twin sum of the linked list.
 */

public class Solution {

    public int pairSum(ListNode head) {
        int len = 0;
        for (ListNode curr = head; curr != null; curr = curr.next) len++;

        int i = 0, ans = 0, half = len / 2;
        int[] twinSum = new int[len / 2];
        for (ListNode curr = head; curr != null; curr = curr.next) {
            if (i < half) twinSum[i++] = curr.val;
            else ans = Math.max(ans, curr.val + twinSum[len - i++ - 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().pairSum(ListNode.build(5,4,2,1)) == 6;
        assert new Solution().pairSum(ListNode.build(4,2,2,3)) == 7;
        assert new Solution().pairSum(ListNode.build(1,100000)) == 100001;
    }

}
