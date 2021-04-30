package problem.p21mergetwosortedlists;

/**
 * 21. Merge Two Sorted Lists
 *
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * Merge two sorted linked lists and return it as a sorted list.
 * The list should be made by splicing together the nodes of the first two lists.
 */

public class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(), curr = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        curr.next = l1 == null ? l2 : l1;
        return head.next;
    }

    public static void main(String[] args) {
        assert check(new Solution().mergeTwoLists(build(1,3,5), build(2,4,6)), 1,2,3,4,5,6);
        assert check(new Solution().mergeTwoLists(build(), build(2,4,6)), 2,4,6);
        assert check(new Solution().mergeTwoLists(null, build(2,4,6)), 2,4,6);
        assert check(new Solution().mergeTwoLists(null, null));
        assert check(new Solution().mergeTwoLists(null, build(0)), 0);
        assert check(new Solution().mergeTwoLists(build(1,2,4), build(1,3,4)), 1,1,2,3,4);
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

        return list == null;
    }

}
