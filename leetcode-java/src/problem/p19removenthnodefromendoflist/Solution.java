package problem.p19removenthnodefromendoflist;

import common.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 19. Remove Nth Node From End of List
 *
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 */

public class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = head, slow = dummy;
        while (n-- > 0) fast = fast.next;
        for (; fast != null; fast = fast.next) slow = slow.next;
        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeNthFromEnd(ListNode.build(1,2,3,4,5), 2));
        System.out.println(new Solution().removeNthFromEnd(ListNode.build(1), 1));
        System.out.println(new Solution().removeNthFromEnd(ListNode.build(1,2), 1));
    }

}
