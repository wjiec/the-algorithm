package problem.p228summaryranges;

import common.ListNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 228. Summary Ranges
 *
 * https://leetcode-cn.com/problems/summary-ranges/
 *
 * You are given a sorted unique integer array nums.
 *
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
 * That is, each element of nums is covered by exactly one of the ranges,
 * and there is no integer x such that x is in one of the ranges but not in nums.
 *
 * Each range [a,b] in the list should be output as:
 *
 * "a->b" if a != b
 * "a" if a == b
 */

public class Solution {

    public List<String> summaryRanges(int[] nums) {
        int sz = nums.length;
        if (sz == 0) {
            return Collections.emptyList();
        } else if (sz == 1) {
            return List.of(String.valueOf(nums[0]));
        }

        List<String> summary = new LinkedList<>();
        int l = nums[0], r = nums[0];
        for (int i = 1; i < sz; i++) {
            if (nums[i] == r + 1) {
                r++;
            } else {
                if (l == r) {
                    summary.add(String.valueOf(r));
                } else {
                    summary.add(String.format("%d->%d", l, r));
                }

                l = r = nums[i];
            }
        }

        if (l == r) {
            summary.add(String.valueOf(r));
        } else {
            summary.add(String.format("%d->%d", l, r));
        }

        return summary;
    }

    public static void main(String[] args) {
        assert new Solution().summaryRanges(new int[]{0,1,2,4,5,7}).equals(List.of("0->2","4->5","7"));
        assert new Solution().summaryRanges(new int[]{0,2,3,4,6,8,9}).equals(List.of("0","2->4","6","8->9"));
    }

}
