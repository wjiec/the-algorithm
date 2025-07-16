package weekly.w454.C;

import java.util.TreeSet;

/**
 * Q3. Maximum Product of First and Last Elements of a Subsequence
 *
 * https://leetcode.cn/contest/weekly-contest-454/problems/maximum-product-of-first-and-last-elements-of-a-subsequence/
 *
 * You are given an integer array nums and an integer m.
 *
 * Return the maximum product of the first and last elements of any subsequence of nums of size m.
 */

public class Solution {

    // 子序列首尾元素相乘的最大值
    public long maximumProduct(int[] nums, int m) {
        // 对于位置 i, 在 [0, i - m + 1] 的范围内找到一个数, 使得与 nums[i] 相乘的结果最大
        //  - 如果 nums[i] > 0, 则需要找最大的正数(包括 0)和最小的负数
        //  - 如果 nums[i] < 0, 则需要找最小的负数(包括 0)和最小的正数
        //  - 如果 nums[i] = 0, 则直接取 0
        long ans = Long.MIN_VALUE;
        TreeSet<Long> positive = new TreeSet<>();
        TreeSet<Long> negative = new TreeSet<>();
        for (int i = 0, j = 1 - m; i < nums.length; i++, j++) {
            if (j >= 0) {
                if (nums[j] > 0) positive.add((long) nums[j]);
                else if (nums[j] < 0) negative.add((long) nums[j]);
                else { positive.add(0L); negative.add(0L); }
            }

            if (nums[i] > 0) {
                if (!positive.isEmpty()) ans = Math.max(ans, positive.last() * nums[i]);
                else if (!negative.isEmpty()) ans = Math.max(ans, negative.last() * nums[i]);
            } else if (nums[i] < 0) {
                if (!negative.isEmpty()) ans = Math.max(ans, negative.first() * nums[i]);
                else if (!positive.isEmpty()) ans = Math.max(ans, positive.first() * nums[i]);
            } else if (!positive.isEmpty() || !negative.isEmpty()) ans = Math.max(ans, 0);
        }

        return ans;
    }

    private static class Optimization {
        public long maximumProduct(int[] nums, int m) {
            // 枚举右, 维护左
            long ans = Long.MIN_VALUE, mi = Long.MAX_VALUE, mx = Long.MIN_VALUE;
            for (int i = m - 1; i < nums.length; i++) {
                mi = Math.min(mi, nums[i - m + 1]);
                mx = Math.max(mx, nums[i - m + 1]);
                ans = Math.max(ans, Math.max(nums[i] * mi, nums[i] * mx));
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().maximumProduct(new int[]{-9,-9,-1,2,-8,10,3,10,0}, 7) == 0;
        assert new Solution().maximumProduct(new int[]{-5,9,4,0,10,-8,-3,9}, 8) == -45;
        assert new Solution().maximumProduct(new int[]{-1,-9,2,3,-2,-3,1}, 1) == 81;
        assert new Solution().maximumProduct(new int[]{1,3,-5,5,6,-4}, 3) == 20;
        assert new Solution().maximumProduct(new int[]{2,-1,2,-6,5,2,-5,7}, 2) == 35;
    }

}
