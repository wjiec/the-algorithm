package problem.p632smallestrangecoveringelementsfromklists;

import common.Checker;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 632. Smallest Range Covering Elements from K Lists
 *
 * https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists/
 *
 * You have k lists of sorted integers in non-decreasing order. Find the smallest
 * range that includes at least one number from each of the k lists.
 *
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 */

public class Solution {

    public int[] smallestRange(List<List<Integer>> nums) {
        int max = Integer.MIN_VALUE;
        int[] next = new int[nums.size()];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing(i -> nums.get(i).get(next[i])));
        for (int i = 0; i < nums.size(); i++) {
            pq.add(i);
            max = Math.max(max, nums.get(i).get(0));
        }

        int l = 0, r = Integer.MAX_VALUE, minRange = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            int idx = pq.remove();
            int currRange = max - nums.get(idx).get(next[idx]);
            if (currRange < minRange) {
                minRange = currRange;
                l = nums.get(idx).get(next[idx]);
                r = max;
            }

            next[idx]++;
            if (next[idx] == nums.get(idx).size()) break;

            pq.add(idx);
            max = Math.max(max, nums.get(idx).get(next[idx]));
        }
        return new int[]{l, r};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().smallestRange(List.of(
            List.of(4,10,15,24,26),
            List.of(0,9,12,20),
            List.of(5,18,22,30)
        )), new int[]{20, 24});

        assert Checker.check(new Solution().smallestRange(List.of(
            List.of(1,2,3),
            List.of(1,2,3),
            List.of(1,2,3)
        )), new int[]{1, 1});
    }

}
