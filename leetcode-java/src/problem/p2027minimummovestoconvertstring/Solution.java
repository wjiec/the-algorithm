package problem.p2027minimummovestoconvertstring;

/**
 * 2027. Minimum Moves to Convert String
 *
 * https://leetcode-cn.com/problems/minimum-moves-to-convert-string/
 *
 * You are given a string s consisting of n characters which are either 'X' or 'O'.
 *
 * A move is defined as selecting three consecutive characters of s and converting them to 'O'.
 *
 * Note that if a move is applied to the character 'O', it will stay the same.
 *
 * Return the minimum number of moves required so that all the characters of s are converted to 'O'.
 */

public class Solution {

    public int minimumMoves(String s) {
        int ans = 0, l = s.length();
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == 'X') {
                ans++;
                i += 2;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumMoves("XXX") == 1;
        assert new Solution().minimumMoves("XXOX") == 2;
        assert new Solution().minimumMoves("OOOO") == 0;
    }

}
