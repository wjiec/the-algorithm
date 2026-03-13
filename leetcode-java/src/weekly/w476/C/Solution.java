package weekly.w476.C;

/**
 * Q3. Count Distinct Integers After Removing Zeros
 *
 * https://leetcode.cn/contest/weekly-contest-476/problems/count-distinct-integers-after-removing-zeros/
 *
 * You are given a positive integer n.
 *
 * For every integer x from 1 to n, we write down the integer obtained by removing all zeros
 * from the decimal representation of x.
 *
 * Return an integer denoting the number of distinct integers written down.
 */

public class Solution {

    private static final long[] p = new long[20];
    static { p[0] = 1; }
    static { for (int i = 1; i < p.length; i++) p[i] = p[i - 1] * 9L; }

    public long countDistinct(long n) {
        if (n < 10) return n;
        char[] chars = String.valueOf(n).toCharArray();
        // 对于这个 1000 这个数, 1 后面的 [000] 可以是任意的组合
        //  - 只能是 1 [1-9]{3} 这样一共有 9 * 9 * 9 = 729 个数字
        // 其实就是从 9^1 + 9^2 + ... + 9^(l-1)
        long ans = 0;
        for (int i = 1; i < chars.length; i++) ans += p[i];

        int[] digits = new int[chars.length];
        for (int i = 0; i < chars.length; i++) digits[i] = chars[i] - '0';

        // 如果第一位为 3, 也就是可以是 0xxx 1xxx 2xxx
        //  - 对于 1xxx 和 2xxx 后面必须是 [1-9] 也就是 p[l - 1]
        ans += (digits[0] - 1) * p[chars.length - 1];
        //  - 剩下的 3xxx 需要根据内容进行计算
        return ans + dfs(digits, 1, true);
    }

    private long dfs(int[] digits, int i, boolean limited) {
        if (i == digits.length) return 1;
        // 如果当前位没有限制, 那么从 [i, n) 可以任意选择 [1-9]
        if (!limited) return p[digits.length - i];

        long ans = 0;
        for (int d = 1, upper = digits[i]; d <= upper; d++) {
            ans += dfs(digits, i + 1, d == upper);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countDistinct(200L) == 171;
        assert new Solution().countDistinct(120L) == 99;
        assert new Solution().countDistinct(21L) == 19;
        assert new Solution().countDistinct(20L) == 18;
        assert new Solution().countDistinct(11L) == 10;
        assert new Solution().countDistinct(12L) == 11;
        assert new Solution().countDistinct(1020103040102L) == 317733228540L;
        assert new Solution().countDistinct(1234567891234L) == 357449874727L;
        assert new Solution().countDistinct(8234567891234L) == 2334456630094L;
        assert new Solution().countDistinct(10) == 9;
        assert new Solution().countDistinct(3) == 3;
    }

}
