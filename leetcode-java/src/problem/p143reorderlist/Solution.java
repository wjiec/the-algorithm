package problem.p143reorderlist;

import common.ListNode;

/**
 * 143. Reorder List
 *
 * https://leetcode-cn.com/problems/reorder-list/
 *
 * You are given the head of a singly linked-list. The list can be represented as:
 * L0 → L1 → … → Ln - 1 → Ln
 *
 * Reorder the list to be on the following form:
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 *
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 */

public class Solution {

    public void reorderList(ListNode head) {
        ListNode mid = middleNode(head);
        ListNode reversed = reverse(mid.next);
        mid.next = null;
        merge(head, reversed);
    }

    private ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    private void merge(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            ListNode a = l1.next, b = l2.next;

            l1.next = l2;
            l1 = a;

            l2.next = l1;
            l2 = b;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = ListNode.build(1,2,3,4);
        new Solution().reorderList(l1);
        System.out.println(l1);

        ListNode l2 = ListNode.build(1,2,3,4,5);
        new Solution().reorderList(l2);
        System.out.println(l2);
    }

}
