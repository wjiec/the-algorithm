package problem.p1624largestsubstringbetweentwoequalcharacters;

/**
 * 1624. Largest Substring Between Two Equal Characters
 *
 * https://leetcode-cn.com/problems/largest-substring-between-two-equal-characters/
 *
 * Given a string s, return the length of the longest substring between two equal characters,
 * excluding the two characters. If there is no such substring return -1.
 *
 * A substring is a contiguous sequence of characters within a string.
 */

public class Solution {

    public int maxLengthBetweenEqualCharacters(String s) {
        int[][] map = new int[26][2];
        for (var item : map) item[0] = item[1] = -1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map[c - 'a'][0] == -1) map[c - 'a'][0] = i;
            else map[c - 'a'][1] = i;
        }

        int ans = -1;
        for (var item : map) {
            if (item[1] != -1) ans = Math.max(ans, item[1] - item[0] - 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxLengthBetweenEqualCharacters("aa") == 0;
        assert new Solution().maxLengthBetweenEqualCharacters("abca") == 2;
        assert new Solution().maxLengthBetweenEqualCharacters("cbzxy") == -1;
        assert new Solution().maxLengthBetweenEqualCharacters("cabbac") == 4;
    }

}
