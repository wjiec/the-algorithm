package problem.p454a4sumii;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 4Sum II
 *
 * https://leetcode-cn.com/problems/4sum-ii/
 *
 * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n,
 * return the number of tuples (i, j, k, l) such that:
 *
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 */

public class Solution {

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var a : nums1) {
            for (var b : nums2) {
                map.merge(a + b, 1, Integer::sum);
            }
        }

        int ans = 0;
        for (var a : nums3) {
            for (var b : nums4) {
                ans += map.getOrDefault(-a - b, 0);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().fourSumCount(new int[]{1,2}, new int[]{-2,-1}, new int[]{-1, 2}, new int[]{0, 2}) == 2;
        assert new Solution().fourSumCount(new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0}) == 1;
    }

}
