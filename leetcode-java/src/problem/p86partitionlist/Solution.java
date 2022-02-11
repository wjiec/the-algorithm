package problem.p86partitionlist;

import common.ListNode;

/**
 * 86. Partition List
 *
 * https://leetcode-cn.com/problems/partition-list/
 *
 * Given the head of a linked list and a value x, partition it such that
 * all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 */

public class Solution {

    public ListNode partition(ListNode head, int x) {
        ListNode less = new ListNode(), great = new ListNode();
        ListNode lt = less, gt = great;
        for (ListNode curr = head; curr != null; curr = curr.next) {
            if (curr.val < x) {
                lt.next = curr;
                lt = lt.next;
            } else {
                gt.next = curr;
                gt = gt.next;
            }
        }
        lt.next = great.next;
        gt.next = null;
        return less.next;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().partition(ListNode.build(1,4,3,2,5,2), 3));
        System.out.println(new Solution().partition(ListNode.build(2,1), 2));
    }

}
