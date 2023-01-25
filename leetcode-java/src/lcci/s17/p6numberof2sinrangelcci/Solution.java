package lcci.s17.p6numberof2sinrangelcci;

import java.util.Arrays;

/**
 * 面试题 17.06. 2出现的次数
 *
 * https://leetcode.cn/problems/number-of-2s-in-range-lcci/
 *
 * 编写一个方法，计算从 0 到 n (含 n) 中数字 2 出现的次数。
 */

public class Solution {

    public int numberOf2sInRange(int n) {
        char[] chars = Integer.toString(n).toCharArray();
        return dfs(chars, 0, 0, true);
    }

    private final int[][] dp = new int[11][11];
    { for (var rows : dp) Arrays.fill(rows, -1); }

    private int dfs(char[] chars, int i, int c, boolean limit) {
        if (i == chars.length) return c;
        if (!limit && dp[i][c] >= 0) return dp[i][c];

        int ans = 0;
        for (int j = 0, e = limit ? (chars[i] - '0') : 9; j <= e; j++) {
            ans += dfs(chars, i + 1, c + (j == 2 ? 1 : 0), limit && j == e);
        }
        if (!limit) dp[i][c] = ans;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOf2sInRange(123456789) == 100589849;
        assert new Solution().numberOf2sInRange(25) == 9;
    }

}
