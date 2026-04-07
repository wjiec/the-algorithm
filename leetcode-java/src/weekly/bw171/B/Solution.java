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

    private static class Optimization {
        public int[] minOperations(int[] nums) {
            // 计算一个数的左半边 l, 那么根据回文, 右半边也是相同的
            //  - 我们直接枚举从 0 开始到左半边长度的二进制数
            //  - 离 number 最近的肯定是 l, l + 1, l - 1 的位置
            //      - 如果左半边是 l + 2 的话, 肯定是比 l + 1 大的
            //      - 如果左半边是 l - 2 的话, 右半边可能会取反之后再比原来的大
            //
            // 对于一个数 v, 它的二进制长度是 l, 我们只需要取它的左半边, 拼接到右半边上即可
            //  - 长度为偶数, 则左半边就是 v >> (l >> 1)
            //  - 长度是奇数, 则左半边就是 v >> ((l >> 1) + 1)
            //
            // 整合一下就是 v >> ((l + 1) >> 1)
            int[] ans = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                int v = nums[i], n = 32 - Integer.numberOfLeadingZeros(v);
                if (n == 1) { ans[i] = 0; continue; }

                int m = n >> 1, l1 = v >> m, l2 = l1 + 1, l3 = l1 - 1;
                int v1 = (l1 << m) | Integer.reverse(l1 >> (n % 2)) >>> (32 - m);
                int v2 = (l2 << m) | Integer.reverse(l2 >> (n % 2)) >>> (32 - m);
                int v3 = (l3 << m) | Integer.reverse(l3 >> (n % 2)) >>> (32 - m);
                ans[i] = Math.min(Math.min(Math.abs(v - v1), Math.abs(v2 - v)), Math.abs(v3 - v));
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Optimization().minOperations(new int[]{1,2,4}), new int[]{0,1,1});
        assert Checker.check(new Optimization().minOperations(new int[]{3664}), new int[]{23});
        assert Checker.check(new Optimization().minOperations(new int[]{3521}), new int[]{38});

        assert Checker.check(new Solution().minOperations(new int[]{3664}), new int[]{23});
        assert Checker.check(new Solution().minOperations(new int[]{3521}), new int[]{38});
    }

}
