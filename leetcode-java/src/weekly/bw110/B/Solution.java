package weekly.bw110.B;

import common.ListNode;

/**
 * 2807. Insert Greatest Common Divisors in Linked List
 *
 * https://leetcode.cn/contest/biweekly-contest-110/problems/insert-greatest-common-divisors-in-linked-list/
 *
 * Given the head of a linked list head, in which each node contains an integer value.
 *
 * Between every pair of adjacent nodes, insert a new node with a value equal to the greatest common divisor of them.
 *
 * Return the linked list after insertion.
 *
 * The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.
 */

public class Solution {

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode prev = head, curr = head.next;
        while (curr != null) {
            prev.next = new ListNode(gcd(prev.val, curr.val));
            prev.next.next = curr;

            prev = curr;
            curr = curr.next;
        }
        return head;
    }

    private int gcd(int a, int b) { return a % b == 0 ? b : gcd(b, a % b); }

    public static void main(String[] args) {
    }

}
