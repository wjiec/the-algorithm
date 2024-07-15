package weekly.w406.B;

import common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 3217. Delete Nodes From Linked List Present in Array
 *
 * https://leetcode.cn/contest/weekly-contest-406/problems/delete-nodes-from-linked-list-present-in-array/
 *
 * You are given an array of integers nums and the head of a linked list. Return the head of the modified
 * linked list after removing all nodes from the linked list that have a value that exists in nums.
 */

public class Solution {

    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> unique = new HashSet<>();
        for (var v : nums) unique.add(v);

        ListNode dummy = new ListNode(-1, head);
        for (var curr = dummy; curr.next != null; ) {
            if (unique.contains(curr.next.val)) curr.next = curr.next.next;
            else curr = curr.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
    }

}
