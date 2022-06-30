package problem.p1079lettertilepossibilities;

import java.util.Arrays;

/**
 * 1079. Letter Tile Possibilities
 *
 * https://leetcode.cn/problems/letter-tile-possibilities/
 *
 * You have n tiles, where each tile has one letter tiles[i] printed on it.
 *
 * Return the number of possible non-empty sequences of letters you can make using
 * the letters printed on those tiles.
 */

public class Solution {

    private int ans = 0;

    public int numTilePossibilities(String tiles) {
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        dfs(chars);
        return ans;
    }

    private void dfs(char[] chars) {
        char prev = '*';
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '*' && chars[i] != prev) {
                ans++;
                char curr = chars[i]; chars[i] = '*';
                dfs(chars); chars[i] = curr; prev = curr;
            }
        }
    }

    public static void main(String[] args) {
        assert new Solution().numTilePossibilities("AAB") == 8;
        assert new Solution().numTilePossibilities("AAABBC") == 188;
        assert new Solution().numTilePossibilities("V") == 1;
    }

}
