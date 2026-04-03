package weekly.w478.C;

import java.util.HashMap;
import java.util.Map;

/**
 * Q3. Minimum Absolute Distance Between Mirror Pairs
 *
 * https://leetcode.cn/contest/weekly-contest-478/problems/minimum-absolute-distance-between-mirror-pairs/
 *
 * You are given an integer array nums.
 *
 * A mirror pair is a pair of indices (i, j) such that:
 *
 * 0 <= i < j < nums.length, and
 * reverse(nums[i]) == nums[j], where reverse(x) denotes the integer formed by reversing the digits of x.
 * Leading zeros are omitted after reversing, for example reverse(120) = 21.
 *
 * Return the minimum absolute distance between the indices of any mirror pair.
 * The absolute distance between indices i and j is abs(i - j).
 *
 * If no mirror pair exists, return -1.
 */

public class Solution {

    public int minMirrorPairDistance(int[] nums) {
        int ans = Integer.MAX_VALUE;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            if (m.containsKey(v)) ans = Math.min(ans, i - m.get(v));
            m.put(reverse(v), i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int reverse(int v) {
        int ans = 0;
        for (; v != 0; v /= 10) ans = ans * 10 + v % 10;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minMirrorPairDistance(new int[]{12,34,46,21,12}) == 1;
    }

}
