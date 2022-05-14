package weekly.bw78.p2maximumwhitetilescoveredbyacarpet;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 6068. Maximum White Tiles Covered by a Carpet
 *
 * https://leetcode.cn/contest/biweekly-contest-78/problems/maximum-white-tiles-covered-by-a-carpet/
 *
 * You are given a 2D integer array tiles where tiles[i] = [li, ri] represents that
 * every tile j in the range li <= j <= ri is colored white.
 *
 * You are also given an integer carpetLen, the length of a single carpet that can be placed anywhere.
 *
 * Return the maximum number of white tiles that can be covered by the carpet.
 */

public class Solution {

    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, Comparator.comparingInt(v -> v[0]));

        int ans = 0, curr = 0;
        for (int l = 0, r = 0, n = tiles.length; l < n; l++) {
            for (; r < n && tiles[r][1] <= tiles[l][0] + carpetLen - 1; r++) {
                curr += tiles[r][1] - tiles[r][0] + 1;
            }

            if (curr > ans) ans = curr;
            if (r < n && tiles[l][0] + carpetLen - 1 >= tiles[r][0]) {
                ans = Math.max(ans, curr + (tiles[l][0] + carpetLen - tiles[r][0]));
            }

            curr -= tiles[l][1] - tiles[l][0] + 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumWhiteTiles(new int[][]{
            {1,5},{10,11},{12,18},{20,25},{30,32}
        }, 10) == 9;

        assert new Solution().maximumWhiteTiles(new int[][]{
            {10,11},{1,1}
        }, 2) == 2;

        assert new Solution().maximumWhiteTiles(new int[][]{
            {1,5},{10,11},{12,18},{20,25},{30,32}
        }, 11) == 10;
    }

}
