package problem.p2516takekofeachcharacterfromleftandright;

/**
 * 2516. Take K of Each Character From Left and Right
 *
 * https://leetcode.cn/problems/take-k-of-each-character-from-left-and-right/
 *
 * You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k.
 * Each minute, you may take either the leftmost character of s, or the rightmost character of s.
 *
 * Return the minimum number of minutes needed for you to take at least k of each character, or
 * return -1 if it is not possible to take k of each character.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int takeCharacters(String s, int k) {
        if (k == 0) return 0;

        int n = s.length();
        char[] chars = s.toCharArray();
        int[][] sum = new int[3][n + 1];
        for (int i = 1; i <= n; i++) {
            sum[0][i] += sum[0][i - 1];
            sum[1][i] += sum[1][i - 1];
            sum[2][i] += sum[2][i - 1];
            sum[chars[n - i] - 'a'][i]++;
        }
        if (sum[0][n] < k || sum[1][n] < k || sum[2][n] < k) return -1;

        int a = k, b = k, c = k, r = n, ans = n;
        for (int i = 0; i <= n; i++) {
            if (i != 0) {
                switch (chars[i - 1]) {
                    case 'a' -> a--;
                    case 'b' -> b--;
                    case 'c' -> c--;
                }
            }

            while (r > 0 && sum[0][r - 1] >= a && sum[1][r - 1] >= b && sum[2][r - 1] >= c) r--;
            ans = Math.min(ans, i + r);
            if (r == 0) break;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().takeCharacters("acbcc", 1) == 3;
        assert new Solution().takeCharacters("cbbac", 1) == 3;
        assert new Solution().takeCharacters("a", 0) == 0;

        assert new Solution().takeCharacters("aabaaaacaabc", 2) == 8;
        assert new Solution().takeCharacters("a", 1) == -1;
    }

}
