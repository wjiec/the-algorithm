package problem.p1864minimumnumberofswapstomakethebinarystringalternating;

/**
 * 1864. Minimum Number of Swaps to Make the Binary String Alternating
 *
 * https://leetcode.cn/problems/minimum-number-of-swaps-to-make-the-binary-string-alternating/
 *
 * Given a binary string s, return the minimum number of character swaps to
 * make it alternating, or -1 if it is impossible.
 *
 * The string is called alternating if no two adjacent characters are equal.
 * For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 *
 * Any two characters may be swapped, even if they are not adjacent.
 */

public class Solution {

    public int minSwaps(String s) {
        int ones = 0, n = s.length();
        for (var c : s.toCharArray()) ones += c - '0';

        int zero = n - ones;
        if (Math.abs(zero - ones) > 1) return -1;

        int first0 = 0, first1 = 0;
        for (int i = 0; i < n; i++) {
            int curr = s.charAt(i) - '0';
            // 第一位为0的情况
            if (i % 2 != curr) first0++;
            // 第一为为1的情况
            if ((i + 1) % 2 != curr) first1++;
        }

        if (ones > zero) return first1 / 2;
        if (zero > ones) return first0 / 2;
        return Math.min(first0 / 2, first1 / 2);
    }

    public static void main(String[] args) {
        assert new Solution().minSwaps("111000") == 1;
        assert new Solution().minSwaps("010") == 0;
        assert new Solution().minSwaps("1110") == -1;
    }

}
