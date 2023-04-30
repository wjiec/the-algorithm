package problem.p369plusonelinkedlist;

/**
 * 369. Plus One Linked List
 *
 * https://leetcode.cn/problems/plus-one-linked-list/
 *
 * Given a non-negative integer represented as a linked list of digits, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 */

import common.ListNode;
import common.PrettyPrinter;

public class Solution {

    public ListNode plusOne(ListNode head) {
        head = reverse(head);

        int carry = 1;
        for (ListNode curr = head; curr != null; curr = curr.next) {
            curr.val += carry; carry = curr.val / 10; curr.val %= 10;
        }
        head = reverse(head);

        if (carry != 0) head = new ListNode(carry, head);
        return head;
    }

    private ListNode reverse(ListNode head) {
        ListNode tail = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = tail;
            tail = curr;
            curr = next;
        }
        return tail;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().plusOne(ListNode.build(1,2,3)));
        PrettyPrinter.println(new Solution().plusOne(ListNode.build(1,9,9)));
        PrettyPrinter.println(new Solution().plusOne(ListNode.build(9,9,9)));
    }

}
