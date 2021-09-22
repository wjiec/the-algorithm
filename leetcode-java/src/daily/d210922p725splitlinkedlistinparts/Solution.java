package daily.d210922p725splitlinkedlistinparts;

import common.ListNode;

import java.util.Arrays;

/**
 * 725. Split Linked List in Parts
 *
 * https://leetcode-cn.com/problems/split-linked-list-in-parts/
 *
 * Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
 *
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than one.
 * This may lead to some parts being null.
 *
 * The parts should be in the order of occurrence in the input list,
 * and parts occurring earlier should always have a size greater than or equal to parts occurring later.
 *
 * Return an array of the k parts.
 */

public class Solution {

    public ListNode[] splitListToParts(ListNode head, int k) {
        if (head == null) return new ListNode[k];
        int len = 0; ListNode[] ans = new ListNode[k];
        for (var curr = head; curr != null; curr = curr.next) len++;

        int avg = len / k, remain = len % k;
        for (int i = 0; i < k; i++, remain--) {
            int c = avg + (remain > 0 ? 1 : 0);
            if (c > 0) {
                ans[i] = head;
                ListNode prev = new ListNode(0, head);
                for (; c > 0 && prev.next != null; c--) {
                    prev = prev.next;
                }

                head = prev.next;
                prev.next = null;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().splitListToParts(ListNode.build(1, 2, 3), 5)));
        System.out.println(Arrays.toString(new Solution().splitListToParts(ListNode.build(1,2,3,4,5,6,7,8,9,10), 3)));
        System.out.println(Arrays.toString(new Solution().splitListToParts(ListNode.build(1,2,3,4,5,6,7,8,9), 3)));
        System.out.println(Arrays.toString(new Solution().splitListToParts(ListNode.build(1,2,3,4,5,6,7,8,9), 1)));
        System.out.println(Arrays.toString(new Solution().splitListToParts(null, 20)));
        System.out.println(Arrays.toString(new Solution().splitListToParts(ListNode.build(1), 20)));
    }

}
