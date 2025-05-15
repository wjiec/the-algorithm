package weekly.w447.C;

import common.Checker;
import common.PrettyPrinter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        // 找到拼接之后可以被 k 整除的排列的最小字典序
        //  - 最长的长度为 6 * 13 = 78 位
        int total = 0, n = nums.length;
        for (var v : nums) total += String.valueOf(v).length();

        var ans = dfs(nums, (1 << n) - 1, total, k, 0);
        PrettyPrinter.println(ans);
        return ans == null ? new int[0] : ans.stream().mapToInt(i -> i).toArray();
    }

    private final static BigInteger[] base = new BigInteger[78];
    static {
        base[0] = BigInteger.ONE;
        for (int i = 1; i < base.length; i++) {
            base[i] = base[i - 1].multiply(BigInteger.valueOf(10));
        }
    }

    private List<Integer> dfs(int[] nums, int mask, int shift, int k, int mod) {
        if (mask == 0) return mod == 0 ? Collections.emptyList() : null;

        List<Integer> ans = null;
        for (int i = 0; i < nums.length; i++) {
            if ((mask & (1 << i)) != 0) {
                int n = String.valueOf(nums[i]).length();
                int currMod = BigInteger.valueOf(nums[i]).multiply(base[shift - n]).mod(BigInteger.valueOf(k)).intValue();
                currMod = (currMod + mod) % k;
                var currAns = dfs(nums, mask ^ (1 << i), shift - n, k, currMod);
                if (currAns != null) {
                    currAns = new ArrayList<>(currAns);
                    currAns.add(0, nums[i]);
                    if (less(currAns, ans)) ans = currAns;
                }
            }
        }

        return ans;
    }

    private boolean less(List<Integer> curr, List<Integer> ans) {
        if (ans == null) return true;
        if (curr == null) return false;

        return build(curr).compareTo(build(ans)) < 0;
    }

    private String build(List<Integer> array) {
        StringBuilder sb = new StringBuilder();
        for (var v : array) sb.append(v);
        return sb.toString();
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().concatenatedDivisibility(new int[]{3,12,45}, 5), new int[]{3,12,45});
        assert Checker.check(new Solution().concatenatedDivisibility(new int[]{10,5}, 10), new int[]{5,10});
        assert Checker.check(new Solution().concatenatedDivisibility(new int[]{1,2,3}, 5), new int[]{});
    }

}
