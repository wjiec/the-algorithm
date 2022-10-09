package problem.p2058findtheminimumandmaximumnumberofnodesbetweencriticalpoints;

import common.Checker;
import common.ListNode;

/**
 * 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points
 *
 * https://leetcode.cn/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/
 *
 * A critical point in a linked list is defined as either a local maxima or a local minima.
 *
 * A node is a local maxima if the current node has a value strictly greater than the previous node and the next node.
 *
 * A node is a local minima if the current node has a value strictly smaller than the previous node and the next node.
 *
 * Note that a node can only be a local maxima/minima if there exists both a previous node and a next node.
 *
 * Given a linked list head, return an array of length 2 containing [minDistance, maxDistance] where minDistance is
 * the minimum distance between any two distinct critical points and maxDistance is the maximum distance between
 * any two distinct critical points. If there are fewer than two critical points, return [-1, -1].
 */

public class Solution {

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int prev = head.val, min = Integer.MAX_VALUE, max = -1, first = -1, idx = 1, prevIdx = -1;
        for (ListNode curr = head.next; curr.next != null; curr = curr.next) {
            int next = curr.next.val;
            if ((curr.val > prev && curr.val > next) || (curr.val < prev && curr.val < next)) {
                if (first == -1) first = idx;
                else max = Math.max(max, idx - first);
                if (prevIdx != -1) min = Math.min(min, idx - prevIdx);
                prevIdx = idx;
            }

            idx++;
            prev = curr.val;
        }
        return new int[]{min == Integer.MAX_VALUE ? -1 : min, max};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().nodesBetweenCriticalPoints(ListNode.build(3,1)), new int[]{-1, -1});
        assert Checker.check(new Solution().nodesBetweenCriticalPoints(ListNode.build(5,3,1,2,5,1,2)), new int[]{1,3});
        assert Checker.check(new Solution().nodesBetweenCriticalPoints(ListNode.build(1,3,2,2,3,2,2,2,7)), new int[]{3,3});
        assert Checker.check(new Solution().nodesBetweenCriticalPoints(ListNode.build(2,3,3,2)), new int[]{-1,-1});
    }

}
