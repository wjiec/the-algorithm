package problem.p2074reversenodesinevenlengthgroups;

import common.Checker;
import common.ListNode;

/**
 * 2074. Reverse Nodes in Even Length Groups
 *
 * https://leetcode.cn/problems/reverse-nodes-in-even-length-groups/
 *
 * You are given the head of a linked list.
 *
 * The nodes in the linked list are sequentially assigned to non-empty groups whose lengths
 * form the sequence of the natural numbers (1, 2, 3, 4, ...). The length of a group is the
 * number of nodes assigned to it. In other words,
 *
 * The 1st node is assigned to the first group.
 * The 2nd and the 3rd nodes are assigned to the second group.
 * The 4th, 5th, and 6th nodes are assigned to the third group, and so on.
 * Note that the length of the last group may be less than or equal to 1 + the length of
 * the second to last group.
 *
 * Reverse the nodes in each group with an even length, and return the head of the modified linked list.
 */

public class Solution {

    public ListNode reverseEvenLengthGroups(ListNode head) {
        int len = 0; ListNode curr = head;
        while (curr != null) { curr = curr.next; len++; }

        ListNode[] nodes = new ListNode[len + 1];
        for (int i = 0; i < len; i++) {
            nodes[i] = head; head = head.next;
        }

        for (int i = 0, j = 1; i < len; i += j++) {
            int end = Math.min(len, i + j);
            if ((end - i) % 2 == 0) {
                for (int l = i, r = end - 1; l < r; l++, r--) {
                    ListNode temp = nodes[l];
                    nodes[l] = nodes[r];
                    nodes[r] = temp;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            nodes[i].next = nodes[i + 1];
        }

        return nodes[0];
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().reverseEvenLengthGroups(
            ListNode.build(5,2,6,3,9,1,7,3,8,4)
        ), ListNode.build(5,6,2,3,9,1,4,8,3,7));

        assert Checker.check(new Solution().reverseEvenLengthGroups(
            ListNode.build(1,1,0,6)
        ), ListNode.build(1,0,1,6));

        assert Checker.check(new Solution().reverseEvenLengthGroups(
            ListNode.build(1,1,0,6,5)
        ), ListNode.build(1,0,1,5,6));

        assert Checker.check(new Solution().reverseEvenLengthGroups(
            ListNode.build(2,1)
        ), ListNode.build(2,1));
    }

}
