package problem.p160intersectionoftwolinkedlists;

import common.ListNode;

/**
 * 160. Intersection of Two Linked Lists
 *
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 *
 * For example, the following two linked lists begin to intersect at node c1:
 *
 * It is guaranteed that there are no cycles anywhere in the entire linked structure.
 *
 * Note that the linked lists must retain their original structure after the function returns.
 */

public class Solution {

    // broken
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            if (a != null) {
                a = a.next;
            } else {
                a = headB;
            }

            if (b != null) {
                b = b.next;
            } else {
                b = headA;
            }
        }
        return a;
    }

    // broken
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        for (ListNode a = headA; a != null; a = a.next) {
            a.val = -a.val;
        }

        for (ListNode b = headB; b != null; b = b.next) {
            if (b.val < 0) {
                b.val = -b.val;
                return b;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        assert new Solution().getIntersectionNode(ListNode.build(1,2,3,4,5,6,1), ListNode.build(4,5,6)) == null;
    }

}
