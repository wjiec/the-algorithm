package problem.p1513numberofsubstringswithonly1s;

/**
 * 1513. Number of Substrings With Only 1s
 *
 * https://leetcode.cn/problems/number-of-substrings-with-only-1s/
 *
 * Given a binary string s, return the number of substrings with all characters 1's.
 * Since the answer may be too large, return it modulo 109 + 7.
 */

public class Solution {

    public int numSub(String s) {
        long ans = 0, n = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; ) {
            if (chars[i] == '1') {
                int curr = 0;
                for (; i < n && chars[i] == '1'; i++)
                    ans += ++curr;
            } else i++;
        }
        return (int) (ans % 1_000_000_007);
    }

    public static void main(String[] args) {
        assert new Solution().numSub("0110111") == 9;
        assert new Solution().numSub("101") == 2;
        assert new Solution().numSub("111111") == 21;
        assert new Solution().numSub("000") == 0;
    }

}
