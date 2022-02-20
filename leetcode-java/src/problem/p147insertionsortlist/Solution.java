package problem.p147insertionsortlist;

import common.ListNode;

/**
 * 147. Insertion Sort List
 *
 * https://leetcode-cn.com/problems/insertion-sort-list/
 *
 * Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.
 *
 * The steps of the insertion sort algorithm:
 *
 * Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
 *
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs
 * within the sorted list and inserts it there.
 *
 * It repeats until no input elements remain.
 *
 * The following is a graphical example of the insertion sort algorithm. The partially
 * sorted list (black) initially contains only the first element in the list.
 * One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.
 */

public class Solution {

    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE, head);
        for (ListNode curr = head.next, sorted = head; curr != null;) {
            if (sorted.val <= curr.val) {
                sorted = sorted.next;
            } else {
                ListNode index = dummy;
                while (index.next.val <= curr.val) index = index.next;

                sorted.next = curr.next;
                curr.next = index.next;
                index.next = curr;
            }
            curr = sorted.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().insertionSortList(ListNode.build(4,2,1,3)));
        System.out.println(new Solution().insertionSortList(ListNode.build(-1,5,3,4,0)));
    }

}
