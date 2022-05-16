package problem.p817linkedlistcomponents;

import common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 817. Linked List Components
 *
 * https://leetcode.cn/problems/linked-list-components/
 *
 * You are given the head of a linked list containing unique integer values and
 * an integer array nums that is a subset of the linked list values.
 *
 * Return the number of connected components in nums where two values are connected
 * if they appear consecutively in the linked list.
 */

public class Solution {

    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int v : nums) set.add(v);

        int ans = 0;
        for (ListNode curr = head; curr != null; curr = curr.next) {
            if (set.contains(curr.val) && (curr.next == null || !set.contains(curr.next.val))) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numComponents(ListNode.build(0,1,2,3), new int[]{0,1,3}) == 2;
        assert new Solution().numComponents(ListNode.build(0,1,2,3,4), new int[]{0,3,1,4}) == 2;
    }

}
