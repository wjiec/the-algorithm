package problem.p876middleofthelinkedlist;

import common.ListNode;

/**
 * 876. Middle of the Linked List
 *
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 *
 * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
 *
 * If there are two middle nodes, return the second middle node.
 */

public class Solution {

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        assert new Solution().middleNode(ListNode.build(1,2,3,4,5)).val == 3;
        assert new Solution().middleNode(ListNode.build(1,2,3,4,5,6)).val == 4;
    }

}
