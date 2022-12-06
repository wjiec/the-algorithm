package problem.p25reversenodesinkgroup;

import common.Checker;
import common.ListNode;
import common.Tag;

/**
 * 25. Reverse Nodes in k-Group
 *
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/
 *
 * Given the head of a linked list, reverse the nodes of the list k
 * at a time, and return the modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes, in the end, should
 * remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 */

public class Solution {

    @Tag({"按组翻转链表"})
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1 || head == null) return head;

        int len = 1;
        ListNode curr = head;
        while (curr != null && len < k) {
            curr = curr.next;
            if (curr != null) len++;
        }

        ListNode next = null;
        if (curr != null) {
            next = reverseKGroup(curr.next, k);
            curr.next = null;
        }

        if (len == k) {
            ListNode ans = reverse(head);
            head.next = next;
            return ans;
        }

        return head;
    }

    private ListNode reverse(ListNode head) {
        ListNode tail = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = tail;
            tail = head;
            head = next;
        }
        return tail;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().reverseKGroup(ListNode.build(1,2,3,4,5), 2), ListNode.build(2,1,4,3,5));
        assert Checker.check(new Solution().reverseKGroup(ListNode.build(1,2,3,4,5), 3), ListNode.build(3,2,1,4,5));
    }

}
