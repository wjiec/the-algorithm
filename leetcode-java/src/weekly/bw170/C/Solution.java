package weekly.bw170.C;

import common.PrettyPrinter;

/**
 * Q3. Lexicographically Smallest Negated Permutation that Sums to Target
 *
 * https://leetcode.cn/contest/biweekly-contest-170/problems/lexicographically-smallest-negated-permutation-that-sums-to-target/
 *
 * You are given a positive integer n and an integer target.
 *
 * Return the lexicographically smallest array of integers of size n such that:
 *
 * The sum of its elements equals target.
 * The absolute values of its elements form a permutation of size n.
 * If no such array exists, return an empty array.
 *
 * A permutation of size n is a rearrangement of integers 1, 2, ..., n.
 */

public class Solution {

    public int[] lexSmallestNegatedPerm(int n, long target) {
        long sum = (n + 1L) * n / 2;
        // 只能使用 [1, n] 的所有数字(正负), 找到和为 target 且字典序 最小的排列
        //  - [1, n] 之和为 s = n * (n + 1) / 2, 需要满足 s >= target
        //
        // 当我们将其中一个数 x 转换为负数, 对于 s 的影响为 s' = s - 2 * x
        //  - 当我们删除 x, y, z, ... 使得 s - 2x - 2y - ... - 2z == target
        //  - 也就是 s - target == 2 * (x + y + ... + z)
        //
        // 可以总结出以下特点
        //  - s - target 需要是一个偶数
        //  - 找到最大的 x + y + z + ... == s - target 的组合
        if (sum < Math.abs(target) || (sum - target) % 2 != 0) return new int[0];

        // 接下来就是找到一些数, 使得 x + y + z + ... + == s - target
        long remain = (sum - target) / 2; int[] ans = new int[n];
        // 为了使字典序最小, 我们直接在 [1, n] 里找 max([1, n], remain) 并将其变成负数
        for (int v = n, i = 0; remain > 0; remain += ans[i - 1]) {
            ans[i++] = (int) -Math.min(v--, remain);
        }

        // 接下来就是将剩余的数字填进去, 我们是按照从大到小用负数填进去的, 所以可以用双指针
        for (int l = 0, r = n - 1; n != 0; n--) {
            if (-n == ans[l]) { l++; continue; }
            ans[r--] = n;
        }

        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().lexSmallestNegatedPerm(4, 2));
        PrettyPrinter.println(new Solution().lexSmallestNegatedPerm(4, -2));

        PrettyPrinter.println(new Solution().lexSmallestNegatedPerm(3, 0));
        PrettyPrinter.println(new Solution().lexSmallestNegatedPerm(1, 10000000000L));
    }

}
