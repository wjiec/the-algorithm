package problem.p142linkedlistcycleii;

import common.ListNode;

/**
 * 142. Linked List Cycle II
 *
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 *
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by
 * continuously following the next pointer. Internally, pos is used to denote the index of the node
 * that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle.
 *
 * Note that pos is not passed as a parameter.
 *
 * Do not modify the linked list.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode loop = head;
                while (loop != slow) {
                    slow = slow.next;
                    loop = loop.next;
                }
                return loop;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        assert new Solution().detectCycle(ListNode.build(3,2,0,-4)) == null;
    }

}
