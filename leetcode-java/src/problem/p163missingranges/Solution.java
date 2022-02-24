package problem.p163missingranges;

import java.util.ArrayList;
import java.util.List;

/**
 * 163. Missing Ranges
 *
 * https://leetcode-cn.com/problems/missing-ranges/
 *
 * You are given an inclusive range [lower, upper] and a sorted unique integer array nums,
 * where all elements are in the inclusive range.
 *
 * A number x is considered missing if x is in the range [lower, upper] and x is not in nums.
 *
 * Return the smallest sorted list of ranges that cover every missing number exactly.
 *
 * That is, no element of nums is in any of the ranges, and each missing number is in one of the ranges.
 */

public class Solution {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        int pre = lower - 1;
        List<String> ans = new ArrayList<>();
        for (int num : nums) {
            if (num - pre == 2) ans.add(String.valueOf(pre + 1));
            else if (num - pre > 2) ans.add(String.format("%d->%d", pre + 1, num - 1));
            pre = num;
        }
        if (upper - pre == 1) ans.add(String.valueOf(pre + 1));
        if (upper - pre > 1) ans.add(String.format("%d->%d", pre + 1, upper));
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99)
            .equals(List.of("2","4->49","51->74","76->99"));
        assert new Solution().findMissingRanges(new int[]{-1}, -1, -1)
            .equals(List.of());
    }

}
