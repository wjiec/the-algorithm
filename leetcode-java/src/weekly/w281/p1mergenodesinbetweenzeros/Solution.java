package weekly.w281.p1mergenodesinbetweenzeros;

import common.ListNode;

/**
 * 6013. Merge Nodes in Between Zeros
 *
 * https://leetcode-cn.com/contest/weekly-contest-281/problems/merge-nodes-in-between-zeros/
 *
 * You are given the head of a linked list, which contains a series of integers separated by 0's.
 * The beginning and end of the linked list will have Node.val == 0.
 *
 * For every two consecutive 0's, merge all the nodes lying in between them into a single node whose value is
 * the sum of all the merged nodes. The modified list should not contain any 0's.
 *
 * Return the head of the modified linked list.
 */

public class Solution {

    public ListNode mergeNodes(ListNode head) {
        ListNode dummy = new ListNode(0), curr = dummy;
        for (ListNode iter = head; iter != null; iter = iter.next) {
            if (iter.val == 0 && iter.next != null) {
                curr.next = new ListNode(0);
                curr = curr.next;
            } else {
                curr.val += iter.val;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().mergeNodes(ListNode.build(0,3,1,0,4,5,2,0)));
        System.out.println(new Solution().mergeNodes(ListNode.build(0,1,0,3,0,2,2,0)));
    }

}
