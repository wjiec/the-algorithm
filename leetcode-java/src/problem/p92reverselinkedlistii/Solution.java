package problem.p92reverselinkedlistii;

import common.ListNode;

import java.util.Stack;

/**
 * 92. Reverse Linked List II
 *
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 *
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 */

public class Solution {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right || head.next == null) return head;

        ListNode dummy = new ListNode(0, head), curr = dummy;
        for (int i = 1; i < left; i++) curr = curr.next;
        curr.next = reverse(curr.next, right - left + 1);

        return dummy.next;
    }

    private ListNode reverse(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        for (; n-- > 0; head = head.next) stack.push(head);

        ListNode reversed = stack.pop(), curr = reversed;
        while (!stack.empty()) { curr.next = stack.pop(); curr = curr.next; }
        curr.next = head;

        return reversed;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverseBetween(ListNode.build(1,2,3,4,5), 2, 4));
        System.out.println(new Solution().reverseBetween(ListNode.build(5), 1, 1));

        System.out.println(new Solution().reverseBetween(ListNode.build(1,2,3,4,5), 2, 5));
        System.out.println(new Solution().reverseBetween(ListNode.build(1,2,3,4,5), 1, 5));
        System.out.println(new Solution().reverseBetween(ListNode.build(1,2,3,4,5), 2, 3));
    }

}
