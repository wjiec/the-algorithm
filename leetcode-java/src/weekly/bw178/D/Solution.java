package weekly.bw178.D;

import java.util.Arrays;

/**
 * Q4. Count Fancy Numbers in a Range
 *
 * https://leetcode.cn/contest/biweekly-contest-178/problems/count-fancy-numbers-in-a-range/
 *
 * You are given two integers l and r.
 *
 * An integer is called good if its digits form a strictly monotone sequence, meaning
 * the digits are strictly increasing or strictly decreasing.
 *
 * All single-digit integers are considered good.
 *
 * An integer is called fancy if it is good, or if the sum of its digits is good.
 *
 * Return an integer representing the number of fancy integers in the range [l, r] (inclusive).
 *
 * A sequence is said to be strictly increasing if each element is strictly greater than its previous one (if exists).
 *
 * A sequence is said to be strictly decreasing if each element is strictly less than its previous one (if exists).
 */

public class Solution {

    private static final int MAX_W = 16;
    private static final int MAX_N = MAX_W * 9 + 1;
    private static final boolean[] isGood = new boolean[MAX_N];
    // 也就是最多 14 个 9 的数量, 枚举所有的数位和, 检查是否是好数
    static {
        for (int i = 0; i < MAX_N; i++) {
            int a = i / 100, b = (i / 10) % 10, c = i % 10;
            if (i < 10) isGood[i] = true;
            else if (i < 100) isGood[i] = b != c;
            else isGood[i] = a < b && b < c;
        }
    }

    // 1 <= l <= r <= 1e15
    public long countFancy(long l, long r) {
        return countFancy(r) - countFancy(l - 1);
    }

    private long countFancy(long v) {
        if (v <= 10) return v;
        // 分别计算递增和递减的好数
        int[] digits = new int[String.valueOf(v).length()];
        for (long x = v, i = digits.length - 1; x != 0; x /= 10) {
            digits[(int) i--] = (int) (x % 10);
        }

        resetMemo();
        return dfs(digits, 0, true, true, -1, 0, true, false);
    }

    private final long[][][][][] memo = new long[MAX_W][2][2][10][MAX_N];
    private void resetMemo() {
        for (var a : memo) for (var b : a) for (var c : b) for (var d : c) Arrays.fill(d, -1);
    }

    private long dfs(int[] digits, int i, boolean incGood, boolean decGood, int pre, int sum, boolean limited, boolean valid) {
        if (i >= digits.length) return valid && (incGood || decGood || isGood[sum]) ? 1 : 0;
        if (!limited && valid && memo[i][incGood ? 1 : 0][decGood ? 1 : 0][pre][sum] != -1) {
            return memo[i][incGood ? 1 : 0][decGood ? 1 : 0][pre][sum];
        }

        long ans = valid ? 0 : dfs(digits, i + 1, incGood, decGood, pre, sum, false, false);
        for (int j = valid ? 0 : 1, upper = limited ? digits[i] : 9; j <= upper; j++) {
            boolean nextIncGood = incGood && (pre == -1 || j > pre);
            boolean nextDecGood = decGood && (pre == -1 || j < pre);
            ans += dfs(digits, i + 1, nextIncGood, nextDecGood, j, sum + j, limited && j == upper, true);
        }
        if (!limited && valid) memo[i][incGood ? 1 : 0][decGood ? 1 : 0][pre][sum] = ans;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countFancy(1, 1000000000000000L) == 907441159188136L;
        assert new Solution().countFancy(1, 999999999999999L) == 907441159188135L;
        assert new Solution().countFancy(1, 999999999999998L) == 907441159188134L;
        assert new Solution().countFancy(108010292329672L, 215989348011082L) == 98131025237800L;

        assert new Solution().countFancy(8, 10) == 3;
        assert new Solution().countFancy(12340, 12341) == 1;
        assert new Solution().countFancy(123456788, 123456788) == 0;
    }

}
