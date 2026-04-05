package weekly.bw171.B;

import common.Checker;

import java.util.Arrays;

/**
 * Q2. Minimum Operations to Make Binary Palindrome
 *
 * https://leetcode.cn/contest/biweekly-contest-171/problems/minimum-operations-to-make-binary-palindrome/
 *
 * You are given an integer array nums.
 *
 * For each element nums[i], you may perform the following operations any number of times (including zero):
 *
 * Increase nums[i] by 1, or
 * Decrease nums[i] by 1.
 *
 * A number is called a binary palindrome if its binary representation
 * without leading zeros reads the same forward and backward.
 *
 * Your task is to return an integer array ans, where ans[i] represents
 * the minimum number of operations required to convert nums[i] into a binary palindrome.
 */

public class Solution {

    // 数字范围只从 [1, 5000] 我们直接枚举 [1, 5000] 内所有满足回文的数字, 然后计算每个数距离最近的即可
    private static final int MAX_N = 5001;
    private static final int[] nearest = new int[MAX_N];
    static {
        for (int i = 1, prev = 0; i < MAX_N; i++) {
            char[] chars = Integer.toBinaryString(i).toCharArray();
            for (int l = 0, r = chars.length - 1; l < r; l++, r--) {
                if (chars[l] != chars[r]) {
                    nearest[i] = i - prev;
                    break;
                }
            }
            if (nearest[i] == 0) prev = i;
        }

        // 从右边往回找一遍
        for (int i = MAX_N - 1, prev = Integer.MAX_VALUE; i >= 0; i--) {
            if (nearest[i] == 0) prev = i;
            else nearest[i] = Math.min(nearest[i], prev - i);
        }
    }

    public int[] minOperations(int[] nums) {
        return Arrays.stream(nums).map(v -> nearest[v]).toArray();
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().minOperations(new int[]{3664}), new int[]{23});

        assert Checker.check(new Solution().minOperations(new int[]{3521}), new int[]{38});
    }

}
