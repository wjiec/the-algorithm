package problem.p368largestdivisiblesubset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 368. Largest Divisible Subset
 *
 * https://leetcode-cn.com/problems/largest-divisible-subset/
 *
 * Given a set of distinct positive integers nums, return the largest subset answer
 * such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
 *
 * answer[i] % answer[j] == 0, or
 * answer[j] % answer[i] == 0
 *
 * If there are multiple solutions, return any of them.
 */

public class Solution {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        int size = 1, value = 0;
        int[] dp = new int[nums.length]; dp[0] = 1;
        for (int i = 1, n = nums.length; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] > size) { size = dp[i]; value = nums[i]; }
        }

        if (size == 1) return List.of(nums[0]);
        List<Integer> ans = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0 && size > 0; i--) {
            if (value % nums[i] == 0 && dp[i] == size) {
                ans.add(nums[i]);
                value = nums[i];
                size--;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().largestDivisibleSubset(new int[]{1}));
        System.out.println(new Solution().largestDivisibleSubset(new int[]{4,8,10,240}));

        System.out.println(new Solution().largestDivisibleSubset(new int[]{1,2,3}));
        System.out.println(new Solution().largestDivisibleSubset(new int[]{1,2,4,8}));
    }

}
