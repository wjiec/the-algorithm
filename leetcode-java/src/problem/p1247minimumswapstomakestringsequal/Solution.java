package problem.p1247minimumswapstomakestringsequal;

/**
 * 1247. Minimum Swaps to Make Strings Equal
 *
 * https://leetcode.cn/problems/minimum-swaps-to-make-strings-equal/
 *
 * You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only.
 * Your task is to make these two strings equal to each other. You can swap any two characters
 * that belong to different strings, which means: swap s1[i] and s2[j].
 *
 * Return the minimum number of swaps required to make s1 and s2 equal, or
 * return -1 if it is impossible to do so.
 */

public class Solution {

    public int minimumSwap(String s1, String s2) {
        if (s1.length() != s2.length()) return -1;

        int x = 0, y = 0, n = s1.length();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) == 'x') x++; else y++;
            if (s2.charAt(i) == 'x') x++; else y++;
        }
        if ((x & 1) != 0 || (y & 1) != 0) return -1;

        int xy = 0, yx = 0;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (s1.charAt(i) == 'x' && s2.charAt(i) == 'y') xy++;
                else yx++;
            }
        }

        return (xy / 2) + (yx / 2) + ((xy & 1) == 0 ? 0 : 2);
    }

    public static void main(String[] args) {
        assert new Solution().minimumSwap("xx", "yy") == 1;
        assert new Solution().minimumSwap("xy", "yx") == 2;
        assert new Solution().minimumSwap("xx", "xy") == -1;
    }

}
