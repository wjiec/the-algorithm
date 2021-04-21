package problem.p2addtwonumbers;

/**
 * 2. Add Two Numbers
 *
 * https://leetcode-cn.com/problems/add-two-numbers/
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode((l1.val + l2.val) % 10);

        ListNode curr = result;
        int carry = (l1.val + l2.val) / 10;
        for (ListNode n1 = l1.next, n2 = l2.next; n1 != null || n2 != null;) {
            int v = (n1 != null ? n1.val : 0) + (n2 != null ? n2.val : 0) + carry;

            curr.next = new ListNode(v % 10);
            carry = v / 10;

            curr = curr.next;
            n1 = n1 != null ? n1.next : null;
            n2 = n2 != null ? n2.next : null;
        }

        if (carry != 0) {
            curr.next = new ListNode(carry);
        }

        return result;
    }

    public static void main(String[] args) {
        assert check(build(1, 2, 3), 1, 2, 3);
        assert check(new Solution().addTwoNumbers(build(1, 2, 3), build(1, 2, 3)), 2, 4, 6);
        assert check(new Solution().addTwoNumbers(build(0), build(0)), 0);
        assert check(new Solution().addTwoNumbers(build(0), build(1)), 1);
        assert check(new Solution().addTwoNumbers(build(1, 0), build(1)), 2, 0);
        assert check(new Solution().addTwoNumbers(build(1, 1), build(9, 8)), 0, 0, 1);
        assert check(new Solution().addTwoNumbers(build(2, 4, 3), build(5, 6, 4)), 7, 0, 8);
    }

    private static ListNode build(int ...numbers) {
        ListNode list = new ListNode();
        ListNode curr = list;
        for (var n : numbers) {
            curr.next = new ListNode(n);
            curr = curr.next;
        }

        return list.next;
    }

    private static boolean check(ListNode list, int ...numbers) {
        for (var n : numbers) {
            if (list != null && list.val == n) {
                list = list.next;
            } else {
                return false;
            }
        }

        return true;
    }

}