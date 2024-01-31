package problem.p1416restorethearray;

import java.util.Arrays;

/**
 * 1416. Restore The Array
 *
 * https://leetcode.cn/problems/restore-the-array
 *
 * A program was supposed to print an array of integers. The program forgot
 * to print whitespaces and the array is printed as a string of digits s
 * and all we know is that all integers in the array were in the
 * range [1, k] and there are no leading zeros in the array.
 *
 * Given the string s and the integer k, return the number of the possible
 * arrays that can be printed as s using the mentioned program.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 */

public class Solution {

    private final long[] base = new long[15];
    { Arrays.setAll(base, i -> (long) Math.pow(10, i)); }

    public int numberOfArrays(String s, int k) {
        final int MOD = 1_000_000_007;
        char[] chars = s.toCharArray();
        long[] dp = new long[s.length()];
        for (int i = 0; i < chars.length; i++) {
            long curr = 0; int j = i;
            while (j >= 0 && chars[j] == '0') j--;
            for (; j >= 0; j--) {
                curr += (chars[j] - '0') * base[i - j];
                if (chars[j] != '0' && curr >= 1 && curr <= k) {
                    dp[i] += j > 0 ? dp[j - 1] : 1;
                }
                if (curr > k) break;
            }

            if (dp[i] == 0) return 0;
            dp[i] %= MOD;
        }
        return (int) (dp[chars.length - 1] % MOD);
    }

    public static void main(String[] args) {
        assert new Solution().numberOfArrays("407780786171321121429620765476840275495357129574174233567552131453038760763182952432348422252546559691171161181440370120987634895458140447952079749439961325982629462531738374032416182281868731817661954890417245087359968833257550123324827240537957646002494771036413572415", 823924906) == 427125123;

        assert new Solution().numberOfArrays("1000", 10000) == 1;
        assert new Solution().numberOfArrays("1000", 10) == 0;
        assert new Solution().numberOfArrays("1317", 2000) == 8;
        assert new Solution().numberOfArrays("2020", 30) == 1;
        assert new Solution().numberOfArrays("1234567890", 90) == 34;
    }

}
