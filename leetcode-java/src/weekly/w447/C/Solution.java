package weekly.w447.C;

import common.Checker;

import java.util.*;

/**
 * 3533. Concatenated Divisibility
 *
 * https://leetcode.cn/contest/weekly-contest-447/problems/concatenated-divisibility/
 *
 * You are given an array of positive integers nums and a positive integer k.
 *
 * A permutation of nums is said to form a divisible concatenation if, when you concatenate
 * the decimal representations of the numbers in the order specified by the permutation, the
 * resulting number is divisible by k.
 *
 * Return the lexicographically smallest permutation (when considered as a list of integers) that
 * forms a divisible concatenation. If no such permutation exists, return an empty list.
 */

public class Solution {

    // 1 <= nums.length <= 13
    // 1 <= nums[i] <= 1e5
    public int[] concatenatedDivisibility(int[] nums, int k) {
        Arrays.sort(nums);

        // 找到以十进制拼接之后可以被 k 整除的排列的最小字典序(在 nums 上的最小字典序)
        int n = nums.length;
        int[] pow10 = new int[n];
        for (int i = 0; i < n; i++) pow10[i] = base(nums[i]);

        List<Integer> ans = new ArrayList<>();
        if (dfs(nums, (1 << n) - 1, 0, k, ans, pow10)) {
            return ans.stream().mapToInt(i -> i).toArray();
        }
        return new int[0];
    }

    private final Set<Integer> seen = new HashSet<>();

    private boolean dfs(int[] nums, int mask, int mod, int k, List<Integer> ans, int[] pow10) {
        if (mask == 0) return mod == 0;
        if (!seen.add((mask << 8) | mod)) return false;

        for (int i = 0; i < nums.length; i++) {
            // 得到下一个数实际上就是 mod * 10 ^ len(nums[i]) + nums[i]
            int next = (mod * pow10[i] + nums[i]) % k;
            if ((mask & (1 << i)) != 0 && dfs(nums, mask ^ (1 << i), next, k, ans, pow10)) {
                //noinspection SequencedCollectionMethodCanBeUsed
                ans.add(0, nums[i]);
                return true;
            }
        }

        return false;
    }

    private int base(int v) {
        if (v < 10) return 10;
        else if (v < 100) return 100;
        else if (v < 1000) return 1000;
        else if (v < 10000) return 10000;
        else if (v < 100000) return 100000;
        else return 1000000;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().concatenatedDivisibility(new int[]{3,12,45}, 5), new int[]{3,12,45});
        assert Checker.check(new Solution().concatenatedDivisibility(new int[]{10,5}, 10), new int[]{5,10});
        assert Checker.check(new Solution().concatenatedDivisibility(new int[]{1,2,3}, 5), new int[]{});
    }

}
