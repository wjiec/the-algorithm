package problem.p82removeduplicatesfromsortedlistii;

import common.ListNode;

/**
 * 82. Remove Duplicates from Sorted List II
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 *
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 *
 * Return the linked list sorted as well.
 */

public class Solution {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode dummy = new ListNode(101, head), slow = dummy;
        for (ListNode prev = dummy, curr = head; curr != null; curr = curr.next) {
            if (curr.val != prev.val && (curr.next == null || curr.next.val != curr.val)) {
                slow.next = curr;
                slow = slow.next;
            }
            prev = curr;
        }
        slow.next = null;
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().deleteDuplicates(ListNode.build(1,2,3,3,4,4,5)));
        System.out.println(new Solution().deleteDuplicates(ListNode.build(1,1,1,2,3)));

        System.out.println(new Solution().deleteDuplicates(ListNode.build(1)));
        System.out.println(new Solution().deleteDuplicates(ListNode.build(1,2,3,4,5)));
        System.out.println(new Solution().deleteDuplicates(ListNode.build(1,1,2,3,4,5)));
        System.out.println(new Solution().deleteDuplicates(ListNode.build(1,2,3,4,5,5,5)));
        System.out.println(new Solution().deleteDuplicates(ListNode.build(1,2,3,4,5,5,5,6)));
        System.out.println(new Solution().deleteDuplicates(ListNode.build(1,2,3,4,5,5,5,6,6)));
    }

}
