package weekly.w360.C;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2835. Minimum Operations to Form Subsequence With Target Sum
 *
 * https://leetcode.cn/contest/weekly-contest-360/problems/minimum-operations-to-form-subsequence-with-target-sum/
 *
 * You are given a 0-indexed array nums consisting of non-negative powers of 2, and an integer target.
 *
 * In one operation, you must apply the following changes to the array:
 *
 * Choose any element of the array nums[i] such that nums[i] > 1.
 * Remove nums[i] from the array.
 * Add two occurrences of nums[i] / 2 to the end of nums.
 * Return the minimum number of operations you need to perform so that nums contains a subsequence
 * whose elements sum to target. If it is impossible to obtain such a subsequence, return -1.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements
 * without changing the order of the remaining elements.
 */

public class Solution {

    public int minOperations(List<Integer> nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 32; i++) map.put(1 << i, i);

        long sum = 0;
        for (var num : nums) sum += num;
        if (sum < target) return -1;

        var bits = new long[32];
        for (int x : nums) bits[map.get(x)]++;

        int ans = 0, i = 0; sum = 0;
        while ((1L << i) <= target) {
            sum += bits[i] << i;
            int mask = (int) ((1L << ++i) - 1);
            if (sum >= (target & mask)) continue;

            ans++; // 一定要找更大的数操作
            for (; bits[i] == 0; i++) ans++; // 还没找到，继续找更大的数
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(List.of(1, 2, 8), 7) == 1;
        assert new Solution().minOperations(List.of(1,32,1,2), 12) == 2;
        assert new Solution().minOperations(List.of(1,32,1), 35) == -1;
    }

}
