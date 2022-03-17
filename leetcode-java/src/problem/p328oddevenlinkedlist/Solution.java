package problem.p328oddevenlinkedlist;

import common.Checker;
import common.ListNode;

/**
 * 328. Odd Even Linked List
 *
 * https://leetcode-cn.com/problems/odd-even-linked-list/
 *
 * Given the head of a singly linked list, group all the nodes with odd indices
 * together followed by the nodes with even indices, and return the reordered list.
 *
 * The first node is considered odd, and the second node is even, and so on.
 *
 * Note that the relative order inside both the even and odd groups
 * should remain as it was in the input.
 *
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 */

public class Solution {

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode even = head.next, odd = head; head.next = null;
        for (ListNode curr = even; curr != null && curr.next != null;) {
            odd.next = curr.next; odd = odd.next;
            curr.next = odd.next; curr = curr.next;
        }

        odd.next = even;
        return head;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().oddEvenList(ListNode.build(1,2,3,4,5)),
            ListNode.build(1,3,5,2,4));
        assert Checker.check(new Solution().oddEvenList(ListNode.build(2,1,3,5,6,4,7)),
            ListNode.build(2,3,6,7,1,5,4));

        assert Checker.check(new Solution().oddEvenList(ListNode.build(1,2,3,4,5,6)),
            ListNode.build(1,3,5,2,4,6));
    }

}
