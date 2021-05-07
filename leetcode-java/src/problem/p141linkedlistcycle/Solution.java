package problem.p141linkedlistcycle;

import common.ListNode;

/**
 * 141. Linked List Cycle
 *
 * https://leetcode-cn.com/problems/linked-list-cycle/
 *
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that
 * can be reached again by continuously following the next pointer.
 *
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to.
 * Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false
 */

public class Solution {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head, fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    // broken list
    public boolean hasCycle0(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode v = new ListNode();
        for (ListNode prev = head, curr = head.next; curr != null; prev = curr, curr = curr.next) {
            prev.next = v;
            if (curr.next == v) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode l0 = ListNode.build(3,2,0,4);
        l0.next.next.next.next = l0.next.next;
        assert new Solution().hasCycle(l0);
        assert !new Solution().hasCycle(ListNode.build(1,2,3,4));
    }

}
