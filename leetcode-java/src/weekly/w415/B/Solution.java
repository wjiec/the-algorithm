package weekly.w415.B;

import java.util.HashMap;
import java.util.Map;

/**
 * 3290. Maximum Multiplication Score
 *
 * https://leetcode.cn/contest/weekly-contest-415/problems/maximum-multiplication-score/
 *
 * You are given an integer array a of size 4 and another integer array b of size at least 4.
 *
 * You need to choose 4 indices i0, i1, i2, and i3 from the array b such that i0 < i1 < i2 < i3.
 *
 * Your score will be equal to the value a[0] * b[i0] + a[1] * b[i1] + a[2] * b[i2] + a[3] * b[i3].
 *
 * Return the maximum score you can achieve.
 */

public class Solution {

    public long maxScore(int[] a, int[] b) {
        long[][] values = new long[4][b.length];
        for (int i = 0; i < b.length; i++) {
            values[0][i] = (long) a[0] * b[i];
            values[1][i] = (long) a[1] * b[i];
            values[2][i] = (long) a[2] * b[i];
            values[3][i] = (long) a[3] * b[i];
        }

        // 从 values 的每一行中取出一个数相加, 要求所取的值最大
        return maxScore(values, 0, 0, b.length);
    }

    private final Map<Long, Long> memo = new HashMap<>();

    // 从第 i 行以及之后行的 [l, r] 中所能取到最大数值
    private long maxScore(long[][] values, int i, int l, int r) {
        if (i == values.length) return 0;

        long key = ((long) i << 48) | ((long) l << 24) | r;
        if (memo.containsKey(key)) return memo.get(key);

        long ans = Long.MIN_VALUE, mx = Long.MIN_VALUE;
        for (int j = l; j <= r - (4 - i); j++) {
            // 如果值不变大, 后面做的基本都是重复计算, 直接减枝
            if (values[i][j] > mx) {
                mx = values[i][j];
                ans = Math.max(ans, mx + maxScore(values, i + 1, j + 1, r));
            }
        }

        memo.put(key, ans);
        return ans;
    }

    private static class DFS {
        public long maxScore(int[] a, int[] b) {
            return dfs(b, b.length - 1, a, 3);
        }

        private final Map<Integer, Long> memo = new HashMap<>();

        // 从 b 数组的 [0, i] 中选出 j 个数与 a 数组中的 [0, j] 相乘的最大值
        private long dfs(int[] b, int i, int[] a, int j) {
            // 此时所有的 j 已经选完了
            if (j < 0) return 0;
            // 此时 j >= 0, 但是在 b 中已经没有配对的数了, 非法
            if (i < j) return Long.MIN_VALUE;

            int key = (i << 4) | j;
            if (memo.containsKey(key)) return memo.get(key);

            long ans = Math.max(
                dfs(b, i - 1, a, j), // 不选择当前的数 b[i]
                dfs(b, i - 1, a, j - 1) + (long) b[i] * a[j] // 选择当前的 b[i] 与 a[j] 相乘
            );

            memo.put(key, ans);
            return ans;
        }
    }

    private static class DP {
        public long maxScore(int[] a, int[] b) {
            int m = a.length, n = b.length;
            // dp[i][j] 表示从 a 中选择前 i 个数与从 b 中选择前 j 个数的最大得分
            long[][] dp = new long[m + 1][n + 1];

            // 枚举从 a 中选择前 i 个数
            for (int i = 1; i <= m; i++) {
                // 对于所有在 b 中选择出不超过 i 个数的方案都是非法的
                for (int j = 0; j < i; j++) dp[i][j] = Long.MIN_VALUE;
                // 我们最小的选择方式是从 b 中选择出至少 i 个数
                for (int j = i; j <= n; j++) {
                    dp[i][j] = Math.max(
                        // 从 b 的前 j - 1 个数中选出 i 个数
                        dp[i][j - 1],
                        // 从 b 的前 j - 1 个数中选出 i 个数与 a 中的前 i - 1 个
                        // 数匹配, 让当前的 a 中的第 i 个数与 b 中的第 j 个数匹配
                        dp[i - 1][j - 1] + (long) a[i - 1] * b[j - 1]
                    );
                }
            }

            return dp[a.length][b.length];
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxScore(new int[]{3,2,5,6}, new int[]{2,-6,4,-5,-3,2,-7}) == 26;
        assert new Solution().maxScore(new int[]{-1,4,5,-2}, new int[]{-5,-1,-3,-2,-4}) == -1;

        assert new DFS().maxScore(new int[]{3,2,5,6}, new int[]{2,-6,4,-5,-3,2,-7}) == 26;
        assert new DFS().maxScore(new int[]{-1,4,5,-2}, new int[]{-5,-1,-3,-2,-4}) == -1;

        assert new DP().maxScore(new int[]{3,2,5,6}, new int[]{2,-6,4,-5,-3,2,-7}) == 26;
        assert new DP().maxScore(new int[]{-1,4,5,-2}, new int[]{-5,-1,-3,-2,-4}) == -1;
    }

}
