package problem.p1262greatestsumdivisiblebythree;

import java.util.Arrays;

/**
 * 1262. Greatest Sum Divisible by Three
 *
 * https://leetcode.cn/problems/greatest-sum-divisible-by-three/
 *
 * Given an integer array nums, return the maximum possible sum of elements of
 * the array such that it is divisible by three.
 */

public class Solution {

    public int maxSumDivThree(int[] nums) {
        int[] mod1 = new int[nums.length];
        int[] mod2 = new int[nums.length];

        Arrays.sort(nums);
        int ans = 0, a = 0, b = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            switch (nums[i] % 3) {
                case 0 -> ans += nums[i];
                case 1 -> mod1[a++] = nums[i];
                case 2 -> mod2[b++] = nums[i];
            }
        }

        int[] dp = new int[Math.max(a, b) + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1];
            int sum = mod1[i - 1] + mod2[i - 1];
            if (sum % 3 == 0) dp[i] += sum;

            if (i > 2) {
                dp[i] = Math.max(dp[i], dp[i - 3] + Math.max(
                    mod1[i - 1] + mod1[i - 2] + mod1[i - 3],
                    mod2[i - 1] + mod2[i - 2] + mod2[i - 3]
                ));
            }
        }

        return ans + dp[dp.length - 1];
    }

    private static class Optimization {
        public int maxSumDivThree(int[] nums) {
            int[] mods = new int[3];
            for (var num : nums) {
                int a = mods[0] + num, b = mods[1] + num, c = mods[2] + num;
                mods[a % 3] = Math.max(mods[a % 3], a);
                mods[b % 3] = Math.max(mods[b % 3], b);
                mods[c % 3] = Math.max(mods[c % 3], c);
            }
            return mods[0];
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxSumDivThree(new int[]{3,6,5,1,8}) == 18;
        assert new Solution().maxSumDivThree(new int[]{4}) == 0;
        assert new Solution().maxSumDivThree(new int[]{1,2,3,4,4}) == 12;

        assert new Optimization().maxSumDivThree(new int[]{3,6,5,1,8}) == 18;
        assert new Optimization().maxSumDivThree(new int[]{4}) == 0;
        assert new Optimization().maxSumDivThree(new int[]{1,2,3,4,4}) == 12;
    }

}
