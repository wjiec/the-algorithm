package problem.p61rotatelist;

import common.ListNode;

/**
 * 61. Rotate List
 *
 * https://leetcode-cn.com/problems/rotate-list/
 *
 * Given the head of a linked list, rotate the list to the right by k places.
 */

public class Solution {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        int count = 1;
        ListNode tail = head, dummy = new ListNode(0, head);
        for (; tail.next != null; tail = tail.next) count++;
        if (k % count == 0) return head;

        tail.next = head;
        for (k = count - (k % count); k > 0; k--) dummy = dummy.next;

        ListNode ans = dummy.next;
        dummy.next = null;

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().rotateRight(ListNode.build(1,2), 1));

        System.out.println(new Solution().rotateRight(ListNode.build(1,2,3,4,5), 2));
        System.out.println(new Solution().rotateRight(ListNode.build(0,1,2), 4));
    }

}
