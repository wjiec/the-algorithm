package problem.p206reverselinkedlist;

import common.Checker;
import common.ListNode;

import java.util.Stack;

/**
 * 206. Reverse Linked List
 *
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */

public class Solution {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode reverseListRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return curr;
    }

    public ListNode reverseListStack(ListNode head) {
        if (head == null) {
            return null;
        }

        Stack<ListNode> nodes = new Stack<>();
        for (; head != null; head = head.next) {
            nodes.add(head);
        }

        head = nodes.pop();
        for (ListNode curr = head; !nodes.empty(); curr = curr.next) {
            curr.next = nodes.pop();
            curr.next.next = null;
        }
        return head;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().reverseList(ListNode.build(1,2)), 2,1);
        assert Checker.check(new Solution().reverseList(ListNode.build(1,2,3,4,5)), 5,4,3,2,1);
    }

}
