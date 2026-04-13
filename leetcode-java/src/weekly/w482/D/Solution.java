package weekly.w482.D;

import java.util.Arrays;

/**
 * Q4. Number of Balanced Integers in a Range
 *
 * https://leetcode.cn/contest/weekly-contest-482/problems/number-of-balanced-integers-in-a-range/
 *
 * You are given two integers low and high.
 *
 * An integer is called balanced if it satisfies both of the following conditions:
 *
 * It contains at least two digits.
 * The sum of digits at even positions is equal to the sum of digits
 * at odd positions (the leftmost digit has position 1).
 *
 * Return an integer representing the number of balanced integers in the range [low, high] (both inclusive).
 */

public class Solution {

    public long countBalanced(long low, long high) {
        return countBalanced(high) - countBalanced(low - 1);
    }

    private long countBalanced(long v) {
        if (v < 10) return 0;
        int[] digits = new int[String.valueOf(v).length()];
        for (int i = digits.length - 1; i >= 0; i--, v /= 10) digits[i] = (int) (v % 10);

        for (var mat : memo) for (var r : mat) Arrays.fill(r, -1);
        return dfs(digits, 0, 0, 1, false, true);
    }

    private final long[][][] memo = new long[20][200][3];

    private long dfs(int[] digits, int i, int diff, int base, boolean valid, boolean limited) {
        if (i >= digits.length) return valid && diff == 0 ? 1 : 0;
        if (!limited && valid && memo[i][diff + 100][base + 1] != -1) return memo[i][diff + 100][base + 1];

        long ans = !valid ? dfs(digits, i + 1, diff, base, false, false) : 0;
        for (int d = valid ? 0 : 1, upper = limited ? digits[i] : 9; d <= upper; d++) {
            ans += dfs(digits, i + 1, diff + base * d, base * -1, true, limited && d == upper);
        }

        if (!limited && valid) memo[i][diff + 100][base + 1] = ans;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countBalanced(1, 100) == 9;
        assert new Solution().countBalanced(120, 129) == 1;
        assert new Solution().countBalanced(1234, 1234) == 0;
    }

}
