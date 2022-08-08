package problem.p1438longestcontinuoussubarraywithabsolutedifflessthanorequaltolimit;

import java.util.TreeMap;

/**
 * 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 *
 * https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 *
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray
 * such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 */

public class Solution {

    public int longestSubarray(int[] nums, int limit) {
        int ans = 0, n = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int l = 0, r = 0; r < n; r++) {
            map.merge(nums[r], 1, Integer::sum);
            while (map.lastKey() - map.firstKey() > limit) {
                map.merge(nums[l++], 1, (ov, nv) -> ov == 1 ? null : ov - nv);
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestSubarray(new int[]{8,2,4,7}, 4) == 2;
        assert new Solution().longestSubarray(new int[]{10,1,2,4,7,2}, 5) == 4;
        assert new Solution().longestSubarray(new int[]{4,2,2,2,4,4,2,2}, 0) == 3;
    }

}
