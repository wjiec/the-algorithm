package weekly.w352.C;

import java.util.TreeMap;

/**
 * 6911. Continuous Subarrays
 *
 * https://leetcode.cn/contest/weekly-contest-352/problems/continuous-subarrays/
 *
 * You are given a 0-indexed integer array nums. A subarray of nums is called continuous if:
 *
 * Let i, i + 1, ..., j be the indices in the subarray. Then, for each pair of
 * indices i <= i1, i2 <= j, 0 <= |nums[i1] - nums[i2]| <= 2.
 * Return the total number of continuous subarrays.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public long continuousSubarrays(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        long ans = 0, n = nums.length;
        for (int l = 0, r = 0; r < n; r++) {
            map.merge(nums[r], 1, Integer::sum);
            while (map.lastKey() - map.firstKey() > 2) {
                map.merge(nums[l++], -1, (a, b) -> a + b == 0 ? null : a + b);
            }
            ans += r - l + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
