package problem.p1474deletennodesaftermnodesofalinkedlist;

import common.ListNode;

/**
 * 1474. Delete N Nodes After M Nodes of a Linked List
 *
 * https://leetcode-cn.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/
 *
 * You are given the head of a linked list and two integers m and n.
 *
 * Traverse the linked list and remove some nodes in the following way:
 *
 * Start with the head as the current node.
 * Keep the first m nodes starting with the current node.
 * Remove the next n nodes
 * Keep repeating steps 2 and 3 until you reach the end of the list.
 * Return the head of the modified list after removing the mentioned nodes.
 */

public class Solution {

    public ListNode deleteNodes(ListNode head, int m, int n) {
        for (ListNode curr = head; curr != null; ) {
            for (int i = 1; i < m && curr != null; i++) curr = curr.next;

            ListNode next = curr;
            for (int i = 0; i <= n && next != null; i++) next = next.next;
            if (curr != null) curr = curr.next = next;
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().deleteNodes(ListNode.build(1,2,3,4,5,6,7,8,9,10,11,12,13), 2, 3));
        System.out.println(new Solution().deleteNodes(ListNode.build(1,2,3,4,5,6,7,8,9,10,11), 1, 3));
        System.out.println(new Solution().deleteNodes(ListNode.build(1,2,3,4,5,6,7,8,9,10,11), 3, 1));
        System.out.println(new Solution().deleteNodes(ListNode.build(9,3,7,7,9,10,8,2), 1, 2));
    }

}
