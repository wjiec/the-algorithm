package problem.p1573numberofwaystosplitastring;

/**
 * 1573. Number of Ways to Split a String
 *
 * https://leetcode.cn/problems/number-of-ways-to-split-a-string/
 *
 * Given a binary string s, you can split s into 3 non-empty strings s1, s2, and s3
 * where s1 + s2 + s3 = s.
 *
 * Return the number of ways s can be split such that the number of ones is the
 * same in s1, s2, and s3. Since the answer may be too large, return it modulo 109 + 7.
 */

public class Solution {

    public int numWays(String s) {
        int n = s.length(), ones = 0, MOD = 1_000_000_007;
        char[] chars = s.toCharArray();
        for (char c : chars) ones += c - '0';
        if (ones % 3 != 0) return 0;
        if (ones == 0) return (int) (((long) (n - 1) * (n - 2) / 2) % MOD);

        int lb = ones / 3, rb = ones / 3 * 2, cnt = 0, seg = 0, a = 0, b = 0;
        for (char c : chars) {
            if (c == '1') cnt++;
            else if (seg == 1 && c == '0') a++;
            else if (seg == 2 && c == '0') b++;

            if (cnt == lb) seg = 1;
            if (cnt == lb + 1) seg = 0;
            if (cnt == rb) seg = 2;
            if (cnt == rb + 1) break;
        }
        return (int) (((long) (a + 1) * (b + 1)) % MOD);
    }

    public static void main(String[] args) {
        assert new Solution().numWays("10101") == 4;
        assert new Solution().numWays("1001") == 0;
        assert new Solution().numWays("0000") == 3;
        assert new Solution().numWays("100100010100110") == 12;
    }

}
