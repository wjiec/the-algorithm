package weekly.w454.B;

import java.util.HashMap;
import java.util.Map;

/**
 * Q2. Count Special Triplets
 *
 * https://leetcode.cn/contest/weekly-contest-454/problems/count-special-triplets/
 *
 * You are given an integer array nums.
 *
 * A special triplet is defined as a triplet of indices (i, j, k) such that:
 *
 * 0 <= i < j < k < n, where n = nums.length
 * nums[i] == nums[j] * 2
 * nums[k] == nums[j] * 2
 * Return the total number of special triplets in the array.
 *
 * Since the answer may be large, return it modulo 1e9 + 7.
 */

public class Solution {

    // i < j < k, 同时 nums[i] = nums[k] = nums[j] * 2
    public int specialTriplets(int[] nums) {
        Map<Integer, Long> suffix = new HashMap<>();
        for (var v : nums) suffix.merge(v, 1L, Long::sum);

        long ans = 0;
        Map<Integer, Long> prefix = new HashMap<>();
        for (int jv : nums) {
            suffix.merge(jv, -1L, Long::sum);
            ans = (ans + prefix.getOrDefault(jv << 1, 0L) * suffix.getOrDefault(jv << 1, 0L)) % 1_000_000_007;
            prefix.merge(jv, 1L, Long::sum);
        }
        return (int) ans;
    }

    public static void main(String[] args) {
    }

}
