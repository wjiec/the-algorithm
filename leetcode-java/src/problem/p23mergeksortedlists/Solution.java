package problem.p23mergeksortedlists;

import common.ListNode;
import common.PrettyPrinter;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 *
 * https://leetcode.cn/problems/merge-k-sorted-lists/
 *
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 */

public class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.val));
        for (var node : lists) if (node != null) pq.add(node);
        for (ListNode curr = dummy; !pq.isEmpty(); curr = curr.next) {
            ListNode node = pq.remove();
            curr.next = node;

            if (node.next != null) pq.add(node.next);
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().mergeKLists(new ListNode[]{
            ListNode.build(1,4,5),
            ListNode.build(1,3,4),
            ListNode.build(2,6),
        }));
    }

}
